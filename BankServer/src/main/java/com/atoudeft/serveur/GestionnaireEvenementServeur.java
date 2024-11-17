package com.atoudeft.serveur;

import com.atoudeft.banque.*;
import com.atoudeft.banque.serveur.ConnexionBanque;
import com.atoudeft.banque.serveur.ServeurBanque;
import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Cette classe représente un gestionnaire d'événement d'un serveur. Lorsqu'un serveur reçoit un texte d'un client,
 * il crée un événement à partir du texte reçu et alerte ce gestionnaire qui réagit en gérant l'événement.
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-09-01
 */
public class GestionnaireEvenementServeur implements GestionnaireEvenement {
    private Serveur serveur;

    /**
     * Construit un gestionnaire d'événements pour un serveur.
     *
     * @param serveur Serveur Le serveur pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    /**
     * Méthode de gestion d'événements. Cette méthode contiendra le code qui gère les réponses obtenues d'un client.
     *
     * @param evenement L'événement à gérer.
     */
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        ServeurBanque serveurBanque = (ServeurBanque)serveur;
        Banque banque;
        ConnexionBanque cnx;
        String msg, typeEvenement, argument, numCompteClient, nip;
        String[] t;

        if (source instanceof Connexion) {
            cnx = (ConnexionBanque) source;
            System.out.println("SERVEUR: Recu : " + evenement.getType() + " " + evenement.getArgument());
            typeEvenement = evenement.getType();
            cnx.setTempsDerniereOperation(System.currentTimeMillis());
            switch (typeEvenement) {

                /******************* COMMANDES GÉNÉRALES *******************/
                case "EXIT": //Ferme la connexion avec le client qui a envoyé "EXIT":
                    cnx.envoyer("END");
                    serveurBanque.enlever(cnx);
                    cnx.close();
                    break;

                case "LIST": //Envoie la liste des numéros de comptes-clients connectés :
                    cnx.envoyer("LIST " + serveurBanque.list());
                    break;

                /******************* COMMANDES DE GESTION DE COMPTES *******************/
                case "NOUVEAU": //Crée un nouveau compte-client :
                    if (cnx.getNumeroCompteClient()!=null) {
                        cnx.envoyer("NOUVEAU NO deja connecte");
                        break;
                    }
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    if (t.length<2) {
                        cnx.envoyer("NOUVEAU NO");
                    }
                    else {
                        numCompteClient = t[0];
                        nip = t[1];
                        banque = serveurBanque.getBanque();
                        if (banque.ajouter(numCompteClient,nip)) {
                            cnx.setNumeroCompteClient(numCompteClient);
                            cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(numCompteClient));
                            cnx.envoyer("NOUVEAU OK " + t[0] + " cree");
                        }
                        else
                            cnx.envoyer("NOUVEAU NO "+t[0]+" existe");
                    }
                    break;
                case "CONNECT":
                    argument = evenement.getArgument();

                    /*JE VÉRIFIE SI LES ARGUMENTS CONTIENNENT LE REGEX DU SPLIT ET SI ILS NE SONT PAS NULLES*/
                    if(argument == null || !argument.contains(":")){
                        cnx.envoyer("CONNECT NO");
                        break;
                    }
                    t = argument.split(":");
                    if(t.length !=2){
                        cnx.envoyer("CONNECT NO");
                        break;
                    }

                    numCompteClient = t[0];
                    nip = t[1];

                    //VÉRIFIONS QUE LE COMPTE CLIENT EXISTE
                    banque = serveurBanque.getBanque();
                    ArrayList<CompteClient>comptes = (ArrayList<CompteClient>) banque.getComptes();

                    CompteClient compteClient = banque.getCompteClient(numCompteClient);

                    if(!comptes.contains(compteClient)){
                        cnx.envoyer("LE COMPTE CLIENT N'EXISTE PAS !");
                        break;
                    }
                    //VÉRIFIONS QUE LE COMPTE N'EST PAS DÉJÀ CONNECTÉ.
                    Iterator <Connexion> iterator = serveur.connectes.iterator();
                    while (iterator.hasNext()){
                        ConnexionBanque cnx1 = (ConnexionBanque) iterator.next();
                        if (numCompteClient.equals(cnx1.getNumeroCompteClient())){
                            cnx.envoyer("CONNECT NO");
                            break;
                        }
                        else{
                            ArrayList<CompteClient> compteClients = (ArrayList<CompteClient>)banque.getComptes();
                            Iterator<CompteClient> iterator1 = compteClients.iterator();
                            boolean existe = false ;
                            while (iterator1.hasNext()){
                                CompteClient compteClient1 = iterator1.next();
                                if(compteClient1.getNumero().equals(numCompteClient)){
                                    existe = true;
                                    break;
                                }
                            }
                            if(!existe || !compteClient.getNip().equals(nip)){
                                cnx.envoyer("CONNECT NO5");
                            }else {
                                cnx.setNumeroCompteClient(numCompteClient);
                                cnx.setNumeroCompteActuel(numCompteClient);
                                cnx.envoyer("CONNECT OK");
                            }
                        }
                    // QUESTRION 3 ?
                    //VERIFIER QUE LE COMPTE CLIENT EXISTE DANS LES COMPTES BANCAIRES PAS QUE LE NIP CORRESPONDS AU NIP DU COMPTE TROUVÉ.

                        break;
                    }


                case "EPARGNE":
                    if(cnx.getNumeroCompteClient() == null){
                        cnx.envoyer("EPARGNE NO");
                        break;
                    }
                    Banque banque1 = serveurBanque.getBanque();
                    if(banque1.getNumeroCompteEpargne(cnx.getNumeroCompteClient())!=null){
                        cnx.envoyer("EPARGNE NO");
                        break;
                    }
                    CompteClient compteClients;
                    Iterator<CompteClient> iterator1 = banque1.getComptes().iterator();
                    ArrayList<CompteBancaire> compteBancairese;
                    while (iterator1.hasNext()){
                        compteClients = iterator1.next();
                        CompteBancaire compteBancaires;

                    }
                    String numeroCompteB = CompteBancaire.genereNouveauNumero();
                    banque1.getCompteClient(cnx.getNumeroCompteClient()).ajouter(new CompteEpargne(numeroCompteB,TypeCompte.EPARGNE,0.05));
                    cnx.envoyer("EPARGNE OK");
                    break;
// SELECT permettera de choisir entre le compte epargne ou cheque
                case "SELECT" :
                    argument = evenement.getArgument();
                    if (cnx.getNumeroCompteClient()==null) {
                        cnx.envoyer("aucun compte connecte");
                        cnx.envoyer("SELECT NO");
                        cnx.envoyer(cnx.getNumeroCompteActuel());
                        break;
                    }

                    if (argument.contentEquals("cheque")) {
                        cnx.envoyer("Compte cheque selectionnee");
                        String typecompte="cheque";
                        String numcptactuel= cnx.getNumeroCompteClient();
                        String numcptcheque = serveurBanque.getBanque().getNumeroCompteParDefaut(numcptactuel);
                        cnx.envoyer(numcptcheque);
                        cnx.envoyer("SELECT OK");

                    } else if (argument.contentEquals("epargne")) {
                        cnx.envoyer("Compte epargne selectionnee");
                        String typecompte="epargne";
                        String numcptactuel= cnx.getNumeroCompteClient();
                        String numcptepargne = serveurBanque.getBanque().getNumeroCompteParDefaut(numcptactuel);
                        cnx.envoyer(numcptepargne);
                        cnx.envoyer("SELECT OK");

                    }
                    else {
                        cnx.envoyer("SELECT NO");
                    }

                    break;


                case "DEPOT" :
                    argument = evenement.getArgument();
                    String compteutilise="test";
                    if (compteutilise.contentEquals("cheque")){
                        
                    }
                    if (compteutilise.contentEquals("epargne")) {
                        
                    }
                    break;

                case "RETRAIT" :
                    argument = evenement.getArgument();
                    compteutilise=argument;
                    if (compteutilise.contentEquals("cheque")){

                    }
                    if (compteutilise.contentEquals("epargne")) {

                    }
                    break;


                case "FACTURE" :
                    argument = evenement.getArgument();
                    t = argument.split("//s+-");
                    String montant = t[0];
                    String numfacture = t[1];
                    String descfacture = t[2];

                    cnx.envoyer(montant);
                    cnx.envoyer(numfacture);
                    cnx.envoyer(descfacture);

                    break;

                case "TRANSFER" :
                    argument = evenement.getArgument();

                    break;


                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default: //Renvoyer le texte recu convertit en majuscules :
                    msg = (evenement.getType() + " " + evenement.getArgument()).toUpperCase();
                    cnx.envoyer(msg);
            }
        }
    }
}
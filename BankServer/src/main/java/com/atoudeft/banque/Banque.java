package com.atoudeft.banque;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.text.html.HTMLDocument;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banque implements Serializable {
    private String nom;
    private List<CompteClient> comptes;

    public Banque(String nom) {
        this.nom = nom;
        this.comptes = new ArrayList<>();
    }

    /**
     * Recherche un compte-client à partir de son numéro.
     *
     * @param numeroCompteClient le numéro du compte-client
     * @return le compte-client s'il a été trouvé. Sinon, retourne null
     */
    public CompteClient getCompteClient(String numeroCompteClient) {
        CompteClient cpt = new CompteClient(numeroCompteClient, "");
        int index = this.comptes.indexOf(cpt);
        if (index != -1)
            return this.comptes.get(index);
        else
            return null;
    }

    /**
     * Vérifier qu'un compte-bancaire appartient bien au compte-client.
     *
     * @param numeroCompteBancaire numéro du compte-bancaire
     * @param numeroCompteClient   numéro du compte-client
     * @return true si le compte-bancaire appartient au compte-client
     */
    public boolean appartientA(String numeroCompteBancaire, String numeroCompteClient) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un dépot d'argent dans un compte-bancaire
     *
     * @param montant      montant à déposer
     * @param numeroCompte numéro du compte
     * @return true si le dépot s'est effectué correctement
     */
    public boolean deposer(double montant, String numeroCompte) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un retrait d'argent d'un compte-bancaire
     *
     * @param montant      montant retiré
     * @param numeroCompte numéro du compte
     * @return true si le retrait s'est effectué correctement
     */
    public boolean retirer(double montant, String numeroCompte) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un transfert d'argent d'un compte à un autre de la même banque
     *
     * @param montant             montant à transférer
     * @param numeroCompteInitial numéro du compte d'où sera prélevé l'argent
     * @param numeroCompteFinal   numéro du compte où sera déposé l'argent
     * @return true si l'opération s'est déroulée correctement
     */
    public boolean transferer(double montant, String numeroCompteInitial, String numeroCompteFinal) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un paiement de facture.
     *
     * @param montant       montant de la facture
     * @param numeroCompte  numéro du compte bancaire d'où va se faire le paiement
     * @param numeroFacture numéro de la facture
     * @param description   texte descriptif de la facture
     * @return true si le paiement s'est bien effectuée
     */
    public boolean payerFacture(double montant, String numeroCompte, String numeroFacture, String description) {
        throw new NotImplementedException();
    }

    /**
     * Crée un nouveau compte-client avec un numéro et un nip et l'ajoute à la liste des comptes.
     *
     * @param numCompteClient numéro du compte-client à créer
     * @param nip             nip du compte-client à créer
     * @return true si le compte a été créé correctement
     */
    public boolean ajouter(String numCompteClient, String nip) {
        /*À compléter et modifier :
            - Vérifier que le numéro a entre 6 et 8 caractères et ne contient que des lettres majuscules et des chiffres.
              Sinon, retourner false.
            - Vérifier que le nip a entre 4 et 5 caractères et ne contient que des chiffres. Sinon,
              retourner false.
            - Vérifier s'il y a déjà un compte-client avec le numéro, retourner false.
            - Sinon :
                . Créer un compte-client avec le numéro et le nip;
                . Générer (avec CompteBancaire.genereNouveauNumero()) un nouveau numéro de compte bancaire qui n'est
                  pas déjà utilisé;
                . Créer un compte-chèque aver ce numéro et l'ajouter au compte-client;
                . Ajouter le compte-client à la liste des comptes et retourner true.
         */

        // Ici nous allons vérifier pour le numéro de compte fourni

        boolean numCptOk = false;
        boolean pinCptOk = false;
        boolean checkChiffre = false;
        boolean checkMaj = false;
        Boolean compteCree = false;
        int taillech = numCompteClient.length();
        char carac;
        char carap;

        if (taillech < 6 || taillech > 8){
            return false;
        }
        else {
            for (int i = 0; i < numCompteClient.length(); i++) {
                carac = numCompteClient.charAt(i);
                if (Character.isUpperCase(carac))//carac>='A' && carac<='Z'
                    checkMaj = true;

                else if (Character.isDigit(carac))//carac>='0' && carac<='9'
                    checkChiffre = true;

            }
        }
        if (checkMaj && checkChiffre) {
            numCptOk = true;
        }
        // Ici nous allons vérifier le pin fourni

        int tailp = nip.length();

        if (tailp < 4 || tailp > 5){
            return false;
        }
        else {
                for (int j = 0; j < nip.length(); j++) {
                    carap = nip.charAt(j);
                    if(!Character.isLetter(carap)) {
                        pinCptOk = true;
                    }
                    else {
                        pinCptOk = false;
                    }
                }
        }

        Iterator<CompteClient> iterator = comptes.iterator();
        ArrayList <CompteBancaire> compteBancaireClient = new ArrayList<CompteBancaire>();
        CompteCheque cheque = null;
        while (iterator.hasNext()){
            if (numCptOk && pinCptOk && numCompteClient != iterator.next().getNumero()) {
                CompteClient compteClient = new CompteClient(numCompteClient, nip);
                String numCompteBancaire = CompteBancaire.genereNouveauNumero();
                compteBancaireClient = (ArrayList<CompteBancaire>) iterator.next().getComptesBancaire();
                Iterator<CompteBancaire> iterator2 = compteBancaireClient.iterator();
                while (iterator2.hasNext()){
                    if (iterator2.next().getNumero().compareTo(numCompteBancaire)!=0 ) {
                        cheque = new CompteCheque(numCompteBancaire, TypeCompte.CHEQUE);
                    }
                    iterator.next().ajouter(cheque);
                        comptes.add(compteClient);
                        compteCree = true;
                    }

                }

            }

        return compteCree;
        //return this.comptes.add(new CompteClient(numCompteClient, nip));//À modifier
    }

    /**
     * Retourne le numéro du compte-chèque d'un client à partir de son numéro de compte-client.123
     *
     * @param numCompteClient numéro de compte-client
     * @return numéro du compte-chèque du client ayant le numéro de compte-client
     */
    public String getNumeroCompteParDefaut(String numCompteClient) {
        CompteClient compteClient = getCompteClient(numCompteClient);
        String numeroCompteCheque="PAS DE NUMÉRO DE COMPTE CHÈQUE !";
        if(compteClient != null){
            Iterator<CompteBancaire> iterator = compteClient.getComptesBancaire().iterator();
            CompteBancaire compte;
            while (iterator.hasNext()){
                 compte = iterator.next();
                 if(compte.getType().compareTo(TypeCompte.CHEQUE)==0){
                     numeroCompteCheque = compte.getNumero();
                     return numeroCompteCheque;
                 } else return numeroCompteCheque;
            }
        }else  System.out.println("Ce numéro de Compte Client ne correspond à aucun Compte Client !");
        return numeroCompteCheque;
}
    /**
     *CETTE MÉTHODE EST UTILISÉE POUR VÉRIFIER SI LE CLIENT POSSÈDE UN COMPTE ÉPARGNE !
     */
    public boolean verifierCompteEpargne(){
        return true;
    }

}
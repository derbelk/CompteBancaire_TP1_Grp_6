package com.atoudeft.client;

import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauHistorique;
import com.atoudeft.vue.PanneauOperationsCompte;
import com.atoudeft.vue.PanneauPrincipal;
import com.programmes.MainFrame;

import javax.swing.*;

public class GestionnaireEvenementClient2 implements GestionnaireEvenement {
    private Client client;
    private PanneauPrincipal panneauPrincipal;

    /**
     * Construit un gestionnaire d'événements pour un client.
     *
     * @param client Client Le client pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementClient2(Client client, PanneauPrincipal panneauPrincipal) {

        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
        this.client.setGestionnaireEvenement(this);
    }
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        //Connexion cnx;
        String typeEvenement, arg, str;
        int i;
        String[] t;
        MainFrame fenetre;
        // UPDATE2.1
        if (source instanceof Connexion) {
            //cnx = (Connexion) source;
            typeEvenement = evenement.getType();
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "END": //Le serveur demande de fermer la connexion
                    client.deconnecter(); //On ferme la connexion
                    break;
                /******************* CREATION et CONNEXION *******************/
//                case "HIST": //Le serveur a renvoyé
//                    panneauPrincipal.setVisible(true);
//                    JOptionPane.showMessageDialog(null,"Panneau visible");
//                    client.envoyer("LIST");
//                    arg = evenement.getArgument();
//                    break;
                case "OK":
                    panneauPrincipal.setVisible(true);
                    fenetre = (MainFrame)panneauPrincipal.getTopLevelAncestor();
                    fenetre.setTitle(MainFrame.TITRE);//+" - Connecté"
                    break;
                case "NOUVEAU":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Nouveau refusé");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.ajouterCompte(str);
                    }
                    break;
                case "CONNECT":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Connexion refusée");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        for (String s:t) {
                            panneauPrincipal.ajouterCompte(s.substring(0,s.indexOf("]")+1));
                        }
                    }
                    break;
                /******************* SÉLECTION DE COMPTES *******************/
                case "EPARGNE" :
                    //Question 2.1
                    arg = evenement.getArgument();

                    if (arg.trim().startsWith("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal,"Création de compte épargne échoué");
                    }
                    else {
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        for (String s:t) {
                            panneauPrincipal.ajouterCompte(s.substring(0,s.indexOf("]")+1));
                        }

                        JOptionPane.showMessageDialog(panneauPrincipal,"Compte Épargne créé avec succés "+ str);

                        panneauPrincipal.ajouterCompte(arg.substring(arg.indexOf("]")+4));
                    }
                    //*/
                    break;
                case "SELECT" :
                    arg = evenement.getArgument();
                    String[] arguments = new String[arg.length()];
                    arguments = arg.split(" ");
                    String solde = arguments[arguments.length -1];
                    //Question 3.1
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Aucun compte selectionné");
                    }
                    else {

                    }
                    panneauPrincipal.setPanneauOperationsCompte(solde);
                    //JOptionPane.showMessageDialog(panneauPrincipal,"SELECT "+arg);
                    JOptionPane.showMessageDialog(panneauPrincipal,"Compte séléctionné : "+ arg);
                    break;

                /******************* OPÉRATIONS BANCAIRES *******************/
                case "DEPOT" :
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Dépot non effectué");
                    }
                    else {
                        //JOptionPane.showMessageDialog(panneauPrincipal,"Dépot effectué avec succées");
                        String[] arguments1 = new String[arg.length()];
                        arguments = arg.split(" ");
                        String solde1 = arguments[arguments.length -1];
                        JOptionPane.showMessageDialog(panneauPrincipal, "DEPOT " + arg);
                        panneauPrincipal.setPanneauOperationsCompte(solde1);
                    }

                    break;
                case "RETRAIT" :
                    arg = evenement.getArgument();

                    if (arg.trim().startsWith("NO")) {
                    JOptionPane.showMessageDialog(panneauPrincipal,"Retrait non effectué");
                }
                    else{
                    String[] arguments1 = new String[arg.length()];
                    arguments = arg.split(" ");
                    String solde1 = arguments[arguments.length -1];
                    JOptionPane.showMessageDialog(panneauPrincipal,"Retrait effectué avec succées");
                    panneauPrincipal.setPanneauOperationsCompte(solde1);
                }
                //JOptionPane.showMessageDialog(panneauPrincipal,"RETRAIT "+arg);
                break;
                case "FACTURE" :
                    arg = evenement.getArgument();

                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Payement de facture non effectué");
                    }
                    else{
                        String[] arguments1 = new String[arg.length()];
                        arguments = arg.split(" ");
                        String solde1 = arguments[arguments.length -1];
                        JOptionPane.showMessageDialog(panneauPrincipal,"Facture payé");
                        panneauPrincipal.setPanneauOperationsCompte(solde1);
                    }

                    break;
                case "TRANSFER" :
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal, "Un problème a été rencontré lors du transfert. Vérifier votre solde ou le numéro du compte vers lequel vous voulez effectuer cette opération","Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String[] arguments1 = new String[arg.length()];
                        arguments = arg.split(" ");
                        String solde1 = arguments[arguments.length -1];
                        JOptionPane.showMessageDialog(panneauPrincipal, "Transfert effectué avec succées");
                        panneauPrincipal.setPanneauOperationsCompte(solde1);
                        //JOptionPane.showMessageDialog(panneauPrincipal, "TRANSFER " + arg);
                    }
                    break;

                case "HIST" :
                    arg = evenement.getArgument();
                    PanneauHistorique panneauHistorique = new PanneauHistorique();

                    panneauHistorique.setjTextArea(arg);
                    JOptionPane.showMessageDialog(null, panneauHistorique, "Historique de transaction", JOptionPane.PLAIN_MESSAGE);



                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default:
                    System.out.println("RECU : "+evenement.getType()+" "+evenement.getArgument());
            }
        }
    }
}
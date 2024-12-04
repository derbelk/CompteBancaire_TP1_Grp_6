package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.sql.Struct;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    //=private PanneauPrincipal panneauPrincipal;

    public EcouteurOperationsCompte(Client client) {
        this.client = client;
        //panneauPrincipal = new PanneauPrincipal(client);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //à compléter :
        //Question 2.1
        Object source = e.getSource();
        String action;
        if (source instanceof JButton) {
            action = ((JButton) source).getActionCommand();

            if ("EPARGNE".equals(action)) {

                client.envoyer("EPARGNE");
            }

            if ("DEPOT".equals(action)) {

                //JOptionPane.showMessageDialog(panneauDepot,"Voici le panneau de dépot");
                double montantDepot;
                int res;
                boolean operationDepot = false;

                PanneauDepot panneauDepot = new PanneauDepot();

                res = JOptionPane.showConfirmDialog(null, panneauDepot, "Depot",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (res == 0){

                    while(!operationDepot) {
                        String tmontant = panneauDepot.getmontant();

                        try {
                            montantDepot = Double.parseDouble(tmontant);
                            //JOptionPane.showMessageDialog(panneauDepot, "Ce montant sera déposé dans votre compte " + montantDepot);
                            operationDepot = true;

                            client.envoyer("DEPOT "+ montantDepot);

                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(panneauDepot, "le montant entré doit être un nombre", "Attention", JOptionPane.ERROR_MESSAGE);

                            res = JOptionPane.showConfirmDialog(null, panneauDepot, "Depot",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                        }
                    }
                }



            }

            if ("RETRAIT".equals(action)) {

                double montantRetrait;
                int res;
                boolean operationRetrait = false;

                PanneauRetrait panneauRetrait = new PanneauRetrait();

                res = JOptionPane.showConfirmDialog(null, panneauRetrait, "Retrait",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (res == 0) {

                    while (!operationRetrait) {
                        String tmontant2 = panneauRetrait.getmontant();

                        try {
                            montantRetrait = Double.parseDouble(tmontant2);
                            //JOptionPane.showMessageDialog(panneauRetrait, "Ce montant sera retiré de votre compte " + montantRetrait);
                            operationRetrait = true;

                            client.envoyer("RETRAIT " + montantRetrait);

                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(panneauRetrait, "le montant entré doit être un nombre", "Attention", JOptionPane.ERROR_MESSAGE);

                            res = JOptionPane.showConfirmDialog(null, panneauRetrait, "Depot",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                        }
                    }
                }
            }


            if ("TRANSFER".equals(action)) {

                double montantTransfert;
                int res;
                boolean operationTransfert = false;

                PanneauTransfert panneauTransfert = new PanneauTransfert();

                res = JOptionPane.showConfirmDialog(null, panneauTransfert, "Opération de transfert",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (res == 0) {
                    while (!operationTransfert) {
                        String numeroCompte = panneauTransfert.getNumeroCompte();
                        String tmontant3 = panneauTransfert.getMontant();

                        try {
                            montantTransfert = Double.parseDouble(tmontant3);
                            JOptionPane.showMessageDialog(panneauTransfert, montantTransfert + " seront transférés de votre compte vers le compte " + numeroCompte);
                            operationTransfert = true;

                            client.envoyer("TRANSFER " + montantTransfert + " " + numeroCompte);

                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(panneauTransfert, "le montant entré doit être un nombre", "Attention", JOptionPane.ERROR_MESSAGE);

                            res = JOptionPane.showConfirmDialog(null, panneauTransfert, "Opération de transfert",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                        }

                    }
                }
            }


            if ("FACTURE".equals(action)) {

                int res;
                double montant;
                String textMontant, numeroFacture, description, mois, annee;
                boolean facturePayee = false;
                PanneauFacture panneauFacture = new PanneauFacture();

                res = JOptionPane.showConfirmDialog(null, panneauFacture, "Payement de Facture",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (res == 0){
                    while (!facturePayee){
                        textMontant = panneauFacture.getMontant();
                        numeroFacture = panneauFacture.getNumeroFacture();
                        description = panneauFacture.getDescription();
                        mois = (String)panneauFacture.getMois();
                        annee = (String)panneauFacture.getAnnee();

                        try{
                            montant = Double.parseDouble(textMontant);
                            JOptionPane.showMessageDialog(panneauFacture, montant+" "+ numeroFacture+" "+description+" "+ mois+ " "+annee);
                            client.envoyer("FACTURE "+montant+" "+ numeroFacture+" "+description+" "+ mois+ " "+annee);
                            facturePayee = true;
                        } catch (Exception exception){

                            JOptionPane.showMessageDialog(panneauFacture, "le montant entré doit être un nombre", "Attention", JOptionPane.ERROR_MESSAGE);

                            res = JOptionPane.showConfirmDialog(null, panneauFacture, "Payement de Facture",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                        }

                    }

                }

            }

           if ("HIST".equals(action)){

               client.envoyer("HIST");
            }


        }

    }
}

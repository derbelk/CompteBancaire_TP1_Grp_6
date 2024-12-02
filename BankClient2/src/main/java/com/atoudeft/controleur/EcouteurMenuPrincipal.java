package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauConfigServeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class EcouteurMenuPrincipal implements ActionListener {
    private Client client;
    private JFrame fenetre;
    //Question 2.1
    private Client epargne;

    public EcouteurMenuPrincipal(Client client, JFrame fenetre) {
        this.client = client;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        String action;
        String alias;
        int res;

        if (source instanceof JMenuItem) {
            action = ((JMenuItem)source).getActionCommand();
            switch (action) {
                case "CONNECTER":
                    if (!client.isConnecte()) {
                        if (!client.connecter()) {
                            JOptionPane.showMessageDialog(fenetre, "Le serveur ne répond pas");
                            break;
                        }
                    }
                    break;
                case "DECONNECTER":
                    if (!client.isConnecte())
                        break;
                    res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                            "Confirmation Déconnecter",
                            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.deconnecter();
                    }
                    break;
                case "CONFIGURER":

                    //

                    PanneauConfigServeur panneauConfigServeur = new PanneauConfigServeur();
                    int port;
                    boolean okay = false;
                    //Question 1.3 : Fait

                        res = JOptionPane.showConfirmDialog(fenetre,  panneauConfigServeur, "Configuration Serveur",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (res == 0) {

                            while (!okay) {
                                String numeroPort = panneauConfigServeur.getPortServeur();
                                try {
                                    port = Integer.parseInt(numeroPort);
                                    JOptionPane.showMessageDialog(panneauConfigServeur, "Okay c'est un int " + numeroPort);
                                    okay = true;

                                } catch (Exception exception) {
                                    JOptionPane.showMessageDialog(panneauConfigServeur, "Le numéro ne doit pas être String",
                                            "Choix invalide", JOptionPane.ERROR_MESSAGE);

                                    res = JOptionPane.showConfirmDialog(fenetre, panneauConfigServeur, "Configuration Serveur",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                    //okay = false;
                                }

                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(fenetre,"configuration annullée",
                                    "Information",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    break;
                case "QUITTER":
                    if (client.isConnecte()) {
                        res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                                "Confirmation Quitter",
                                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.OK_OPTION){
                            client.deconnecter();
                            System.exit(0);
                        }
                    }
                    else
                        System.exit(0);
                    break;
            }
        }
    }
}
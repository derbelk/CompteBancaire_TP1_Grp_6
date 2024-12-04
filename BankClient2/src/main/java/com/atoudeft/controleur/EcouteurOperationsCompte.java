package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauDepot;
import com.atoudeft.vue.PanneauOperationsCompte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauDepot panneauDepot;

    public EcouteurOperationsCompte(Client client) {
        this.client = client;
        this.panneauDepot = new PanneauDepot();

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

                panneauDepot.montrerPanneauDepot();


                //JOptionPane.showMessageDialog(panneauEffectuerOperations, "okay là on veut faire un dépot");


            }
           /* //else if ("RETRAIT".equals(action)) {


                //JOptionPane.showMessageDialog(, "okay là on veut faire un retrait");
            }

            //else if ("FACTURE".equals(action)) {


            //JOptionPane.showMessageDialog(, "okay là on veut payer une facture");
        }
            //else if ("TRANSFER".equals(action)) {


                //JOptionPane.showMessageDialog(, "okay là on veut tranferer vers un autre compte");
            }*/


        }
    }
}

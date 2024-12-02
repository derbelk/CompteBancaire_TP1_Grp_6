package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauOperationsCompte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;

    public EcouteurOperationsCompte(Client client) {this.client = client;}

    @Override
    public void actionPerformed(ActionEvent e) {
        //à compléter :
        //Question 2.1
        Object source = e.getSource();
        String action;
        if (source instanceof JButton){
            action = ((JButton)source).getActionCommand();

            if ("EPARGNE".equals(action)){

                client.envoyer("EPARGNE");
                //JOptionPane.showMessageDialog(null, "Okay on est bon");
            }
        }




    }
}

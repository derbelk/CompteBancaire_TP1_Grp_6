package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauConnexion;
import com.atoudeft.vue.PanneauOperationsCompte;
import com.atoudeft.vue.PanneauPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeComptes extends MouseAdapter {

    private Client client;
    public EcouteurListeComptes(Client client) {
        this.client = client;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        //à compléter
        //Question 3.1
        Object source = evt.getSource();


            if (evt.getClickCount()==2){

                String text = (String) ((JList<?>) source).getSelectedValue();

                if (text.contains("CHEQUE"))
                    client.envoyer("SELECT cheque");
                else if (text.contains("EPARGNE")) {
                    client.envoyer("SELECT epargne");
                }
            }

    }
}

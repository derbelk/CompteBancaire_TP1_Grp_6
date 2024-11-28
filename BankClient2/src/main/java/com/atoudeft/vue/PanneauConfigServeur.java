package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {
        //à compléter
        JPanel panel = new JPanel();
        JLabel lAdrServeur = new JLabel("Adresse IP : "),
                lNumPort = new JLabel("Port : ");

        txtAdrServeur = new JTextField(adr,15);
        txtNumPort = new JTextField(String.valueOf(port),5);

        panel.add(lAdrServeur);
        panel.add(txtAdrServeur);
        panel.add(lNumPort);
        panel.add(txtNumPort);

        //txtAdrServeur.setBorder(BorderFactory.createTitledBorder("Adresse IP : "));
        //txtNumPort.setBorder(BorderFactory.createTitledBorder("Port : "));
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}

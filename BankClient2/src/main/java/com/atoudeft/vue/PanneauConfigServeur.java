package com.atoudeft.vue;

import com.atoudeft.client.Config;

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

    public PanneauConfigServeur() {
        //1.2 correction

        JPanel p = new JPanel(new GridLayout(2,2));
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        JLabel lAdrServeur = new JLabel("Adresse IP:");
        JLabel lNumPort = new JLabel("Port:");

        txtAdrServeur = new JTextField(Config.ADRESSE_SERVEUR,15);
        txtAdrServeur.setEditable(false);
        txtNumPort = new JTextField(String.valueOf(Config.PORT_SERVEUR),5);


        //this.add(p.add(lAdrServeur));

        p1.add(lAdrServeur,txtAdrServeur);
        //p1.add(txtAdrServeur);
        //this.add(p.add(txtAdrServeur));

        //this.add(p.add(lNumPort));
        //this.add(p.add(txtNumPort));
        p2.add(lNumPort,txtNumPort);
        //p2.add(txtNumPort);
        p.add(p1);
        p.add(txtAdrServeur);
        p.add(p2);
        p.add(txtNumPort);

        this.add(p);


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

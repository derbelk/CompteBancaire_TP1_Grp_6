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
        //à compléter

        JPanel p1 = new JPanel();
        JPanel p2= new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));


        JLabel lAdrServeur = new JLabel("Adresse IP : ");
        JLabel lNumPort = new JLabel("Port : ");

        txtAdrServeur = new JTextField(Config.ADRESSE_SERVEUR,15);
        txtNumPort = new JTextField(String.valueOf(Config.PORT_SERVEUR),5);


        this.add(p1.add(lAdrServeur));
        //panel.add(lAdrServeur);
        this.add(p1.add(txtAdrServeur));

        this.add(p2.add(lNumPort));
        this.add(p2.add(txtNumPort));
        //panel.add(lNumPort);
        //panel.add(txtNumPort);

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

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
        JPanel panel = new JPanel(new GridLayout(4,1));
        JLabel lAdrServeur = new JLabel("Adresse IP : ");
        JLabel lNumPort = new JLabel("Port : ");

        txtAdrServeur = new JTextField(Config.ADRESSE_SERVEUR,15);
        txtNumPort = new JTextField(String.valueOf(Config.PORT_SERVEUR),5);

        panel.add(lAdrServeur,txtAdrServeur);
        //panel.add(lAdrServeur);
        //panel.add(txtAdrServeur);
        panel.add(lNumPort,txtNumPort);
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

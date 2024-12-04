package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauDepot extends JPanel {
    private JPanel panneauOp;
    private JTextField jTextField;
    //JButton deposer;
    //JButton annuler;


    public PanneauDepot(){

        this.setLayout(new BorderLayout());

        panneauOp = new JPanel();
        panneauOp.setLayout(new BorderLayout());
        panneauOp.setBackground(Color.white);

        JLabel jmontant = new JLabel("Entrez un montant :");
        jTextField = new JTextField(7);

        panneauOp.add(jmontant);
        panneauOp.add(jTextField);
        this.add(panneauOp);

    }

    public void montrerPanneauDepot(){panneauOp.setVisible(true);}
}

package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauDepot extends JPanel {
    private JPanel panneauOp;
    private JTextField jTextField;
    //JButton deposer;
    //JButton annuler;


    public PanneauDepot(){

        panneauOp = new JPanel(new GridLayout(1,2,5,5));
        //panneauOp.setBackground(Color.white);

        JLabel jmontant = new JLabel("Entrez un montant à déposer :");
        jTextField = new JTextField(10);

        panneauOp.add(jmontant);
        panneauOp.add(jTextField);
        this.add(panneauOp, BorderLayout.CENTER);


    }

    public String getmontant(){
        return jTextField.getText();
    }


}

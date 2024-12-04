package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauTransfert extends JPanel {

    private JTextField numeroCompte;
    private JTextField montant;

    public PanneauTransfert(){

        JPanel panneauTransfert = new JPanel(new GridLayout(2,2,5,10));
        numeroCompte = new JTextField(10);
        montant = new JTextField(10);
        JLabel titreMontant = new JLabel("Entrez le montant à transférer: ");
        JLabel titreNumCompte = new JLabel("Entrez le numéro du compte: ");

        panneauTransfert.add(titreNumCompte);
        panneauTransfert.add(numeroCompte);
        panneauTransfert.add(titreMontant);
        panneauTransfert.add(montant);

        this.add(panneauTransfert);

    }



    public String getNumeroCompte(){
        return numeroCompte.getText();
    }
    public String getMontant(){
        return montant.getText();
    }
}

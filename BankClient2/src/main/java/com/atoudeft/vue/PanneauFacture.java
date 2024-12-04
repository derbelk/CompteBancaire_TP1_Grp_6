package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauFacture extends JPanel{
    private JTextField montant, numeroFacture, description;
    private JComboBox<String> boxMois, boxAnnee;


    public PanneauFacture(){

        this.setLayout(new BorderLayout(5,10));
        JPanel p1= new JPanel(new GridLayout(3,2,5,10));
        JPanel p2= new JPanel(new GridLayout(1,4,5,10));
        JLabel l1 = new JLabel("Montant de la facture: ");
        JLabel l2 = new JLabel("Numéro de la facture: ");
        JLabel l3 = new JLabel("Description");
        JLabel l4 = new JLabel("Mois: ");
        JLabel l5 = new JLabel("Annee: ");
        montant = new JTextField();
        numeroFacture = new JTextField();
        description = new JTextField();

        String[] mois = {"Jan","Fev", "Mar","Avr", "Mai","Jun", "Jui", "Sep", "Oct", "Nov","Dec"};

        boxMois = new JComboBox<>(mois);

        String[] annee = new String[50];

        for (int i = 0; i < annee.length; i++) {
            annee[i]= String.valueOf(2024 + i);
        }

        boxAnnee = new JComboBox<>(annee);

        p2.add(l4);
        p2.add(boxMois);
        p2.add(l5);
        p2.add(boxAnnee);

        p1.add(l1);
        p1.add(montant);
        p1.add(l2);
        p1.add(numeroFacture);
        p1.add(l3);
        p1.add(description);

        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.SOUTH);
    }

    public String getMontant() {
        return montant.getText();
    }

    public String getNumeroFacture() {
        return numeroFacture.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public  Object getMois(){
        return boxMois.getSelectedItem();
    }

    public Object getAnnee(){
        return boxAnnee.getSelectedItem();
    }
}

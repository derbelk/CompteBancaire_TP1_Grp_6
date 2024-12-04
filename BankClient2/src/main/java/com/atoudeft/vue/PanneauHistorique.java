package com.atoudeft.vue;

import javax.swing.*;

public class PanneauHistorique extends JPanel {
    private JTextArea jTextArea;

    public PanneauHistorique(){

        JPanel panneau = new JPanel();
        jTextArea = new JTextArea();

        jTextArea.setEditable(true);

        panneau.add(jTextArea);

        this.add(panneau);
    }

    public void setjTextArea(String s){
        jTextArea.setText(s);
    }
}

package com.atoudeft.vue;

import javax.swing.*;

public class PanneauHistorique extends JPanel {
    private JTextArea jTextArea;

    public PanneauHistorique(){
        jTextArea = new JTextArea();

        jTextArea.setEditable(false);

        this.add(jTextArea);
    }
}

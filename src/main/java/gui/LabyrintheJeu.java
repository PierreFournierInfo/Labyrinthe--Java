package main.java.gui;

import main.java.model.Labyrinthe;

import javax.swing.*;

public class LabyrintheJeu extends JFrame {

    private LabyrinthePanel labyrinthePanel;

    public LabyrintheJeu(){
        this.setTitle("Labyrinthe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.labyrinthePanel = new LabyrinthePanel();
        this.setSize(1024, 576);
        this.add(labyrinthePanel);

        this.setLocationRelativeTo(null);
        labyrinthePanel.startGameThread();
    }



}

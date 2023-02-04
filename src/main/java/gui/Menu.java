package main.java.gui;

import main.java.controller.Menu_Controller;
import main.java.model.FondEcran;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    private JButton labyrinthe;
    private Menu_Controller controller;
    private FondEcran fond;

    public Menu() {

        this.setTitle("Labyrinthe");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(null);

        this.labyrinthe = new JButton("Labyrinthe");
        this.labyrinthe.setBounds(700,400,300,100);
        //this.fond=new FondEcran("ressources/Labyrinthe.jpg");
        //this.add(this.fond);
        this.fond=new FondEcran("src/main/java/gui/Labyrinthe.jpg");
       
		
		this.add(fond);
        this.add(this.labyrinthe);

        this.controller = new Menu_Controller(this);
        this.controller.launchLabyrinthe();

		this.setVisible(true);

    }

    public JFrame getMenu(){
        return this;
    }

    public JButton getLabyrinthe() {
        return labyrinthe;
    }
}

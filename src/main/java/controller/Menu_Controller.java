package main.java.controller;

import java.awt.BorderLayout;

import main.java.gui.Labyrinthe.Labyrinthe_Launcher;
import main.java.gui.Menu.Menu;

public class Menu_Controller {

    private Menu menu;
    private Labyrinthe_Launcher labyrintheJeu;

    public Menu_Controller(Menu menu){
        this.menu = menu;
        this.launchLabyrinthe();
        this.launchLium();
        this.lauchQuitter();
    }

    public void launchLabyrinthe(){
        menu.getLabyrinthe().addActionListener(e -> { 
                menu.getMenu().setVisible(false);
                this.labyrintheJeu = new Labyrinthe_Launcher(this.menu, false);
                labyrintheJeu.setVisible(true);
        });
    }

    public void launchLium(){
        menu.getLium().addActionListener(e -> {
                menu.getMenu().setVisible(false);
                this.labyrintheJeu = new Labyrinthe_Launcher(this.menu ,true);
                labyrintheJeu.setVisible(true);
        });
    }

    public void lauchQuitter(){
        menu.getQuitter().addActionListener(e -> {
            menu.getMenu().dispose();
        });
    }

}

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
        this.lauchRetour();
    }

    public void launchLabyrinthe(){
        menu.getLabyrinthe().addActionListener(e -> { 
                menu.getMenu().setVisible(false);
                this.labyrintheJeu = new Labyrinthe_Launcher(this.menu, false);
                labyrintheJeu.setVisible(true);
                initRetour();
        });
    }
    public void launchLium(){
        menu.getLium().addActionListener(e -> {
                menu.getMenu().setVisible(false);
                this.labyrintheJeu = new Labyrinthe_Launcher(this.menu ,true);
                labyrintheJeu.setVisible(true);
                initRetour();
        });
    }
    public void lauchQuitter(){
        menu.getQuitter().addActionListener(e -> {
            menu.getMenu().dispose();
        });
    }
    public void lauchRetour(){
        menu.getRetour().addActionListener(e -> {
            this.labyrintheJeu.dispose();
            menu.getMenu().setVisible(true);
        });
    }

    public void initRetour(){
        this.labyrintheJeu.getTopPanel().add(menu.getRetour(), BorderLayout.WEST);
        menu.getRetour().setVisible(true);
    }

}

package main.java.controller;

import main.java.gui.Labyrinthe.Labyrinthe_Launcher;
import main.java.gui.Menu.Menu;

public class Menu_Controller {

    Menu menu;
    Labyrinthe_Launcher labyrintheJeu;

    public Menu_Controller(Menu menu){
        this.menu = menu;
        
    }

    public void launchLabyrinthe(){
        menu.getLabyrinthe().addActionListener(e -> { 
                menu.getMenu().setVisible(false);
                this.labyrintheJeu = new Labyrinthe_Launcher(false);
                labyrintheJeu.setVisible(true);
        });
    }
    public void launchLium(){
        menu.getLium().addActionListener(e -> {
                menu.getMenu().setVisible(false);
                this.labyrintheJeu = new Labyrinthe_Launcher(true);
                labyrintheJeu.setVisible(true);
        });
    }

}

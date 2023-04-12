package main.java.controller;

import main.java.gui.Labyrinthe.Labyrinthe_Launcher;
import main.java.gui.Menu.Menu;

public class Launcher_Controller {
    Menu menu;
    Labyrinthe_Launcher labyrintheJeu;

    public Launcher_Controller(Labyrinthe_Launcher labyrinthe){
        this.labyrintheJeu = labyrinthe;
        
    }

    public void launchMenu(){
        labyrintheJeu.getMenu().addActionListener(e -> { 
                this.menu = new Menu();
                menu.setVisible(true);
                labyrintheJeu.getLabyrinthe().setVisible(false);
        });
    }

    
}

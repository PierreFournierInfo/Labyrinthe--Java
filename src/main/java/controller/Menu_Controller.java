package main.java.controller;

import main.java.gui.Labyrinthe.Labyrinthe_Launcher;
import main.java.gui.Menu.Menu;

public class Menu_Controller {

    Menu menu;
    Labyrinthe_Launcher labyrintheJeu;

    public Menu_Controller(Menu menu){
        this.menu = menu;
        this.labyrintheJeu = new Labyrinthe_Launcher();
    }

    public void launchLabyrinthe(){
        menu.getLabyrinthe().addActionListener(e -> {
                menu.getMenu().setVisible(false);
                labyrintheJeu.setVisible(true);
        });
    }

}

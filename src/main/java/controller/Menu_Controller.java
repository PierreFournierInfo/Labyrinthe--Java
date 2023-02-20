package main.java.controller;

import main.java.gui.LabyrintheJeu;
import main.java.gui.Menu;

public class Menu_Controller {

    Menu menu;
    LabyrintheJeu labyrintheJeu;

    public Menu_Controller(Menu menu){
        this.menu = menu;
        this.labyrintheJeu = new LabyrintheJeu();
    }

    public void launchLabyrinthe(){
        menu.getLabyrinthe().addActionListener(e -> {
                menu.getMenu().setVisible(false);
                labyrintheJeu.setVisible(true);
        });
    }

}

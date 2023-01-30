package main.java.controller;

import main.java.gui.Labyrinthe_GUI;
import main.java.gui.Menu;

public class Menu_Controller {

    Menu menu;

    public Menu_Controller(Menu menu){
        this.menu = menu;
    }

    public void launchLabyrinthe(){
        menu.getLabyrinthe().addActionListener(e -> {
                menu.getMenu().setVisible(false);
                Labyrinthe_GUI laby = new Labyrinthe_GUI();
                laby.setVisible(true);
        });
    }

}

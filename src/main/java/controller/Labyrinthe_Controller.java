package main.java.controller;

import main.java.gui.Labyrinthe.Labyrinthe_Launcher;
import main.java.gui.Menu.Menu;

public class Labyrinthe_Controller {

    private Labyrinthe_Launcher labyrinthe_launcher;
    private Menu menu;

    public Labyrinthe_Controller(Labyrinthe_Launcher labyrinthe_launcher, Menu menu){
        this.labyrinthe_launcher = labyrinthe_launcher;
        this.menu = menu;
    }

    public void initRetour(){
        this.labyrinthe_launcher.getRetour().addActionListener(e -> {
            labyrinthe_launcher.getLabyrinthe_Panel().stopGameThread();
            labyrinthe_launcher.dispose();
            labyrinthe_launcher.setVisible(false);
            menu.setVisible(true);
        });
    }

}

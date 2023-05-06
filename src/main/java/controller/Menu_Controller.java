package main.java.controller;

import main.java.gui.Main;
import main.java.gui.Labyrinthe.Labyrinthe_Launcher;
import main.java.gui.Menu.Menu;

public class Menu_Controller {

    private Menu menu;
    private Labyrinthe_Launcher labyrintheJeu;

    public Menu_Controller(Menu menu) {
        this.menu = menu;
        launchLabyrinthe();
        launchLium();
        lauchQuitter();
    }

    public void launchLabyrinthe() {
        menu.getLabyrinthe().addActionListener(e -> {
            menu.getMenu().setVisible(false);
            labyrintheJeu = new Labyrinthe_Launcher(this.menu, false);
            labyrintheJeu.setVisible(true);
        });
    }

    public void launchLium() {
        menu.getLium().addActionListener(e -> {
            menu.getMenu().setVisible(false);
            labyrintheJeu = new Labyrinthe_Launcher(this.menu, true);
            labyrintheJeu.setVisible(true);
        });
    }

    public void lauchQuitter() {
        menu.getQuitter().addActionListener(e -> {
            menu.getMenu().dispose();
            Main.reset();
        });
    }

}

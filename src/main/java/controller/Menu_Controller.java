package main.java.controller;

import main.java.gui.Labyrinthe.Labyrinthe_Launcher;
import main.java.gui.Menu.Menu;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Menu_Controller {

    private Menu menu;
    private Labyrinthe_Launcher labyrintheJeu;

    public Menu_Controller(Menu menu){
        this.menu = menu;
        this.launchLabyrinthe();
        this.launchLium();
        this.getNbPlayer();
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

    public void getNbPlayer(){
        menu.getText().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                menu.getText().setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

        menu.getSubmit().addActionListener(e -> {
            menu.setNbPayer(Integer.parseInt(menu.getText().getText()));
            System.out.println(menu.getNbPayer());
        });

    }

}

package main.java.gui;

import main.java.gui.Menu.Menu;

public class Main {

    private Menu menu;

    public Main() {
        this.menu = new Menu();
        this.menu.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }


}
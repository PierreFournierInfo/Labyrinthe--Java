package main.java.gui;

import main.java.controller.Menu_Controller;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    private JButton labyrinthe;
    private Menu_Controller controller;

    public Menu() {

        this.setTitle("Labyrinthe");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.labyrinthe = new JButton("Labyrinthe");
        JLabel l1 = new JLabel();
        l1.setPreferredSize(new Dimension(0,325));
        JLabel l2 = new JLabel();
        l2.setPreferredSize(new Dimension(0,325));
        JLabel l3 = new JLabel();
        l3.setPreferredSize(new Dimension(500,0));
        JLabel l4 = new JLabel();
        l4.setPreferredSize(new Dimension(500,0));
        this.add(l1, BorderLayout.SOUTH);
        this.add(l2, BorderLayout.NORTH);
        this.add(this.labyrinthe, BorderLayout.CENTER);
        this.add(l3, BorderLayout.WEST);
        this.add(l4, BorderLayout.EAST);

        this.controller = new Menu_Controller(this);
        this.controller.launchLabyrinthe();

    }

    public JFrame getMenu(){
        return this;
    }

    public JButton getLabyrinthe() {
        return labyrinthe;
    }
}

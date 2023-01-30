package main.java.gui;

import main.java.model.Labyrinthe;

import javax.swing.*;
import java.awt.*;

public class Labyrinthe_GUI extends JFrame {

    Labyrinthe labyrinthe;
    Terrain terrain;

    public Labyrinthe_GUI(){
        this.setTitle("Labyrinthe");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        this.labyrinthe = new Labyrinthe();

        this.terrain = new Terrain();
        this.add(this.terrain);
    }

    public class Terrain extends JPanel{

        public Terrain(){
            this.setLayout(new GridLayout(labyrinthe.getX(),labyrinthe.getY()));
            remplir();
        }

        public void remplir(){
            for(int i=0;i<labyrinthe.getX();i++){
                for(int j=0;j<labyrinthe.getY();j++){
                    JPanel tmp = new JPanel();
                    switch (labyrinthe.getVal(i,j)){
                        case 0 : tmp.setBackground(Color.black); break;
                        case 1 : tmp.setBackground(Color.white); break;
                        case 2 : tmp.setBackground(Color.gray); break;
                    }
                    this.add(tmp);
                }
            }
        }


    }

}

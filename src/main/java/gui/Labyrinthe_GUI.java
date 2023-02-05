package main.java.gui;

import main.java.model.Labyrinthe;
import javax.swing.*;
import java.awt.*;


public class Labyrinthe_GUI extends JFrame {

    Labyrinthe labyrinthe;
    Terrain terrain;

    public Labyrinthe_GUI(){
        this.setTitle("Labyrinthe");
        this.setSize(1366, 768);
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

        public ImageIcon resizeImg(String paths){
            ImageIcon imageIcon = new ImageIcon(paths);
            Image img = imageIcon.getImage();
            Image imageScal = img.getScaledInstance(40,40,Image.SCALE_DEFAULT);
            return new ImageIcon(imageScal);
        }

        public JLabel getTree(int i, int j){
            ImageIcon arbre1 = resizeImg("src/resources/Arbre1.png");
            ImageIcon arbre2 = resizeImg("src/resources/Arbre2.png");
            ImageIcon arbre3 = resizeImg("src/resources/Arbre3.png");

            JLabel image = new JLabel();

            if(i < labyrinthe.getX()-2){
                if(labyrinthe.getVal(i+1,j) == 0 && labyrinthe.getVal(i+2,j) == 0){
                    image.setIcon(arbre3);
                }else if(labyrinthe.getVal(i+1,j) == 1){
                    image.setIcon(arbre1);
                }else if(labyrinthe.getVal(i+1,j) == 0){
                    image.setIcon(arbre2);
                }
            }else{
                image.setIcon(arbre3);
            }
            return image;
        }

        public JLabel getFloor(){
            JLabel image = new JLabel();
            ImageIcon sol = resizeImg("src/resources/Galerie.png");
            image.setIcon(sol);
            return image;
        }

        public void remplir(){

            for(int i=0;i<labyrinthe.getX();i++){
                for(int j=0;j<labyrinthe.getY();j++){
                    JPanel tmp = new JPanel();
                    tmp.setLayout(new BorderLayout());
                    switch (labyrinthe.getVal(i,j)){
                        case 0 : tmp.add(getTree(i,j)); break;
                        case 1 : tmp.add(getFloor()); break;
                    }
                    this.add(tmp);
                }
            }
        }


    }

}

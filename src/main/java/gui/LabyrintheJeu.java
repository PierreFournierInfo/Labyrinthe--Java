package main.java.gui;

import main.java.model.Labyrinthe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LabyrintheJeu extends JFrame {

    private LabyrinthePanel labyrinthePanel;

    public LabyrintheJeu(){
        this.setTitle("Labyrinthe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int largeur=(int)dimension.getWidth();
        int hauteur=(int)dimension.getHeight();
        this.setSize(largeur,hauteur);

        this.setLayout(new BorderLayout());

        this.labyrinthePanel = new LabyrinthePanel();
        this.add(labyrinthePanel, BorderLayout.CENTER);

        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("src/resources/mer.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(largeur - ((labyrinthePanel.screenWidth * labyrinthePanel.tileSize)/2 + largeur/2), 0));
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(largeur - ((labyrinthePanel.screenWidth * labyrinthePanel.tileSize)/2 + largeur/2), 0));
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(0, hauteur - ((labyrinthePanel.screenHeight * labyrinthePanel.tileSize)/2 + hauteur/2)));
        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(0, hauteur - ((labyrinthePanel.screenHeight * labyrinthePanel.tileSize)/2 + hauteur/2)));
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        labyrinthePanel.startGameThread();
    }



}

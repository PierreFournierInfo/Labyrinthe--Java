package main.java.gui.Labyrinthe;

import javax.swing.*;
import java.awt.*;

public class Labyrinthe_Launcher extends JFrame {

    private Labyrinthe_Panel labyrinthePanel;

    public Labyrinthe_Launcher(){
        this.setTitle("Labyrinthe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int largeur=(int)dimension.getWidth();
        int hauteur=(int)dimension.getHeight();
        this.setSize(largeur,hauteur);

        this.setLayout(new BorderLayout());

        this.labyrinthePanel = new Labyrinthe_Panel();
        this.add(labyrinthePanel, BorderLayout.CENTER);

        JPanel left = new JPanel();
        left.setBackground(new Color(239, 212, 106));
        left.setPreferredSize(new Dimension(largeur - ((labyrinthePanel.screenWidth * labyrinthePanel.tileSize)/2 + largeur/2), 0));
        JPanel right = new JPanel();
        right.setBackground(new Color(239, 212, 106));
        right.setPreferredSize(new Dimension(largeur - ((labyrinthePanel.screenWidth * labyrinthePanel.tileSize)/2 + largeur/2), 0));
        JPanel top = new JPanel();
        top.setBackground(new Color(239, 212, 106));
        top.setPreferredSize(new Dimension(0, hauteur - ((labyrinthePanel.screenHeight * labyrinthePanel.tileSize)/2 + hauteur/2)));
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(239, 212, 106));
        bottom.setPreferredSize(new Dimension(0, hauteur - ((labyrinthePanel.screenHeight * labyrinthePanel.tileSize)/2 + hauteur/2)));
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        labyrinthePanel.startGameThread();
    }



}

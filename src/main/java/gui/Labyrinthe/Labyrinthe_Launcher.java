package main.java.gui.Labyrinthe;

import javax.swing.*;
import java.awt.*;

public class Labyrinthe_Launcher extends JFrame {

    private Labyrinthe_Panel labyrinthePanel;

    public Labyrinthe_Launcher(){
        this.setTitle("Labyrinthe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Dimension de la fenêtre
        
        Insets scnMax=Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int largeur = (int)dimension.getWidth();
        int hauteur = (int)dimension.getHeight();
        int MargeX=(int)largeur-scnMax.left-scnMax.right;
		int MargeY=(int)hauteur-scnMax.bottom-scnMax.top;


        this.setSize(MargeX, MargeY);

        this.setLayout(new BorderLayout());

        // Ajout du labyrinthe
        this.labyrinthePanel = new Labyrinthe_Panel();
        this.add(labyrinthePanel, BorderLayout.CENTER);

        // Calcule des dimensions des bords
        int width = MargeX - ((labyrinthePanel.getScreenWidth() * labyrinthePanel.getTileSize())/2 + MargeX/2);
        int height = MargeY - ((labyrinthePanel.getScreenHeight() * labyrinthePanel.getTileSize())/2 + MargeY/2);

        // Ajout des bords de même couleur que les feuilles d'arbres
        JPanel left = new JPanel();
        left.setBackground(new Color(239, 212, 106));
        left.setPreferredSize(new Dimension(width, 0));
        JPanel right = new JPanel();
        right.setBackground(new Color(239, 212, 106));
        right.setPreferredSize(new Dimension(width, 0));
        JPanel top = new JPanel();
        top.setBackground(new Color(239, 212, 106));
        top.setPreferredSize(new Dimension(0, height));
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(239, 212, 106));
        bottom.setPreferredSize(new Dimension(0, height));
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);

        labyrinthePanel.startGameThread();
    }



}

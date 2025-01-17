package main.java.gui.Menu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import main.java.controller.Menu_Controller;

public class Menu extends JFrame {

    private JButton Lium;
    private JButton labyrinthe;
    private JButton quitter;
    private JPanel fond;
    private double largeur;
    private double hauteur;

    public Menu() {

        this.setTitle("Labyrinthe");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(this.getParent());
        // System.out.println(Toolkit.getScreenInsets(GraphicsConfiguration gc));
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        // System.out.println(scnMax.left);

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        largeur = (int) dimension.getWidth();
        hauteur = (int) dimension.getHeight();
        this.setSize((int) this.largeur, (int) hauteur);

        this.setResizable(true);
        this.setLayout(new GridLayout());

        Icon boutonLabyrinthe = new ImageIcon("src/resources/Menu/boutonLabyrintheV1.png");
        Icon boutonLium = new ImageIcon("src/resources/Menu/boutonLiumV1.png");
        Icon quitterTmp = new ImageIcon("src/resources/Menu/quitter.png");

        this.labyrinthe = new JButton(boutonLabyrinthe);
        this.Lium = new JButton(boutonLium);
        this.quitter = new JButton(quitterTmp);

        this.fond = new FondEcran("src/resources/Menu/fondMenu.png");
        this.fond.setLayout(null);
        int MargeX = (int) largeur - scnMax.left - scnMax.right;
        int MargeY = (int) hauteur - scnMax.bottom - scnMax.top;

        this.fond.add(this.labyrinthe);
        this.fond.add(this.Lium);
        this.fond.add(this.quitter);
        this.add(this.fond);

        int butLargeur = 255;
        int butHauteur = 81;

        this.labyrinthe.setBounds(MargeX / 2 - butLargeur / 2, MargeY / 2 - MargeY / 12 - butHauteur / 2, butLargeur,
                butHauteur);
        this.Lium.setBounds(MargeX / 2 - butLargeur / 2, MargeY / 2 - MargeY / 12 - butHauteur / 2 - 100, butLargeur,
                butHauteur);
        this.quitter.setBounds(MargeX / 2 - butLargeur / 2, MargeY / 2 - MargeY / 12 - butHauteur / 2 + 100, butLargeur,
                butHauteur);
        // labyrinthe.setBounds(MargeX-scnMax.left,MargeY-MargeY/4,(int)largeur-2*MargeX,(int)hauteur-2*MargeY);

        Lium.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        labyrinthe.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        quitter.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");

        new Menu_Controller(this);

        // this.setVisible(true);
    }

    public JFrame getMenu() {
        return this;
    }

    public JButton getLium() {
        return Lium;
    }

    public JButton getLabyrinthe() {
        return labyrinthe;
    }

    public JButton getQuitter() {
        return quitter;
    }
}

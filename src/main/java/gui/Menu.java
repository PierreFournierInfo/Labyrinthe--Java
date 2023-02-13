package main.java.gui;

import main.java.controller.Menu_Controller;
import main.java.model.FondEcran;

import javax.swing.*;
import javax.transaction.xa.Xid;

import java.awt.*;

public class Menu extends JFrame {

    private JButton labyrinthe;
    private Menu_Controller controller;
    private JPanel fond;
	private double scaleX;
	private double scaleY;
	private double largeur=1280;
	private double hauteur=720;

    public Menu() {

        this.setTitle("Labyrinthe");
		
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		largeur=(int)dimension.getWidth();
		hauteur=(int)dimension.getHeight();
		this.setSize((int)this.largeur,(int)hauteur);
		scaleX=dimension.getWidth()/this.getSize().getWidth();
		
        this.setResizable(true);
        this.setLayout(new GridLayout());

        Icon boutonJouer=new ImageIcon("src/resources/button(1).png");
        
        this.labyrinthe = new JButton(boutonJouer);
        fond=new FondEcran("src/resources/fondMenu2.png");
		this.fond.setLayout(null);
        int MargeX=(int)largeur/2-105;
		int MargeY=(int)hauteur/2-40;
		
        this.add(this.fond);
        this.fond.add(this.labyrinthe);
		labyrinthe.setBounds(MargeX,MargeY-MargeY/4,(int)largeur-2*MargeX,(int)hauteur-2*MargeY);
        this.controller = new Menu_Controller(this);
        this.controller.launchLabyrinthe();
		//this.setVisible(true);

    }

    public JFrame getMenu(){
        return this;
    }

    public JButton getLabyrinthe() {
        return labyrinthe;
    }
   

}

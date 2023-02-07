package main.java.gui;

import main.java.controller.Menu_Controller;
import main.java.model.FondEcran;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    private JButton labyrinthe;
    private Menu_Controller controller;
    private JPanel fond;

    public Menu() {

        this.setTitle("Labyrinthe");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(new GridLayout());

        this.labyrinthe = new JButton("Labyrinthe");
        //this.fond=new FondEcran("ressources/Labyrinthe.jpg");
        //this.add(this.fond);
        fond=new FondEcran("src/resources/Labyrinthe.jpg");
       
		
		this.add(this.fond);
        this.fond.add(this.labyrinthe);
        this.fond.setLayout(null);

        this.controller = new Menu_Controller(this);
        this.controller.launchLabyrinthe();
        this.labyrinthe.setBounds(750,400,300,100);

		this.setVisible(true);

    }

    public JFrame getMenu(){
        return this;
    }

    public JButton getLabyrinthe() {
        return labyrinthe;
    }
    /* 

    JComboBox nombreDeJoueurs;
	JComboBox nombreDeJoueursCar;
	String choix;
	String choixCar;
	
	
	public Menu(){
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(1,2));
		JPanel menuGauche=new FondEcran("src/resources/Labyrinthe.jpg");
		JPanel menuDroit=new FondEcran("Ressources/Carcassonne2.png");
		
		this.add(menuGauche);
		this.add(menuDroit);
		
		menuGauche.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		menuDroit.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		menuGauche.setLayout(null);
		menuDroit.setLayout(null);
		
		JButton Domino=new JButton("DOMINO");
		JButton Carcassonne=new JButton("CARCASSONNE");
		
		String[] tab= {"   Choisir:","1 joueur","2 joueurs","3 joueurs","4 joueurs","5 joueurs"};
		nombreDeJoueurs = new JComboBox(tab);
		
		nombreDeJoueursCar = new JComboBox(tab);
		
		
	
		Domino.setBounds(400,400,200,100);
		Domino.setFont(new Font("MV Boli",Font.PLAIN,30));
		Carcassonne.setBounds(400,400,200,100);
		Carcassonne.setFont(new Font("MV Boli",Font.PLAIN,20));
		nombreDeJoueurs.setBounds(400,300,200,100);
		nombreDeJoueurs.setFont(new Font("MV Boli",Font.PLAIN,30));
		nombreDeJoueurs.setFont(new Font("MV Boli",Font.PLAIN,30));
		nombreDeJoueursCar.setBounds(400,300,200,100);
		nombreDeJoueursCar.setFont(new Font("MV Boli",Font.PLAIN,30));
		nombreDeJoueursCar.setFont(new Font("MV Boli",Font.PLAIN,30));
		
		
		menuGauche.add(Domino);
		menuGauche.add(nombreDeJoueurs);
		
		menuDroit.add(Carcassonne);
		menuDroit.add(nombreDeJoueursCar);
		
		
		this.setVisible(true);
    }
    */

}

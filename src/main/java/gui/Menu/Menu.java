package main.java.gui.Menu;

import main.java.controller.Menu_Controller;

import javax.swing.*;

import java.awt.*;

public class Menu extends JFrame {

    private JButton Lium;
    private JButton labyrinthe;
    private Menu_Controller controller;
    private JPanel fond;
	private double scaleX;
	private double scaleY;
	private double largeur=1280;
	private double hauteur=720;
    private JTextField text;
    private JButton submit;
    private int NbPayer;

    public Menu() {

        this.setTitle("Labyrinthe");
		
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(this.getParent());
        //System.out.println(Toolkit.getScreenInsets(GraphicsConfiguration gc));
        Insets scnMax=Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        //System.out.println(scnMax.left);

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		largeur=(int)dimension.getWidth();
		hauteur=(int)dimension.getHeight();
		this.setSize((int)this.largeur,(int)hauteur);
		scaleX=dimension.getWidth()/this.getSize().getWidth();
		
        this.setResizable(true);
        this.setLayout(new GridLayout());

        Icon boutonLabyrinthe=new ImageIcon("src/resources/Menu/boutonLabyrintheV1.png");
        Icon boutonLium=new ImageIcon("src/resources/Menu/boutonLiumV1.png");


        this.labyrinthe = new JButton(boutonLabyrinthe);
        this.Lium = new JButton(boutonLium);
        this.text = new JTextField("Nombre de Joueur");
        this.text.setHorizontalAlignment(JTextField.CENTER);
        this.submit = new JButton("Submit");


        this.fond = new FondEcran("src/resources/Menu/fondMenu.png");
		this.fond.setLayout(null);
        int MargeX=(int)largeur-scnMax.left-scnMax.right;
		int MargeY=(int)hauteur-scnMax.bottom-scnMax.top;

        this.fond.add(this.labyrinthe);
        this.fond.add(this.Lium);
        this.fond.add(this.text);
        this.fond.add(this.submit);
        this.add(this.fond);

        int butLargeur=255;
        int butHauteur=81;

        this.labyrinthe.setBounds(MargeX/2-butLargeur/2,MargeY/2-MargeY/12-butHauteur/2,butLargeur,butHauteur);
        this.Lium.setBounds(MargeX/2-butLargeur/2,MargeY/2-MargeY/12-butHauteur/2-100,butLargeur,butHauteur);
        this.text.setBounds(MargeX/2-butLargeur/2,MargeY/2-MargeY/12-butHauteur/2+100,butLargeur,butHauteur);
        this.submit.setBounds(MargeX/2-(butLargeur-50)/2,MargeY/2-MargeY/12-butHauteur/2+200,butLargeur-50,butHauteur-50);
		//labyrinthe.setBounds(MargeX-scnMax.left,MargeY-MargeY/4,(int)largeur-2*MargeX,(int)hauteur-2*MargeY);
        this.controller = new Menu_Controller(this);
        this.controller.launchLabyrinthe();
        this.controller.launchLium();
        this.controller.getNbPlayer();

		//this.setVisible(true);
    }

    public JFrame getMenu(){
        return this;
    }

    public JButton getLium() {
        return Lium;
    }
    public JButton getLabyrinthe() {
        return labyrinthe;
    }

    public JTextField getText() {
        return text;
    }

    public JButton getSubmit() {
        return submit;
    }

    public void setNbPayer(int nbPayer) {
        NbPayer = nbPayer;
    }

    public int getNbPayer() {
        return NbPayer;
    }
}

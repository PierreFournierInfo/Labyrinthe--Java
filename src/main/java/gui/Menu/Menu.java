package main.java.gui.Menu;

import main.java.controller.Menu_Controller;

import javax.swing.*;

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

        Icon boutonJouer=new ImageIcon("src/resources/Menu/buttonPlay.png");
        
        this.labyrinthe = new JButton(boutonJouer);
        fond=new FondEcran("src/resources/Menu/fondMenu.png");
		this.fond.setLayout(null);
        
        int MargeX=(int)largeur-scnMax.left-scnMax.right;
		int MargeY=(int)hauteur-scnMax.bottom-scnMax.top;
		
        this.add(this.fond);
        this.fond.add(this.labyrinthe);
        int butLargeur=208;
        int butHauteur=82;
        labyrinthe.setBounds(MargeX/2-butLargeur/2,MargeY/2-MargeY/12-butHauteur/2,butLargeur,butHauteur);
		//labyrinthe.setBounds(MargeX-scnMax.left,MargeY-MargeY/4,(int)largeur-2*MargeX,(int)hauteur-2*MargeY);
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

package main.java.gui.Labyrinthe;

import javax.swing.*;
import java.awt.*;
import main.java.controller.Launcher_Controller;

   
        

public class Labyrinthe_Launcher extends JFrame {

    private Labyrinthe_Panel labyrinthePanel;
    private boolean modeJeu;
    private JButton boutonRetour;
    private Launcher_Controller launcherController;

    public Labyrinthe_Launcher(boolean b){
        launcherController=new Launcher_Controller(this);
        modeJeu=b;
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
        this.labyrinthePanel = new Labyrinthe_Panel(modeJeu);
        this.add(labyrinthePanel);

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
        top.setLayout(null);
        top.setPreferredSize(new Dimension(0, height));

        //AJOUT BOUTON Retour
        Icon boutonR=new ImageIcon("src/resources/Menu/boutonRetour.png");
        this.boutonRetour = new JButton(boutonR);
        boutonRetour.setBounds(0,0,137,57);
        top.add(boutonRetour);

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(239, 212, 106));
        bottom.setPreferredSize(new Dimension(0, height));
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);


        this.setLocationRelativeTo(null);

        labyrinthePanel.startGameThread();
        this.launcherController.launchMenu();
    }

    public JFrame getLabyrinthe(){
        return this;
    }

    public JButton getMenu() {
        return boutonRetour;
    }

    public Launcher_Controller getTileController() {
        return launcherController;
    }



}

package main.java.gui.Labyrinthe;

import main.java.gui.Menu.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Labyrinthe_Launcher extends JFrame {

    private Labyrinthe_Panel labyrinthePanel;
    private JPanel left, right, top, bottom;
    private JLabel picLabel;
    private boolean modeJeu;
    private Menu menu;
    private JLabel timer;
    private JLabel nbStep;
    private JLabel direction;
    private JLabel chargement;
    private JButton retour;

    public Labyrinthe_Launcher(Menu menu, boolean b){
        this.menu = menu;
        this.modeJeu = b;
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
        this.labyrinthePanel = new Labyrinthe_Panel(modeJeu, this);
        this.add(labyrinthePanel, BorderLayout.CENTER);

        // Calcule des dimensions des bords
        int width = MargeX - ((labyrinthePanel.getScreenWidth() * labyrinthePanel.getTileSize())/2 + MargeX/2);
        int height = MargeY - ((labyrinthePanel.getScreenHeight() * labyrinthePanel.getTileSize())/2 + MargeY/2);

        // Ajout des bords de même couleur que les feuilles d'arbres
        this.left = new JPanel();
        this.left.setLayout(new BorderLayout());
        this.left.setBackground(new Color(239, 212, 106));
        this.left.setPreferredSize(new Dimension(width, 0));
        this.right = new JPanel();
        this.right.setLayout(new BorderLayout());
        this.right.setBackground(new Color(239, 212, 106));
        this.right.setPreferredSize(new Dimension(width, 0));
        this.top = new JPanel();
        this.top.setBackground(new Color(239, 212, 106));
        this.top.setPreferredSize(new Dimension(0, height));
        this.bottom = new JPanel();
        this.bottom.setBackground(new Color(239, 212, 106));
        this.bottom.setPreferredSize(new Dimension(0, height));
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);

        JLabel rule = new JLabel("<html>Règles du jeux:<br>"+
        "But : aller d'un portail à l'autre du labyrinthe<br>"+
        "Espace : ouvrir fermer le micro<br>"+
        "J : Lancement de Whisper<br>"+
        "K : Lancement de LIUM<br>"+
        "L : Faire avancer le personnage du nombre de pas enregistré<br>"+
        "U : Active les touches ZQSD (mode débug)<br>"+
        "I : Ajoute un pas dans le compteur générale (mode débug)<br>"+
        "O : Permet de réutiliser la valeur qui a été détérminer par LIUM<br>"+
        "Etape 1 : Pour donner la direction, lancer le micro,"+
        " dite une action avec le mot (gauche, droite, haut ou bas)"+
        " compris dedans puis couper le micro puis lancer J<br>"+
        "Etape 2 : Pour obtenir le nombre de pas à effectuer,"+
        " parler (de préfèrence) 20 secondes par personnes puis lancer K<br>"+
        "Etape 2Bis : Il arrive que LIUM commet des erreur, "+
        "dans ce cas refaite étape 2, puis faite avancer le personnage avec L"+
        "Etape 3 : Appuyer sur L pour faire le personnage, s'il tombe sur un mur,"+
        "refaite l'étape 1, puis le personnage continuera.<br>"+
        "Etape 4 : Utiliser O pour récupérer le nombre de pas déjà généré par LIUM puis refaite l'étape 3.<br>"+
        "<html>");
        rule.setFont(new Font("Verdana", Font.BOLD, 9));
        rule.setForeground(new Color(0x704C00));
        this.top.add(rule);

        Font font = new Font("Verdana", Font.BOLD, 20);

        this.timer = new JLabel("0s");
        this.timer.setFont(font);
        this.timer.setForeground(new Color(0x704C00));
        this.timer.setVisible(false);
        this.bottom.add(this.timer);

        this.nbStep = new JLabel("0 step");
        this.nbStep.setFont(font);
        this.nbStep.setForeground(new Color(0x704C00));
        this.nbStep.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        this.right.add(this.nbStep);

        this.direction = new JLabel("right");
        this.direction.setFont(font);
        this.direction.setForeground(new Color(0x704C00));
        this.direction.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        this.left.add(this.direction);

        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("src/resources/Labyrinthe/icon/micro.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.picLabel = new JLabel(new ImageIcon(myPicture));
        this.bottom.add(picLabel);
        this.picLabel.setVisible(false);

        try {
            myPicture = ImageIO.read(new File("src/resources/Labyrinthe/icon/chargement.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.chargement = new JLabel(new ImageIcon(myPicture));
        this.bottom.add(chargement);
        this.chargement.setVisible(false);


        this.setLocationRelativeTo(null);

        labyrinthePanel.startGameThread();
    }

    public JButton getRetour() {
        return retour;
    }

    public JLabel getPicLabel() {
        return picLabel;
    }

    public JLabel getChargement(){
        return chargement;
    }

    public Menu getMenu(){
        return menu;
    }

    public JLabel getTimer() {
        return timer;
    }

    public JLabel getNbStep() {
        return nbStep;
    }

    public JLabel getDirection() {
        return direction;
    }

    public JPanel getTopPanel(){
        return top;
    } 

    public JPanel getLeftPanel(){
        return left;
    } 

    public Labyrinthe_Panel getLabyrinthe_Panel(){
        return labyrinthePanel;
    }

}

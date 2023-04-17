package main.java.gui.Labyrinthe;

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

    public Labyrinthe_Launcher(boolean b){
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
        this.left.setBackground(new Color(239, 212, 106));
        this.left.setPreferredSize(new Dimension(width, 0));
        this.right = new JPanel();
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

        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("src/resources/Labyrinthe/icon/micro.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.picLabel = new JLabel(new ImageIcon(myPicture));
        this.bottom.add(picLabel);
        this.picLabel.setVisible(false);

        this.setLocationRelativeTo(null);

        labyrinthePanel.startGameThread();
    }

    public JLabel getPicLabel() {
        return picLabel;
    }
}

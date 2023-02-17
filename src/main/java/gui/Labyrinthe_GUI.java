package main.java.gui;

import main.java.model.Hero;
import main.java.model.Labyrinthe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Labyrinthe_GUI extends JFrame implements KeyListener{

    Labyrinthe labyrinthe;
    Terrain terrain;
    Hero hero;
    boolean genre;
    private double scaleX;
	private double scaleY;

    public Labyrinthe_GUI(){
        this.setTitle("Labyrinthe");
        this.setSize(1360, 760);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Insets scnMax=Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int largeur=(int)dimension.getWidth();
		int hauteur=(int)dimension.getHeight();
        int MargeX=(int)largeur-scnMax.left-scnMax.right;
		int MargeY=(int)hauteur-scnMax.bottom-scnMax.top;
		this.setSize(MargeX,MargeY);
        this.setLocationRelativeTo(this.getParent());
        scaleX=MargeX/1360;
        scaleY=MargeY/760;

        this.addKeyListener(this);

        this.labyrinthe = new Labyrinthe();

        this.hero = new Hero();

        this.terrain = new Terrain();
        this.add(this.terrain);
        this.genre = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        hero.moveNext(genre, e.getKeyChar());
        terrain.update();
        this.invalidate();
        this.validate();
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public class Terrain extends JPanel {

        public Terrain() {
            this.setLayout(new GridLayout(labyrinthe.getX(), labyrinthe.getY()));
            remplir();
        }

        public ImageIcon resizeImg(String paths) {
            ImageIcon imageIcon = new ImageIcon(paths);
            Image img = imageIcon.getImage();
            Image imageScal = img.getScaledInstance(64*(int)scaleX, 64*(int)scaleY, Image.SCALE_DEFAULT);
            return new ImageIcon(imageScal);
        }

        public JLabel getTree(int i, int j) {
            ImageIcon arbre1 = resizeImg("src/resources/Arbre1v3.png");
            ImageIcon arbre2 = resizeImg("src/resources/ArbreHautV4.png");
            ImageIcon arbre3 = resizeImg("src/resources/ArbreHautV4.png");

            JLabel image = new JLabel();

            if (i < labyrinthe.getX() - 2) {
                if (labyrinthe.getVal(i + 1, j) == 0 && labyrinthe.getVal(i + 2, j) == 0) {
                    image.setIcon(arbre3);
                } else if (labyrinthe.getVal(i + 1, j) == 1) {
                    image.setIcon(arbre1);
                } else if (labyrinthe.getVal(i + 1, j) == 0) {
                    image.setIcon(arbre2);
                }
            } else {
                image.setIcon(arbre3);
            }
            return image;
        }

        public JLabel getFloor() {
            JLabel image = new JLabel();
            ImageIcon sol = resizeImg("src/resources/Galeriev2.png");
            image.setIcon(sol);
            return image;
        }

        public JLabel getHero() {
            JLabel image = new JLabel();
            ImageIcon hero = resizeImg("src/resources/hero.png");
            image.setIcon(hero);
            return image;
        }

        public void remplir() {
            for (int i = 0; i < labyrinthe.getX(); i++) {
                for (int j = 0; j < labyrinthe.getY(); j++) {
                    JPanel tmp = new JPanel();
                    tmp.setLayout(new GridLayout());
                    switch (labyrinthe.getVal(i, j)) {
                        case 0:
                            tmp.add(getTree(i, j));
                            break;
                        case 1:
                            if (hero.getVal(i, j)) tmp.add(getHero());
                            else tmp.add(getFloor());
                            break;
                    }
                    this.add(tmp);
                }
            }
        }

        public void update(){
            this.removeAll();
            this.remplir();
        }

    }

}

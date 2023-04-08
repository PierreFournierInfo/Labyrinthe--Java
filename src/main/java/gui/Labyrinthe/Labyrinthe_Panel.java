package main.java.gui.Labyrinthe;

import main.java.controller.Tile_Controller;
import main.java.gui.Entity.Player;
import main.java.gui.Entity.Portail_Effect;
import main.java.model.Labyrinthe.Collision_Checker;
import main.java.model.Micro_Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Labyrinthe_Panel extends JPanel implements Runnable {

    private final int tileSize = 32;        // Taille d'une tuile
    private final int FPS = 60;
    private final int screenWidth = 38;     // Nombre de colonnes de tuile
    private final int screenHeight = 22;    // Nombre de lignes de tuile
    private Key key = new Key();
    private Player player = new Player(this, this.key);
    private Tile_Controller tileController = new Tile_Controller(this);
    private Thread thread;
    private Collision_Checker checker = new Collision_Checker(this);
    private Micro_Model micro;
    private Portail_Effect portail1 = new Portail_Effect(this, 0,tileSize*7);
    private Portail_Effect portail2 = new Portail_Effect(this, tileSize*35,tileSize*7);
    private boolean modeJeu;

    public Labyrinthe_Panel(boolean b){
        this.modeJeu = b;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void startGameThread(){          // Commencer le Jeu
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();      // Dernière Timer
        long currentTime;                       // Timer Actuelle
        long timer = 0;

        while (thread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }

            if(timer >= 100000000){
                timer = 0;
                portail1.update();
                portail2.update();
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileController.draw(g2);
        player.draw(g2);
        portail1.draw(g2);
        portail2.draw(g2);
        g2.dispose();
    }

    public class Key implements KeyListener{

        public boolean up, down, left, right, space;

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_Z :
                    up = true;
                    break;
                case KeyEvent.VK_Q :
                    left = true;
                    break;
                case KeyEvent.VK_S :
                    down = true;
                    break;
                case KeyEvent.VK_D :
                    right = true;
                    break;
                case KeyEvent.VK_SPACE :
                    if(!space){
                        micro = new Micro_Model();
                        Thread stopper = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(micro.getTemps(modeJeu));
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                if(modeJeu){
                                    micro.finish();
                                }
                                else{
                                    micro.finish2();
                                }                             
                            }
                        });
                        stopper.start();
                        micro.start(); 
                        //player.getNbHF();
                        space = true;
                    }
                    break;
                case KeyEvent.VK_H :
                    //player.HStep();
                    break;
                case KeyEvent.VK_F :
                    //player.FStep();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_Z :
                    up = false;
                    break;
                case KeyEvent.VK_Q :
                    left = false;
                    break;
                case KeyEvent.VK_S :
                    down = false;
                    break;
                case KeyEvent.VK_D :
                    right = false;
                    break;
                case KeyEvent.VK_SPACE :
                    space = false;
                    break;
            }
        }
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public Tile_Controller getTileController() {
        return tileController;
    }

    public Collision_Checker getChecker() {
        return checker;
    }

    public boolean getModeJeu(){
        return this.modeJeu;
    }
}

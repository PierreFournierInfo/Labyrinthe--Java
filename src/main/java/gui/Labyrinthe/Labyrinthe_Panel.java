package main.java.gui.Labyrinthe;

import main.java.controller.Tile_Controller;
import main.java.gui.Entity.Player;
import main.java.gui.Entity.Portail_Effect;
import main.java.model.Labyrinthe.Collision_Checker;
import main.java.model.Audio.Micro_Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Labyrinthe_Panel extends JPanel implements Runnable {

    private Labyrinthe_Launcher labyrinthe_launcher;
    private final int tileSize = 32;        // Taille d'une tuile
    private final int FPS = 60;
    private final int screenWidth = 38;     // Nombre de colonnes de tuile
    private final int screenHeight = 22;    // Nombre de lignes de tuile
    private Key key = new Key();
    private Player player;
    private Tile_Controller tileController;
    private Thread thread;
    private Collision_Checker checker = new Collision_Checker(this);
    private Micro_Model micro;
    private boolean microActivate = false;
    private int xPortail1, yPortail1;
    private int xPortail2, yPortail2;
    private Portail_Effect portail1;
    private Portail_Effect portail2;
    private boolean modeJeu;

    public Labyrinthe_Panel(boolean b, Labyrinthe_Launcher labyrinthe_launcher){
        this.labyrinthe_launcher = labyrinthe_launcher;
        this.tileController = new Tile_Controller(this,b);
        this.modeJeu = b;
        initLabyrinthePanel();
        this.setBackground(new Color(239, 212, 106));
        this.setDoubleBuffered(true);

        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void initLabyrinthePanel(){
        this.xPortail1 = (tileController.getLabyrinthe().getyPortail1() - 1) * tileSize;
        this.yPortail1 = (tileController.getLabyrinthe().getxPortail1() - 2) * tileSize;
        this.xPortail2 = (tileController.getLabyrinthe().getyPortail2() - 1) * tileSize;
        this.yPortail2 = (tileController.getLabyrinthe().getxPortail2() - 2) * tileSize;
        this.portail1 = new Portail_Effect(this, xPortail1, yPortail1);
        this.portail2 = new Portail_Effect(this, xPortail2, yPortail2);
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.player = new Player(this, this.key);
        this.player.setEnd();
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
        long micro = 0;

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

            if(player.getEnd()) {
                tileController.getLabyrinthe().initLabyrinthe();
                initLabyrinthePanel();
            }

            if (microActivate) {
                labyrinthe_launcher.getPicLabel().setVisible(true);
                micro += 1;
            }

            if (micro >= 100000000){
                openMicro();
                
                labyrinthe_launcher.getPicLabel().setVisible(false);
                micro = 0;
                microActivate = false;
            }

            if(timer >= 100000000){
                player.playerEnd(xPortail2, yPortail2);

                timer = 0;
                portail1.update();
                portail2.update();
            }
        }
    }

    public void openMicro(){
        micro = new Micro_Model();
        Thread stopperA = new Thread(new Runnable() {
            public void run() {
                Thread stopper = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(micro.getTemps(modeJeu));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if(modeJeu){
                        micro.finish();
                        player.getNbHF();
                    }
                    else{
                        micro.finish2();
                        player.getDirection();
                    }
                }
                });
                stopper.start();
                micro.start();
            }
        });
        stopperA.start();
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
                    microActivate = true;
                    space = true;
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

    public int getxPortail1() {
        return xPortail1;
    }

    public int getyPortail1() {
        return yPortail1;
    }
}

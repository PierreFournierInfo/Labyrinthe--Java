package main.java.gui.Labyrinthe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JPanel;

import main.java.controller.Tile_Controller;
import main.java.gui.Entity.Player;
import main.java.gui.Entity.Portail_Effect;
import main.java.model.Audio.Micro_Model;
import main.java.model.Labyrinthe.Collision_Checker;

public class Labyrinthe_Panel extends JPanel implements Runnable {

    private Labyrinthe_Launcher labyrinthe_launcher;
    private final int tileSize = 32; // Taille d'une tuile
    private final int FPS = 60;
    private final int screenWidth = 38; // Nombre de colonnes de tuile
    private final int screenHeight = 22; // Nombre de lignes de tuile
    private Key key = new Key();
    private Player player;
    private Tile_Controller tileController;
    private Thread thread;
    private Collision_Checker checker = new Collision_Checker(this);
    private Micro_Model micro = new Micro_Model();
    private boolean microActivate, modeJeu;
    private int xPortail1, yPortail1, xPortail2, yPortail2;
    private Portail_Effect portail1, portail2;
    private End_Game end;
    private long chrono = 0;

    public Labyrinthe_Panel(boolean b, Labyrinthe_Launcher labyrinthe_launcher) {
        this.labyrinthe_launcher = labyrinthe_launcher;
        this.tileController = new Tile_Controller(this, b);
        this.modeJeu = b;
        initLabyrinthePanel();
        this.setBackground(new Color(239, 212, 106));
        this.setDoubleBuffered(true);

        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void initLabyrinthePanel() {
        this.xPortail1 = (tileController.getLabyrinthe().getyPortail1() - 1) * tileSize;
        this.yPortail1 = (tileController.getLabyrinthe().getxPortail1() - 2) * tileSize;
        this.xPortail2 = (tileController.getLabyrinthe().getyPortail2() - 1) * tileSize;
        this.yPortail2 = (tileController.getLabyrinthe().getxPortail2() - 2) * tileSize;
        this.portail1 = new Portail_Effect(this, xPortail1, yPortail1);
        this.portail2 = new Portail_Effect(this, xPortail2, yPortail2);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.player = new Player(this, this.key);
        this.end = new End_Game(this);
        this.player.setEnd();
    }

    public void startGameThread() { // Commencer le Jeu
        thread = new Thread(this);
        thread.start();
    }

    public void stopGameThread() {
        thread.stop();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime(); // Dernière Timer
        long currentTime; // Timer Actuelle
        long timer = 0;

        while (thread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            if (microActivate) {
                chrono += (currentTime - lastTime);
            } else {
                chrono = 0;
            }

            lastTime = currentTime;

            if (delta >= 1) {
                labyrinthe_launcher.getTimer().setText(chrono / 1000000000 + "s");
                update();
                repaint();
                delta--;
            }

            if (player.getEnd()) {
                tileController.getLabyrinthe().initLabyrinthe();
                player.setNbStep();
                labyrinthe_launcher.getNbStep().setText("0 step");
                initLabyrinthePanel();
            }

            if (timer >= 100000000) {
                player.playerEnd(xPortail2, yPortail2);

                timer = 0;
                if (modeJeu) {
                    if (tileController.getLabyrinthe().getNumMap() == 2) {
                        end.update();
                    }
                } else {
                    if (tileController.getLabyrinthe().getNumMap() == 3) {
                        end.update();
                    }
                }
                portail1.update();
                portail2.update();
            }
        }
    }

    public void affichage_icone_micro(boolean b) {
        labyrinthe_launcher.getTimer().setVisible(b);
        labyrinthe_launcher.getPicLabel().setVisible(b);
        repaint();
    }

    public void actualisation_step() {
        labyrinthe_launcher.getNbStep().setText(player.getNbStep() / 32 + " step");
        repaint();
    }

    public void actualisation_stepBis() {
        labyrinthe_launcher.getNbStep().setText(player.getNbTotal() / 32 + " step");
        repaint();
    }

    public void icone_chargement(boolean b) {
        labyrinthe_launcher.getChargement().setVisible(b);
        repaint();
    }

    public void enregistrement_micro() {
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                affichage_icone_micro(true);
                micro.start();
            }
        });
        stopper.start();
    }

    public void terminer_micro() {
        micro.fin_micro();
        labyrinthe_launcher.getTimer().setVisible(false);
        affichage_icone_micro(false);
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int tmp = 3;

        if (modeJeu)
            tmp = 2;

        if (tileController.getLabyrinthe().getNumMap() != tmp) {
            tileController.draw(g2);
            player.draw(g2);
            portail1.draw(g2);
            portail2.draw(g2);
            g2.dispose();
        } else {
            this.end.draw(g2);
        }
    }

    public boolean verify_exist(String s) {
        File f = new File(s);
        return f.exists();
    }

    public class Key implements KeyListener {

        public boolean up, down, left, right, touch_J, touch_K, touch_L;

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z:
                    up = true;
                    break;
                case KeyEvent.VK_Q:
                    left = true;
                    break;
                case KeyEvent.VK_S:
                    down = true;
                    break;
                case KeyEvent.VK_D:
                    right = true;
                    break;
                case KeyEvent.VK_SPACE:
                    if (!microActivate && !touch_J && !touch_K && !touch_L) {
                        microActivate = true;
                        enregistrement_micro();
                    } else if (microActivate && !touch_J && !touch_K && !touch_L) {
                        terminer_micro();
                        microActivate = false;
                    }
                    break;
                case KeyEvent.VK_J:
                    if (!touch_J && !microActivate && !touch_K && verify_exist("src/resources/Audio/RecordAudio.wav")
                            && !touch_L) {
                        System.out.println("Lancement de Whisper");
                        touch_J = true;
                        icone_chargement(true);
                        Thread stopper = new Thread(new Runnable() {
                            public void run() {
                                boolean status = micro.finish2();
                                if (status) {
                                    player.getDirection();
                                    touch_J = false;
                                    icone_chargement(false);
                                }
                            }
                        });
                        stopper.start();
                    }
                    break;
                case KeyEvent.VK_K:
                    if (!touch_K && !microActivate && !touch_J && verify_exist("src/resources/Audio/RecordAudio.wav")
                            && !touch_L) {
                        System.out.println("Lancement de LIUM");
                        touch_K = true;
                        icone_chargement(true);
                        Thread stopper = new Thread(new Runnable() {
                            public void run() {
                                boolean status = micro.finish();
                                if (status) {
                                    player.getNbHF();
                                    actualisation_stepBis();
                                    touch_K = false;
                                    icone_chargement(false);
                                }
                            }
                        });
                        stopper.start();
                    }
                    break;
                case KeyEvent.VK_L:
                    if (!touch_L && !touch_K && !microActivate && !touch_J && player.getNbStep() == 0) {
                        touch_L = true;
                        player.deplacement();
                        actualisation_step();
                        touch_L = false;
                    }
                    break;
                case KeyEvent.VK_U:
                    if (!player.getGameMode()) {
                        player.setGameMode(true);
                    } else {
                        player.setGameMode(false);
                    }
                    break;
                case KeyEvent.VK_I:
                    if (player.getNbStep() == 0) {
                        player.ajouter_pas();
                        actualisation_stepBis();
                    }
                    break;
                case KeyEvent.VK_O:
                    if (!touch_L && !touch_K && !microActivate && !touch_J && player.getNbStep() == 0) {
                        player.valSave();
                        actualisation_stepBis();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    labyrinthe_launcher.getLabyrinthe_Panel().stopGameThread();
                    labyrinthe_launcher.dispose();
                    labyrinthe_launcher.getMenu().setVisible(true);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z:
                    up = false;
                    break;
                case KeyEvent.VK_Q:
                    left = false;
                    break;
                case KeyEvent.VK_S:
                    down = false;
                    break;
                case KeyEvent.VK_D:
                    right = false;
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

    public boolean getModeJeu() {
        return this.modeJeu;
    }

    public int getxPortail1() {
        return xPortail1;
    }

    public int getyPortail1() {
        return yPortail1;
    }

    public Labyrinthe_Launcher getLabyrinthe_launcher() {
        return labyrinthe_launcher;
    }
}

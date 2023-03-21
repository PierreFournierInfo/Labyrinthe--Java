package main.java.gui;

import main.java.controller.TileManager;
import main.java.model.Analyse_audio;
import main.java.model.Micro_Model;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class LabyrinthePanel extends JPanel implements Runnable {

    public final int tileSize = 32;
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 11;
    private int FPS = 60;
    public int screenWidth;
    public int screenHeight;
    private Key key = new Key();
    private Player player = new Player(this, this.key);
    TileManager tileManager = new TileManager(this);
    private Thread thread;
    CollisionChecker checker = new CollisionChecker(this);
    private Micro_Model micro;

    public LabyrinthePanel(){

        this.screenWidth = maxScreenCol * 2;
        this.screenHeight = maxScreenRow * 2;

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void startGameThread(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
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

            if(timer >= 1000000000){
                timer = 0;
            }

        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
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
                                    Thread.sleep(10000);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                try {
                                    micro.finish();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (UnsupportedAudioFileException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (LineUnavailableException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        });
                        stopper.start();
                        micro.start(); 
                        space = true;
                    }
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
                    player.getNbHF();
                    break;
            }
        }
    }
}

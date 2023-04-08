package main.java.gui.Entity;

import javax.imageio.ImageIO;

import main.java.gui.Labyrinthe.Labyrinthe_Panel;
import main.java.model.Analyse_audio;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    Labyrinthe_Panel lp;
    Labyrinthe_Panel.Key key;
    private int nbStep = 0;
    private final Analyse_audio Aaudio;
    private int[] hf;
    private String dir;

    public Player(Labyrinthe_Panel lp, Labyrinthe_Panel.Key k){
        this.lp = lp;
        this.key = k;

        Aaudio = new Analyse_audio();
        if(lp.getModeJeu()){
            hf = new int[2];
        }
        else{
            dir = " ";
        }
        solidArea = new Rectangle(1, 1, 62, 62);

        setDefaultValues();
    }

    public void getNbHF(){
        hf = Aaudio.nbrHommesFemmes();
        nbStep = hf[0] * lp.getTileSize() + hf[1] * 2 * lp.getTileSize();
    }

    public void getDirection(){
        this.dir = Aaudio.directionUnique();
        nbStep = 3 * lp.getTileSize();
    }

    public void setDefaultValues(){
        x = lp.getTileSize();
        y = 9 * lp.getTileSize();
        speed = 1;
        direction = "down";
        getPlayerImage();
    }

    public void update(){
        if(key.up){
            direction = "up";
        }else if (key.down){
            direction = "down";
        }else if (key.left){
            direction = "left";
        }else if (key.right){
            direction = "right";
        }
        collisionOn = false;
        lp.getChecker().checkTile(this);

        if (!collisionOn){
            if (nbStep != 0){
                switch (direction) {
                    case "up" -> this.y -= speed;
                    case "down" -> this.y += speed;
                    case "left" -> this.x -= speed;
                    case "right" -> this.x += speed;
                }
                nbStep--;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };
        g2.drawImage(image, x, y, lp.getTileSize(), lp.getTileSize(), null);
    }

    public void getPlayerImage(){
        try {
            down1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-1.png"));
            left1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-2.png"));
            right1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-3.png"));
            up1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-4.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void HStep(){
        nbStep = lp.getTileSize();
    }

    public void FStep(){
        nbStep = lp.getTileSize()*2;
    }
}

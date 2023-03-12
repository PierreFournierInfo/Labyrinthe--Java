package main.java.gui;

import javax.imageio.ImageIO;

import main.java.model.Analyse_audio;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    LabyrinthePanel lp;
    LabyrinthePanel.Key key;

    private final Analyse_audio Aaudio;
    private int[] hf;
    private int deplacement;

    public Player(LabyrinthePanel lp, LabyrinthePanel.Key k){
        this.lp = lp;
        this.key = k;

        Aaudio = new Analyse_audio();
        hf = new int[2];

        solidArea = new Rectangle(8, 16, 48, 48);

        setDefaultValues();
    }

    public void getNbHF(){
        hf = Aaudio.nbrHommesFemmes();
        deplacement = hf[0] * lp.tileSize + hf[1] * 2 * lp.tileSize;
    }


    public void setDefaultValues(){
        x = 0;
        y = 256;
        speed = 2;
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
        lp.checker.checkTile(this);

        if (!collisionOn){
            if(key.right|| key.left|| key.down|| key.up){
                switch (direction) {
                    case "up" -> this.y -= speed;
                    case "down" -> this.y += speed;
                    case "left" -> this.x -= speed;
                    case "right" -> this.x += speed;
                }
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
        g2.drawImage(image, x+deplacement, y, lp.tileSize, lp.tileSize, null);
    }

    public void getPlayerImage(){
        try {
            down1 = ImageIO.read(new File("src/resources/hero-1.png"));
            left1 = ImageIO.read(new File("src/resources/hero-2.png"));
            right1 = ImageIO.read(new File("src/resources/hero-3.png"));
            up1 = ImageIO.read(new File("src/resources/hero-4.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

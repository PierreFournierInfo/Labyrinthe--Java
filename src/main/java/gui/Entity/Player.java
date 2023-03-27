package main.java.gui.Entity;

import javax.imageio.ImageIO;

import main.java.gui.Entity.Entity;
import main.java.gui.Labyrinthe.Labyrinthe_Panel;
import main.java.model.Analyse_audio;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    Labyrinthe_Panel lp;
    Labyrinthe_Panel.Key key;

    private final Analyse_audio Aaudio;
    private int[] hf;
    private int deplacement;

    public Player(Labyrinthe_Panel lp, Labyrinthe_Panel.Key k){
        this.lp = lp;
        this.key = k;

        Aaudio = new Analyse_audio();
        hf = new int[2];

        solidArea = new Rectangle(7, 15, 48, 48);

        setDefaultValues();
    }

    public void getNbHF(){
        hf = Aaudio.nbrHommesFemmes();
        deplacement = hf[0] * lp.tileSize + hf[1] * 2 * lp.tileSize;
    }


    public void setDefaultValues(){
        x = lp.tileSize;
        y = 9 * lp.tileSize;
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
            down1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-1.png"));
            left1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-2.png"));
            right1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-3.png"));
            up1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-4.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

package main.java.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    LabyrinthePanel lPanel;
    LabyrinthePanel.Key key;

    public Player(LabyrinthePanel l, LabyrinthePanel.Key k){
        this.lPanel = l;
        this.key = k;

        setDefaultValues();
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
            y -= speed;
        }else if (key.down){
            direction = "down";
            y += speed;
        }else if (key.left){
            direction = "left";
            x -= speed;
        }else if (key.right){
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image, x, y, lPanel.tileSize, lPanel.tileSize, null);
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

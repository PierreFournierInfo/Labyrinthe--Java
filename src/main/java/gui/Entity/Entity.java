package main.java.gui.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;
    public BufferedImage up1, down1, left1, right1;
    public String direction;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}

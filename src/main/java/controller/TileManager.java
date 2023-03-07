package main.java.controller;

import main.java.gui.LabyrinthePanel;
import main.java.gui.Tile;
import main.java.model.Labyrinthe;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TileManager {

    LabyrinthePanel lPanel;
    Tile[] tile;
    public Labyrinthe laby;
    int x;
    int y;

    public TileManager(LabyrinthePanel l){
        this.lPanel = l;
        this.tile = new Tile[10];
        this.laby = new Labyrinthe();
        this.x = 0;
        this.y = 0;
        getTileImage();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/resources/Galerie.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/resources/Arbre1.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("src/resources/Arbre2.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("src/resources/Arbre3.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        for(int i=0;i<laby.getX();i++){
            for(int j=0;j<laby.getY();j++){
                switch (laby.getVal(i, j)){
                    case 0:
                        if (i < laby.getX() - 2) {
                            if (laby.getVal(i + 1, j) == 0 && laby.getVal(i + 2, j) == 0) {
                                g2.drawImage(tile[3].image,x,y,lPanel.tileSize, lPanel.tileSize, null);
                            } else if (laby.getVal(i + 1, j) == 1) {
                                g2.drawImage(tile[1].image,x,y,lPanel.tileSize, lPanel.tileSize, null);
                            } else if (laby.getVal(i + 1, j) == 0) {
                                g2.drawImage(tile[2].image,x,y,lPanel.tileSize, lPanel.tileSize, null);
                            }
                        } else {
                            g2.drawImage(tile[3].image,x,y,lPanel.tileSize, lPanel.tileSize, null);
                        }
                        break;
                    case 1:
                        g2.drawImage(tile[0].image,x,y,lPanel.tileSize, lPanel.tileSize, null);
                        break;
                }
                x += lPanel.tileSize;
            }
            x = 0;
            y += lPanel.tileSize;
        }
        y = 0;
    }

    public int getVal(int x, int y){
        return laby.getVal(x, y);
    }

}
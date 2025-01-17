package main.java.controller;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.java.gui.Labyrinthe.Labyrinthe_Panel;
import main.java.model.Labyrinthe.Labyrinthe;
import main.java.model.Labyrinthe.Tile;

public class Tile_Controller {

    private Labyrinthe_Panel labyrinthePanel;
    private Labyrinthe labyrinthe;
    private Tile[] tile; // Les différentes tuiles
    private int x, y; // Les coordonnées des tuiles
    private boolean modeJeu;

    public Tile_Controller(Labyrinthe_Panel labyrinthePanel, boolean b) {
        modeJeu = b;
        this.labyrinthePanel = labyrinthePanel;
        labyrinthe = new Labyrinthe(modeJeu);
        tile = new Tile[10]; // Nombre d'images de tuile
        x = 0;
        y = 0;
        getTileImage(); // avoir les images des tuiles
    }

    public void getTileImage() { // Ajout des images de différentes tuiles
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/resources/Labyrinthe/galerie/Galerie.png"));
            tile[0].collision = true;
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/resources/Labyrinthe/arbre/Arbre1.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("src/resources/Labyrinthe/arbre/Arbre2.png"));
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("src/resources/Labyrinthe/arbre/Arbre3.png"));
            tile[3].collision = true;
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("src/resources/Labyrinthe/portail/Portail1.png"));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("src/resources/Labyrinthe/portail/Portail2.png"));
            tile[5].collision = true;
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("src/resources/Labyrinthe/portail/Portail3.png"));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("src/resources/Labyrinthe/portail/Portail4.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) { // Dessine toutes les tuiles
        for (int i = 0; i < labyrinthe.getX(); i++) {
            for (int j = 0; j < labyrinthe.getY(); j++) {
                switch (labyrinthe.getVal(i, j)) {
                    case 0:
                        g2.drawImage(tile[3].image, x, y, labyrinthePanel.getTileSize(), labyrinthePanel.getTileSize(),
                                null);
                        break;
                    case 1:
                        g2.drawImage(tile[0].image, x, y, labyrinthePanel.getTileSize(), labyrinthePanel.getTileSize(),
                                null);
                        break;
                    case 2:
                        g2.drawImage(tile[1].image, x, y, labyrinthePanel.getTileSize(), labyrinthePanel.getTileSize(),
                                null);
                        break;
                    case 3:
                        g2.drawImage(tile[2].image, x, y, labyrinthePanel.getTileSize(), labyrinthePanel.getTileSize(),
                                null);
                        break;
                    case 4:
                        g2.drawImage(tile[4].image, x, y, labyrinthePanel.getTileSize(), labyrinthePanel.getTileSize(),
                                null);
                        break;
                    case 5:
                        g2.drawImage(tile[5].image, x, y, labyrinthePanel.getTileSize(), labyrinthePanel.getTileSize(),
                                null);
                        break;
                    case 6, 7:
                        if (modeJeu) {
                            g2.drawImage(tile[6].image, x, y, labyrinthePanel.getTileSize(),
                                    labyrinthePanel.getTileSize(), null);
                        } else {
                            g2.drawImage(tile[7].image, x, y, labyrinthePanel.getTileSize(),
                                    labyrinthePanel.getTileSize(), null);
                        }

                        break;

                }
                x += labyrinthePanel.getTileSize();
            }
            x = 0;
            y += labyrinthePanel.getTileSize();
        }
        y = 0;
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }

    public Tile[] getTile() {
        return tile;
    }
}
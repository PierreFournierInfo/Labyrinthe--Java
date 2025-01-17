package main.java.gui.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;

import main.java.gui.Labyrinthe.Labyrinthe_Panel;
import main.java.model.Audio.Analyse_audio;

public class Player extends Entity {

    private Labyrinthe_Panel lp;
    private Labyrinthe_Panel.Key key;
    private int nbStep = 0;
    private final Analyse_audio Aaudio;
    private boolean end;
    private int[] hf;
    private int nbTotal = 0;
    private boolean GameModeStatus;
    private int valTmp = 0;

    public Player(Labyrinthe_Panel lp, Labyrinthe_Panel.Key k) {
        this.lp = lp;
        this.key = k;

        Aaudio = new Analyse_audio();
        if (lp.getModeJeu()) {
            hf = new int[2];
        }

        solidArea = new Rectangle(0, 0, 63, 63);

        setDefaultValues();
    }

    public void getNbHF() {
        hf = Aaudio.nbrHommesFemmes();
        nbTotal = hf[0] * lp.getTileSize() + hf[1] * 2 * lp.getTileSize();
        valTmp = nbTotal;
        if (nbTotal < 0) {
            nbTotal = 0;
            valTmp = 0;
        }
    }

    public void deplacement() {
        nbStep = nbTotal;
        nbTotal = 0;
    }

    public void valSave() {
        nbTotal = valTmp;
    }

    public int getNbTotal() {
        return nbTotal;
    }

    public void setDefaultValues() {
        x = lp.getxPortail1() + lp.getTileSize();
        y = lp.getyPortail1() + lp.getTileSize() * 2;
        speed = 1;
        direction = "right";
        getPlayerImage();
    }

    public void getDirection() {
        if (!lp.getModeJeu()) {
            Stack<String> dir = Aaudio.direction();
            while (!dir.empty()) {
                String d = dir.pop();
                switch (d) {
                    case "haut" -> direction = "up";
                    case "bas" -> direction = "down";
                    case "gauche" -> direction = "left";
                    case "droite" -> direction = "right";
                }
                System.out.println(direction);
            }
        }
    }

    public void update() {
        if (lp.getModeJeu() || (GameModeStatus)) {
            if (key.up) {
                direction = "up";
            } else if (key.down) {
                direction = "down";
            } else if (key.left) {
                direction = "left";
            } else if (key.right) {
                direction = "right";
            }
        }

        collisionOn = false;
        lp.getChecker().checkTile(this);

        if (!collisionOn) {
            if (nbStep != 0) {
                switch (direction) {
                    case "up" -> this.y -= speed;
                    case "down" -> this.y += speed;
                    case "left" -> this.x -= speed;
                    case "right" -> this.x += speed;
                }
                lp.actualisation_step();
                nbStep--;
            }
        }
        lp.getLabyrinthe_launcher().getDirection().setText(direction);
    }

    public void ajouter_pas() {
        nbTotal += lp.getTileSize();
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };
        g2.drawImage(image, x, y, lp.getTileSize(), lp.getTileSize(), null);
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-1.png"));
            left1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-2.png"));
            right1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-3.png"));
            up1 = ImageIO.read(new File("src/resources/Labyrinthe/hero/hero-4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playerEnd(int x, int y) {
        end = (x == (this.x - lp.getTileSize())) && (y == (this.y - (lp.getTileSize() * 2)));
    }

    public boolean getEnd() {
        return this.end;
    }

    public void setEnd() {
        this.end = false;
    }

    public int getNbStep() {
        return nbStep;
    }

    public void setGameMode(boolean b) {
        this.GameModeStatus = b;
    }

    public boolean getGameMode() {
        return this.GameModeStatus;
    }

    public void setNbStep() {
        this.nbStep = 0;
    }
}

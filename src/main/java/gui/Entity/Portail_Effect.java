package main.java.gui.Entity;

import main.java.gui.Labyrinthe.Labyrinthe_Panel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Portail_Effect {

    private BufferedImage[] effect;
    private int nbImg = 0;
    private int x, y;
    private Labyrinthe_Panel lp;

    public Portail_Effect(Labyrinthe_Panel lp, int x, int y){
        this.lp = lp;
        this.effect = new BufferedImage[4];
        this.x = x;
        this.y = y;
        getPlayerImage();
    }

    public void update(){
        incrementNbImg();
        if (nbImg == 3){
            setNbImgTo0();
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = switch (nbImg) {
            case 0 -> effect[0];
            case 1 -> effect[1];
            case 2 -> effect[2];
            case 3 -> effect[3];
            default -> null;
        };
        if (!lp.getModeJeu()){
            g2.drawImage(image, x, y+lp.getTileSize(), lp.getTileSize()*3, lp.getTileSize()*2, null);
        }else{
            g2.drawImage(image, x, y, lp.getTileSize()*3, lp.getTileSize()*3, null);
        }

    }

    public void getPlayerImage(){
        try {
            effect[0] = ImageIO.read(new File("src/resources/Labyrinthe/portail_effect/Portail1.png"));
            effect[1] = ImageIO.read(new File("src/resources/Labyrinthe/portail_effect/Portail2.png"));
            effect[2] = ImageIO.read(new File("src/resources/Labyrinthe/portail_effect/Portail3.png"));
            effect[3] = ImageIO.read(new File("src/resources/Labyrinthe/portail_effect/Portail4.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setNbImgTo0() {
        this.nbImg = 0;
    }
    public void incrementNbImg() {
        this.nbImg++;
    }
}

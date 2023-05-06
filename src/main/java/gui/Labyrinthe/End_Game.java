package main.java.gui.Labyrinthe;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class End_Game extends JPanel {
    
    private BufferedImage[] images;
    private int nbImg = 0;
    private Labyrinthe_Panel lp;

    public End_Game(Labyrinthe_Panel lp) {
        this.lp = lp;
        this.images = new BufferedImage[55];
        getPlayerImage();
    }

    public void update() {
        incrementNbImg();
        if (nbImg == 55) {
            lp.getLabyrinthe_launcher().getMenu().setVisible(true);
            lp.getLabyrinthe_launcher().setVisible(false);
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = images[nbImg];
        g2.drawImage(image, 0, 0, lp.getTileSize() * 38, lp.getTileSize() * 22, null);
    }

    public void getPlayerImage() {
        try {
            for (int i = 0; i < images.length; i++) {
                images[i] = ImageIO.read(new File("src/resources/Labyrinthe/end/end_game-" + i + ".png"));
            }
        } catch (IOException e) {
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

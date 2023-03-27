package main.java.model.Labyrinthe;

import main.java.gui.Entity.Entity;
import main.java.gui.Labyrinthe.Labyrinthe_Panel;

public class Collision_Checker {

    Labyrinthe_Panel lp;

    public Collision_Checker(Labyrinthe_Panel lp){
        this.lp = lp;
    }

    public void checkTile(Entity e){

        int leftX = e.x + e.solidArea.x;
        int rightX = e.x + e.solidArea.x + e.solidArea.width;
        int topY = e.y + e.solidArea.y;
        int bottomY = e.y + e.solidArea.y + e.solidArea.height;

        int leftCol = leftX/lp.getTileSize();
        int rightCol = rightX/lp.getTileSize();
        int topRow = topY/lp.getTileSize();
        int bottomRow = bottomY/lp.getTileSize();

        int tileNum1, tileNum2;

        switch (e.direction){
            case "up":
                topRow = (topY - e.speed)/lp.getTileSize();
                tileNum1 = lp.getTileController().getLabyrinthe().getVal(topRow, leftCol);
                tileNum2 = lp.getTileController().getLabyrinthe().getVal(topRow, rightCol-1);
                if (lp.getTileController().getTile()[tileNum1].collision || lp.getTileController().getTile()[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
            case "down":
                bottomRow = (bottomY + e.speed)/lp.getTileSize();
                tileNum1 = lp.getTileController().getLabyrinthe().getVal(bottomRow-1, leftCol);
                tileNum2 = lp.getTileController().getLabyrinthe().getVal(bottomRow-1, rightCol-1);
                if (lp.getTileController().getTile()[tileNum1].collision || lp.getTileController().getTile()[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (leftX - e.speed)/lp.getTileSize();
                tileNum1 = lp.getTileController().getLabyrinthe().getVal(topRow, leftCol);
                tileNum2 = lp.getTileController().getLabyrinthe().getVal(bottomRow-1, leftCol);
                if ((lp.getTileController().getTile()[tileNum1].collision || lp.getTileController().getTile()[tileNum2].collision)){
                    e.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (rightX + e.speed)/lp.getTileSize();
                tileNum1 = lp.getTileController().getLabyrinthe().getVal(topRow, rightCol-1);
                tileNum2 = lp.getTileController().getLabyrinthe().getVal(bottomRow-1, rightCol-1);
                if (lp.getTileController().getTile()[tileNum1].collision || lp.getTileController().getTile()[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
        }

    }

}

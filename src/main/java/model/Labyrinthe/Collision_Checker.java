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

        int leftCol = leftX/lp.tileSize;
        int rightCol = rightX/lp.tileSize;
        int topRow = topY/lp.tileSize;
        int bottomRow = bottomY/lp.tileSize;

        int tileNum1, tileNum2;

        switch (e.direction){
            case "up":
                topRow = (topY - e.speed)/lp.tileSize;
                tileNum1 = lp.tileManager.labyrinthe.getVal(topRow, leftCol);
                tileNum2 = lp.tileManager.labyrinthe.getVal(topRow, rightCol-1);
                if (lp.tileManager.tile[tileNum1].collision || lp.tileManager.tile[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
            case "down":
                bottomRow = (bottomY + e.speed)/lp.tileSize;
                tileNum1 = lp.tileManager.labyrinthe.getVal(bottomRow-1, leftCol);
                tileNum2 = lp.tileManager.labyrinthe.getVal(bottomRow-1, rightCol-1);
                if (lp.tileManager.tile[tileNum1].collision || lp.tileManager.tile[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (leftX - e.speed)/lp.tileSize;
                tileNum1 = lp.tileManager.labyrinthe.getVal(topRow, leftCol);
                tileNum2 = lp.tileManager.labyrinthe.getVal(bottomRow-1, leftCol);
                if ((lp.tileManager.tile[tileNum1].collision || lp.tileManager.tile[tileNum2].collision)){
                    e.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (rightX + e.speed)/lp.tileSize;
                tileNum1 = lp.tileManager.labyrinthe.getVal(topRow, rightCol-1);
                tileNum2 = lp.tileManager.labyrinthe.getVal(bottomRow-1, rightCol-1);
                if (lp.tileManager.tile[tileNum1].collision || lp.tileManager.tile[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
        }

    }

}

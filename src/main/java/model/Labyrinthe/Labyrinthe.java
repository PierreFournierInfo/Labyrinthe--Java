package main.java.model.Labyrinthe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Labyrinthe {

    private int[][] labyrinthe;
    private String map;
    private final int x = 22;
    private final int y = 38;
    private int xPortail1, xPortail2;
    private int yPortail1, yPortail2;
    private boolean modeJeu;

    public Labyrinthe(boolean b){
        this.modeJeu = b;
        if (modeJeu){
            this.map = "src/resources/Labyrinthe/map/map01.txt";
        }else{
            this.map = "src/resources/Labyrinthe/map/map02.txt";
        }
        this.labyrinthe = new int[x][y];
        this.initMap();
        this.initPortail();
    }

    public void initMap(){
        try {
            //InputStream is = Labyrinthe.class.getResourceAsStream(map);
            File fichier = new File(map);
            BufferedReader br = new BufferedReader(new FileReader(fichier));
            String t;
            for (int i = 0; i < x; i++){
                t = br.readLine().replaceAll("\\s", "");
                for (int j = 0; j < y; j++){
                    labyrinthe[i][j] = t.charAt(j) - '0';
                }
            }
        }catch (Exception e){
            System.out.println("erreur");
        }
    }

    public void initPortail(){
        for (int i=0;i<labyrinthe.length;i++){
            for (int j=0;j<labyrinthe[i].length;j++){
                if (labyrinthe[i][j] == 6){
                    xPortail1 = i;
                    yPortail1 = j;
                }
                if (labyrinthe[i][j] == 7){
                    xPortail2 = i;
                    yPortail2 = j;
                }
            }
        }
    }

    public int getVal(int x, int y){
        return this.labyrinthe[x][y];
    }

    public int[][] getLabyrinthe() {
        return labyrinthe;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxPortail1() {
        return xPortail1;
    }

    public int getyPortail1() {
        return yPortail1;
    }

    public int getxPortail2() {
        return xPortail2;
    }

    public int getyPortail2() {
        return yPortail2;
    }
}

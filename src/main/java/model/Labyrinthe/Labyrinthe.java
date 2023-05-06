package main.java.model.Labyrinthe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Labyrinthe {

    private int[][] labyrinthe;
    private Map map;
    private String map_name;
    private int numMap = 0;
    private final int x = 22;
    private final int y = 38;
    private int xPortail1, xPortail2;
    private int yPortail1, yPortail2;
    private boolean mode;

    public Labyrinthe(boolean b){
        this.mode = b;
        initLabyrinthe();
    }

    public void initLabyrinthe(){
        if (!this.mode){
            this.map = new Map(x,y);
            this.labyrinthe = this.map.getMap();
        }else {
            this.labyrinthe = new int[x][y];
            this.map_name = "src/resources/Labyrinthe/map/map01.txt";
            this.initMap();
        }
        this.initPortail();
        numMap++;
    }

    public void initMap(){
        if (numMap != 3){
            try {
                //InputStream is = Labyrinthe.class.getResourceAsStream(map);
                File fichier = new File(map_name);
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

    public String getMap_name() {
        return map_name;
    }

    public int getNumMap() {
        return numMap;
    }
}

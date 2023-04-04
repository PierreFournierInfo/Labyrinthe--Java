package main.java.model.Labyrinthe;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Labyrinthe {

    private int[][] labyrinthe;
    private String map;
    private final int x = 22;
    private final int y = 36;

    public Labyrinthe(){
        map = "/Labyrinthe/map/map01.txt";
        labyrinthe = new int[x][y];
        getMap();
    }

    public void getMap(){
        try {
            InputStream is = Labyrinthe.class.getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String t;
            for (int i = 0; i < 22; i++){
                t = br.readLine().replaceAll("\\s", "");
                for (int j = 0; j < 36; j++){
                    labyrinthe[i][j] = t.charAt(j) - '0';
                }
            }

        }catch (Exception e){
            System.out.println("erreur");
        }
    }

    public void addGalerie(int x, int y){
        this.labyrinthe[x][y] = 1;
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
}

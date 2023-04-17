package main.java.model.Labyrinthe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Labyrinthe {

    private int[][] labyrinthe;
    private String map;
    private final int x = 22;
    private final int y = 38;
    private int portail1, portail2;
    private boolean modeJeu;

    public Labyrinthe(boolean b){
        this.modeJeu = b;
        if (modeJeu){
            this.map = "src/resources/Labyrinthe/map/map01.txt";
        }else{
            this.map = "src/resources/Labyrinthe/map/map02.txt";
        }
        this.labyrinthe = new int[x][y];
        this.getMap();
        this.portail1 = Portail1();
        this.portail2 = Portail2();
    }

    public void getMap(){
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

    public int Portail1(){
        for (int i=0;i<labyrinthe.length;i++){
            if (labyrinthe[i][1] == 4) return i;
        }
        return 0;
    }
    public int Portail2(){
        for (int i=0;i<labyrinthe.length;i++){
            if (labyrinthe[i][labyrinthe[0].length - 2] == 4) return i;
        }
        return 0;
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

    public int getPortail1() {
        return portail1;
    }

    public int getPortail2() {
        return portail2;
    }
}

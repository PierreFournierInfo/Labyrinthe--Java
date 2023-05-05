package main.java.model.Labyrinthe;

import java.util.Random;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Map{


    final int [][]map;
    final static int mur = 0;
    final static int chemin = 1;
    final static int depart = 6;
    final static int fin = 7;

    public Map(int longueur,int largeur){
        this.map= genererLabyrinthe(longueur, largeur);
    }


    private static int[][] genererLabyrinthe(int longueur, int largeur) {
        int[][] tableau = new int[longueur][largeur];

        // Initialiser toutes les cases avec des murs (0)
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < largeur; j++) {
                tableau[i][j] = mur;
            }
        }

        // Choisir une case de départ aléatoire
        Random random = new Random();
        int startRow = random.nextInt(longueur)+1;
        int startCol = random.nextInt(largeur)+1;
        if(startRow > 3){
            startRow -= 2;
        }
        if(startCol > 3){
            startCol -= 2;
        }
        tableau[startRow][startCol] = depart; // la case de départ est un chemin (1)


        // Appliquer l'algorithme Recursive Backtracker
        Stack<Integer[]> stack = new Stack<>(); // pile pour stocker les cases visitées
        stack.push(new Integer[] {startRow, startCol});

        while (!stack.isEmpty()) {
            System.out.println("");
            System.out.println("");
            afficheTab(tableau);
            Integer[] current = stack.pop();
            int row = current[0];
            int col = current[1];

            // Choisir une case voisine aléatoire non visitée
            List<Integer[]> neighbors = new ArrayList<>();
            if (verify_bord(row-2, longueur) && (verify_for_image(tableau, row-2, col) || tableau[row-2][col] == 0)) {
                neighbors.add(new Integer[] {row-2, col});
            }
            if (verify_bord(row+2, longueur) && (verify_for_image(tableau, row+2, col) ||tableau[row+2][col] == 0)) {
                neighbors.add(new Integer[] {row+2, col});
            }
            if (verify_bord(col-2, largeur) && verify_bord(row, longueur) && verify_for_image(tableau, row, col-2)) {
                neighbors.add(new Integer[] {row, col-2});
            }
            if (verify_bord(col+2, largeur) && verify_bord(row, longueur) && verify_for_image(tableau, row, col+2)) {
                neighbors.add(new Integer[] {row, col+2});
            }
            if (!neighbors.isEmpty()) {
                Integer[] next = neighbors.get(random.nextInt(neighbors.size()));
                int nextRow = next[0];
                int nextCol = next[1];
                

                // Enlever le mur entre les deux cases
                tableau[(row+nextRow)/2][(col+nextCol)/2] = chemin; // la case entre les deux est un chemin (1)


                // Ajouter la case voisine à la pile
                stack.push(new Integer[] {nextRow, nextCol});

                // Marquer la case voisine comme visitée
                tableau[nextRow][nextCol] = chemin; // la case voisine est un chemin (1)
                //change_chemin(tableau, row, nextRow, col, nextCol);
                if(row == nextRow){
                    ajoute_fond(tableau, row, col);
                    ajoute_fond(tableau, (row+nextRow)/2, (col+nextCol)/2);
                }else if(nextRow > row){
                    ajoute_fond(tableau, row, col);
                }
            }
            else{
                tableau[row][col] = fin;
                ajoute_fond(tableau, row, col);
                break;
            }
        }
        change_2_to_0(tableau);
        return tableau;
    }

    private static void ajoute_fond(int[][] tab, int x, int y){
        if(tab[x-1][y] == 0 && tab[x-2][y] == 0){
            tab[x-1][y] = 4;
            tab[x-2][y] = 5;
        }
        
        if(tab[x+1][y] == 0){
            tab[x+1][y] = 2;
        }
        
    }

    private static boolean verify_bord(int x1, int x2){
        if(x1-2 <= 0 || x1+2 >= x2){
            return false;
        }return true;
    }

    private static void afficheTab(int[][]tab){
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RESET = "\u001B[0m";
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[i].length;j++){
                if(tab[i][j] != 0){
                    System.out.print(ANSI_YELLOW + tab[i][j]+" " +ANSI_RESET);
                }else{
                    System.out.print(tab[i][j]+" " );
                }
                
            }
            System.out.println();
        }
    }

    private static boolean verify_for_image(int[][] tab, int x, int y){
        return tab[x+1][y] == 0 && tab[x-1][y] == 0 && tab[x-2][y] == 0;
    }

    private static void change_2_to_0(int[][] tab){
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[i].length;j++){
                if(tab[i][j] == 2){
                    tab[i][j] = 0;
                }
            }
        }
    }

    public int[][] getMap2(){
        return this.map;
    }
    
    // Test de la génération aléatoire de map

    // public static void main(String[] args){
    //     Map m = new Map(22,38);
    //     System.out.println("");
    //     afficheTab(m.getMap2());
    // }
    

}


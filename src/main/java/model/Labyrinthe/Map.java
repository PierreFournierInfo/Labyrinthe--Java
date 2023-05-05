package main.java.model.Labyrinthe;

import java.util.Random;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Map{

    final int [][]map;

    public Map(int longueur,int largeur){
        this.map= genererLabyrinthe(longueur, largeur);
    }


    public static int[][] genererLabyrinthe(int longueur, int largeur) {
    int[][] tableau = new int[longueur][largeur];

    // Initialiser toutes les cases avec des murs (1)
    for (int i = 0; i < longueur; i++) {
        for (int j = 0; j < largeur; j++) {
            tableau[i][j] = 1;
        }
    }

    // Choisir une case de départ aléatoire
    Random random = new Random();
    int startRow = random.nextInt(longueur);
    int startCol = random.nextInt(largeur);
    tableau[startRow][startCol] = 0; // la case de départ est un chemin (0)

    // Appliquer l'algorithme Recursive Backtracker
    Stack<Integer[]> stack = new Stack<>(); // pile pour stocker les cases visitées
    stack.push(new Integer[] {startRow, startCol});

    while (!stack.isEmpty()) {
        Integer[] current = stack.pop();
        int row = current[0];
        int col = current[1];

        // Choisir une case voisine aléatoire non visitée
        List<Integer[]> neighbors = new ArrayList<>();
        if (row > 1 && tableau[row-2][col] == 1) {
            neighbors.add(new Integer[] {row-2, col});
        }
        if (row < longueur-2 && tableau[row+2][col] == 1) {
            neighbors.add(new Integer[] {row+2, col});
        }
        if (col > 1 && tableau[row][col-2] == 1) {
            neighbors.add(new Integer[] {row, col-2});
        }
        if (col < largeur-2 && tableau[row][col+2] == 1) {
            neighbors.add(new Integer[] {row, col+2});
        }
        if (!neighbors.isEmpty()) {
            Integer[] next = neighbors.get(random.nextInt(neighbors.size()));
            int nextRow = next[0];
            int nextCol = next[1];

            // Enlever le mur entre les deux cases
            tableau[(row+nextRow)/2][(col+nextCol)/2] = 0; // la case entre les deux est un chemin (0)

            // Ajouter la case voisine à la pile
            stack.push(new Integer[] {nextRow, nextCol});

            // Marquer la case voisine comme visitée
            tableau[nextRow][nextCol] = 0; // la case voisine est un chemin (0)
        }
    }

    return tableau;
    }

    public static void afficheTab(int[][]tab){
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[i].length;j++){
                System.out.print(tab[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int[][] getMap(){
        return this.map;
    }

    

}


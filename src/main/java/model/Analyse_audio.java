package main.java.model;

import java.io.*;
import java.util.Scanner;

public class Analyse_audio {
	
	public int nbrLocuteur() {
		int nbrLoc = 0;
		try {
			FileInputStream file = new FileInputStream("./src/resources/RecordAudio.txt"); //Là où est normalement enregistré le fichier généré par LIUM
			Scanner sc = new Scanner(file);		
			while(sc.hasNextLine()) {
				if(sc.nextLine().charAt(0) == ';') { //chaque nouveau segment avec locuteur commence par ";"
					nbrLoc++;
				}
			}
			sc.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return nbrLoc;
	}
	
	public static void main(String[] args) {
		Analyse_audio a = new Analyse_audio();
		System.out.println(a.nbrLocuteur());
	}
}

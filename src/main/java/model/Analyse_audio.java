package main.java.model;

import java.io.*;
import java.util.Scanner;

public class Analyse_audio {

	public int nbrLocuteur() {
		int nbrLoc = 0;
		try {
			FileInputStream file = new FileInputStream("./src/resources/RecordAudio.txt"); //Là où est normalement enregistré le fichier généré par LIUM (pensez à télécharger celui que j'ai mis dans les ressources de cette branche pour tester)
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

	public int[] nbrHommesFemmes() {
		int [] hommesFemmes = new int[2]; //case 0 correspond au nombre d'hommes, case 1 correspond au nombre de femmes	
		String s = " ";
		try {
			FileInputStream file = new FileInputStream("./src/resources/RecordAudio.txt");
			Scanner sc = new Scanner(file);		
			while(sc.hasNextLine() && sc.hasNext()) {
				s = sc.nextLine();
				if(s.charAt(0) == ';') {	
					s = sc.nextLine();
					for(int i = 0; i<s.length(); ++i) { //dans la ligne la première lettre majuscule correspond au genre (voir issue "Lire le site suivant")
						if(s.charAt(i) == 'F') { 
							hommesFemmes[1]++;
						}
						if(s.charAt(i) == 'M')  {
							hommesFemmes[0]++;
						}
					}				
				}
			}
			sc.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return hommesFemmes;
	}
    
    //Main pour tester
	/*public static void main(String[] args) {
		Analyse_audio a = new Analyse_audio();
		System.out.println(a.nbrLocuteur());
		System.out.println(a.nbrHommesFemmes()[0] + " " + a.nbrHommesFemmes()[1]);
	}*/
}

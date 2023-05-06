package main.java.model.Audio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Analyse_audio {

	public int nbrLocuteur() {
		int nbrLoc = 0;
		try {
			FileInputStream file = new FileInputStream("src/resources/Audio/RecordAudio.txt"); // Là où est normalement
																								// enregistré le fichier
																								// généré par LIUM
																								// (pensez à télécharger
																								// celui que j'ai mis
																								// dans les ressources
																								// de cette branche pour
																								// tester)
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				if (sc.nextLine().charAt(0) == ';') { // chaque nouveau segment avec locuteur commence par ";"
					nbrLoc++;
				}
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nbrLoc;
	}

	public int[] nbrHommesFemmes() {
		int[] hommesFemmes = { -1, -1 }; // case 0 correspond au nombre d'hommes, case 1 correspond au nombre de femmes
		String s = " ";
		try {
			FileInputStream file = new FileInputStream("src/resources/Audio/RecordAudio.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine() && sc.hasNext()) {
				s = sc.nextLine();
				if (s.charAt(0) == ';') {

					s = sc.nextLine();
					for (int i = 0; i < s.length(); ++i) { // dans la ligne la première lettre majuscule correspond au
															// genre (voir issue "Lire le site suivant")
						if (s.charAt(i) == 'F') {
							hommesFemmes[1]++;
						}
						if (s.charAt(i) == 'M') {
							hommesFemmes[0]++;
						}
					}
				}
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hommesFemmes;
	}

	public Stack<String> direction() {
		Stack<String> dir = new Stack<String>();
		String s = " ";
		try {
			FileInputStream file = new FileInputStream("src/resources/Audio/RecordAudio.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine() && sc.hasNext()) {
				s = sc.nextLine();
				String[] mot = s.split(" ");
				for (int i = mot.length - 1; i >= 0; --i) {
					System.out.println(mot[i]);
					if (mot[i].contains("gauche")) {
						dir.push("gauche");
					} else if (mot[i].contains("droite")) {
						dir.push("droite");
					} else if (mot[i].contains("haut")) {
						dir.push("haut");
					} else if (mot[i].contains("bas")) {
						dir.push("bas");
					}
				}
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dir;
	}

	public String directionUnique() {
		String s = " ";
		try {
			FileInputStream file = new FileInputStream("src/resources/Audio/RecordAudio.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine() && sc.hasNext()) {
				s = sc.nextLine();
				if (s.contains("gauche")) {
					System.out.println("gauche");
					return "left";
				}
				if (s.contains("droite")) {
					System.out.println("droite");
					return "right";
				}
				if (s.contains("haut")) {
					System.out.println("haut");
					return "up";
				}
				if (s.contains("bas")) {
					System.out.println("bas");
					return "down";
				}
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Direction non reconnue !";
	}
}

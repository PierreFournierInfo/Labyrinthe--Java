package main.java.model;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Analyse_audio {

	public int nbrLocuteur() {
		int nbrLoc = 0;
		try {
			FileInputStream file = new FileInputStream("src/resources/Audio/RecordAudio.txt"); //Là où est normalement enregistré le fichier généré par LIUM (pensez à télécharger celui que j'ai mis dans les ressources de cette branche pour tester)
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
		int [] hommesFemmes = {-1, -1}; //case 0 correspond au nombre d'hommes, case 1 correspond au nombre de femmes	
		String s = " ";
		try {
			FileInputStream file = new FileInputStream("src/resources/Audio/RecordAudio.txt");
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

	public Stack<String> direction(){
		Stack<String> dir = new Stack<String>();
		String s = " ";
		try {
			FileInputStream file = new FileInputStream("./RecordAudio.txt");
			Scanner sc = new Scanner(file);		
			while(sc.hasNextLine() && sc.hasNext()) {
				 s = sc.nextLine();
				 String[] mot = s.split(" ");
				 for(int i = mot.length-1; i>=0; --i){
				 	if(mot[i].equals("gauche") || mot[i].equals("droite") || mot[i].equals("haut") || mot[i].equals("bas")){
				 		dir.push(mot[i]);
				 	}				 	
				 }
			}
			sc.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return dir;
	}

	public void cutTxt(){
		String inputFile = "RecordAudio.txt"; // path of input file
        String outputFile = "RecordAudioBis.txt"; // path of output file
        String keyword = ";;"; // keyword to extract after
        
        try {
            // Open input file
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            
            // Open output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            
            String line;
            String lastLine = null;
            boolean foundKeyword = false;
            
            // Read lines from input file
            while ((line = reader.readLine()) != null) {
                // Check for keyword
                if (line.contains(keyword)) {
                    foundKeyword = true;
                    lastLine = line; // update last line containing keyword
                }
            }

			// Reset reader
			reader.close();
			reader = new BufferedReader(new FileReader(inputFile));

			while((line = reader.readLine()) != null){
				if(line.equals(lastLine)){
					writer.write(line);
					writer.newLine();
					break;
				}
			}
            
			while((line = reader.readLine()) != null){
				writer.write(line);
				writer.newLine();
			}
            
            // Close files
			reader.close();
            writer.close();
            
            System.out.println("Extracted text file successfully!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    //Main pour tester
	/*public static void main(String[] args) {
		Analyse_audio a = new Analyse_audio();
		System.out.println(a.nbrLocuteur());
		System.out.println(a.nbrHommesFemmes()[0] + " " + a.nbrHommesFemmes()[1]);
		Stack<String> b = a.direction();
		while(!b.empty()){
		}
	}*/
}

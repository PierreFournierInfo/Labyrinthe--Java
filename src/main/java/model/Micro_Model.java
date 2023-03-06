package main.java.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.sound.sampled.*;

public class Micro_Model {

	//où est enregistré le fichier
	File wavFile = new File(/*"./src/resources*/"./RecordAudio.wav");

	//format wav
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	final long RECORD_TIME = 10000; //10 secondes
	TargetDataLine line;

	AudioFormat getAudioFormat() {
		float sampleRate = 16000;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = true;
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,channels, signed, bigEndian);			
		return format;
	}

	public void start() { //Capture et enregistre le son 
		try {
			AudioFormat format = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);		
			if (!AudioSystem.isLineSupported(info)) { 
				System.out.println("Erreur...");
				System.exit(0);
			}
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();   //Lance le micro
			System.out.println("Micro...");
			AudioInputStream ais = new AudioInputStream(line);
			System.out.println("Enregistrement...");
			AudioSystem.write(ais, fileType, wavFile); //Commence l'enregistrement
		} 
		catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void finish() throws InterruptedException { //Arrête le micro
		line.stop();
		line.close();
		System.out.println("Fin micro...");
		try {
			ProcessBuilder pb=new ProcessBuilder("/bin/bash","/home/xiao/Bureau/2023-sb_2-gc/test.sh");
			Process p=pb.start();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getTemps() {
		return this.RECORD_TIME;
	}
   
    //Pour tester 
/* 	public static void main(String[] args) {
		final Micro_Model recorder = new Micro_Model();
		Thread stopper = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(recorder.RECORD_TIME);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				recorder.finish();
			}
		});
		stopper.start();
		recorder.start(); */
		//test 1
		/*Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        if(i==0){
		    ProcessBuilder pb=new ProcessBuilder("/bin/bash","test.sh");
		    try {
				Process p=pb.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }*/
        
        //test 2
        
/*             try {
				Thread.sleep(10000);//c'est 10 secondes	
				ProcessBuilder pb=new ProcessBuilder("/bin/bash","test.sh");
				Process p=pb.start();
			} 
            catch (InterruptedException e) {
				e.printStackTrace();
			} 		
			catch (IOException e) {
				e.printStackTrace();
			} */
        
        //test 3            
           /* ProcessBuilder pb=new ProcessBuilder("/bin/bash","test.sh");
		    try {
				Process p=pb.start();
				System.out.println("si tu vois ça alors que tu enregistres c'est que la commande s'active trop tôt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		    
       
	//}
}

package main.java.model;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Scanner;

import javax.sound.sampled.*;
import javax.sound.sampled.AudioFileFormat.Type;

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

	public void finish() throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException { //Arrête le micro
		line.stop();
		line.close();
		System.out.println("Fin micro...");
		fusion();
		Thread stopper = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(15000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		stopper.start();
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

	/*public void fusion(){
		String inputFile1 = "Base.wav"; // path of first input file
        String inputFile2 = "RecordAudio.wav"; // path of second input file
        String outputFile = "RecordAudioBis.wav"; // path of output file
        
        try {
            // Open input files
            FileInputStream inputStream1 = new FileInputStream(inputFile1);
            FileInputStream inputStream2 = new FileInputStream(inputFile2);
            
            // Open output file
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            
            // Read WAV headers
            byte[] header1 = new byte[44];
            inputStream1.read(header1);
            
            byte[] header2 = new byte[44];
            inputStream2.read(header2);
            
            // Write WAV headers to output file
            outputStream.write(header1);
            
            // Calculate length of data section
            byte[] lengthBytes1 = new byte[4];
            System.arraycopy(header1, 40, lengthBytes1, 0, 4);
            int length1 = byteArrayToInt(lengthBytes1);
            
            byte[] lengthBytes2 = new byte[4];
            System.arraycopy(header2, 40, lengthBytes2, 0, 4);
            int length2 = byteArrayToInt(lengthBytes2);
            
            int totalLength = length1 + length2;
            
            // Merge data sections
            byte[] data1 = new byte[length1];
            inputStream1.read(data1);
            
            byte[] data2 = new byte[length2];
            inputStream2.read(data2);
            
            byte[] mergedData = new byte[totalLength];
            System.arraycopy(data1, 0, mergedData, 0, length1);
            System.arraycopy(data2, 0, mergedData, length1, length2);
            
            // Write merged data to output file
            outputStream.write(mergedData);
            
            // Close files
            inputStream1.close();
            inputStream2.close();
            outputStream.close();
            
            System.out.println("Merged WAV files successfully!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}*/
	
	public void fusion() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		AudioInputStream audio1 = AudioSystem.getAudioInputStream(new File("Base.wav"));
		AudioInputStream audio2 = AudioSystem.getAudioInputStream(new File("RecordAudio.wav"));
	
		// Create output audio stream with combined length
		AudioFormat format = audio1.getFormat();
		long lengthFrames = audio1.getFrameLength() + audio2.getFrameLength();
		AudioInputStream audioCombined = new AudioInputStream(new SequenceInputStream(audio1, audio2), format, lengthFrames);
	
		// Write output audio stream to file
		AudioSystem.write(audioCombined, AudioFileFormat.Type.WAVE, new File("RecordAudioBis.wav"));
	}	

	public void cut(){
		String inputFile = "Base.wav"; // path of input file
        String outputFile = "BaseBis.wav"; // path of output file
        int cutTimeSeconds = 10; // time in seconds to cut from the beginning
        
        try {
            // Open input file
            FileInputStream inputStream = new FileInputStream(inputFile);
            
            // Open output file
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            
            // Read WAV headers
            byte[] header = new byte[44];
            inputStream.read(header);
            outputStream.write(header);
            
            // Calculate length of data section
            byte[] lengthBytes = new byte[4];
            System.arraycopy(header, 40, lengthBytes, 0, 4);
            int length = byteArrayToInt(lengthBytes);
            
            // Calculate length of cut data
            int cutLength = (int) (cutTimeSeconds * 44100 * 2); // 44100 is the sample rate, 2 is the number of bytes per sample (16-bit PCM)
            
            // Cut data section
            byte[] cutData = new byte[cutLength];
            inputStream.read(cutData);
            outputStream.write(cutData);
            
            // Close files
            inputStream.close();
            outputStream.close();
            
            System.out.println("Cut WAV file successfully!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < bytes.length; i++) {
            value += (bytes[i] & 0xFF) << (8 * i);
        }
        return value;
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

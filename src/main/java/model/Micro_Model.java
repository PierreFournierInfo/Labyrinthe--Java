package main.java.model;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Micro_Model {

	//où est enregistré le fichier
	File wavFile = new File("./src/resources/RecordAudio.wav");

	//format wav
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	final long RECORD_TIME = 10000; //10 secondes
	TargetDataLine line;

	AudioFormat getAudioFormat() {
		float sampleRate = 16000;
		int sampleSizeInBits = 8;
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

	public void finish() { //Arrête le micro
		line.stop();
		line.close();
		System.out.println("Fin micro...");
	}

	public long getTemps() {
		return this.RECORD_TIME;
	}

	/*public static void main(String[] args) {
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
		recorder.start();
	}*/
}

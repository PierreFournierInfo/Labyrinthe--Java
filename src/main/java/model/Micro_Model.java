package main.java.model;
import javax.sound.sampled.*;

public class Micro_Model {

	final long RECORD_TIME = 10000; //10 secondes
	TargetDataLine line;

	AudioFormat getAudioFormat() {
		float sampleRate = 16000;
		int sampleSizeInBits = 8;
		int channels = 2;
		boolean signed = true;
		boolean bigEndian = true;
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,channels, signed, bigEndian);			
		return format;
	}

	public void start() { //Capture le son 
		try {
			AudioFormat format = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);		
			if (!AudioSystem.isLineSupported(info)) { //Vérifie si le système est compatible
				System.out.println("Erreur...");
				System.exit(0);
			}
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();   //Lance le micro
			System.out.println("Micro...");
		} 
		catch (LineUnavailableException ex) {
			ex.printStackTrace();
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

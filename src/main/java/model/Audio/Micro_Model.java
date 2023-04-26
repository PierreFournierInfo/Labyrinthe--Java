package main.java.model.Audio;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Arrays;
import javax.sound.sampled.*;

public class Micro_Model {

	//où est enregistré le fichier
	File wavFile = new File("src/resources/Audio/RecordAudio.wav");

	//format wav
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	final long RECORD_TIME = 20000; //20 secondes
	final long RECORD_TIME2 = 5000;
	TargetDataLine line;

	AudioFormat getAudioFormat() {
		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float sampleRate = 16000.0f;
		int sampleSizeInBits = 16;
		int channels = 2;
		int frameSize = (sampleSizeInBits / 8) * channels;
		float frameRate = sampleRate;
		//boolean signed = true;
		boolean bigEndian = true;
		AudioFormat format = new AudioFormat(encoding, sampleRate, sampleSizeInBits, channels, frameSize, frameRate, bigEndian);			
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

	public void finish(){ //Arrête le micro
 		line.stop();
		line.close();
		System.out.println("Fin micro...");
 		try{
			fusion();
			String[] arguments = {"--fInputDesc=audio16kHz2sphinx:sphinx,1:1:0:0:0:0,35,0:0:0:0", "--fInputMask=src/resources/Audio/RecordAudioBis.wav", "--sOutputMask=src/resources/Audio/RecordAudio.txt", "--sOutputFormat=txt", "--doCEClustering", "test"};
			ProcessBuilder processus = new ProcessBuilder("java", "-Xmx1024m", "-jar", "./LIUM_SpkDiarization-8.4.1.jar");
			processus.command().addAll(Arrays.asList(arguments));

			processus.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			processus.redirectError(ProcessBuilder.Redirect.INHERIT);

			Process process = processus.start();

			int codeSortie = process.waitFor();

			if(codeSortie == 0){
				System.out.println("Succès");
			}else{
				System.out.println("Echec");
			}
			
		} catch (IOException e){
			e.printStackTrace();
		} catch (InterruptedException e){
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void finish2(){
		line.stop();
		line.close();
		try{
			String [] arguments = {"src/resources/Audio/RecordAudio.wav", "--language", "French", "--model", "base", "-o", "src/resources/Audio", "-ftxt"};
			ProcessBuilder processus = new ProcessBuilder("whisper");
			processus.command().addAll(Arrays.asList(arguments));

			processus.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			processus.redirectError(ProcessBuilder.Redirect.INHERIT);

			Process process = processus.start();

			int codeSortie = process.waitFor();

			if(codeSortie == 0){
				System.out.println("Succès");
			}else{
				System.out.println("Echec");
			}		
		}
		catch (IOException e){
			e.printStackTrace();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}


	public long getTemps(boolean jeu) {
		if(jeu){
			return this.RECORD_TIME;
		}
		else{
			return this.RECORD_TIME2;
		}
		
	}
	
	public void fusion() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		AudioInputStream audio1 = AudioSystem.getAudioInputStream(new File("src/resources/Audio/Base.wav"));
		AudioInputStream audio2 = AudioSystem.getAudioInputStream(new File("src/resources/Audio/RecordAudio.wav"));
	
		// Creer un fichier audio de longueur des deux fichiers additionner
		AudioFormat format = audio1.getFormat();
		long longueur = audio1.getFrameLength() + audio2.getFrameLength();
		AudioInputStream audioFusionne = new AudioInputStream(new SequenceInputStream(audio2, audio1), format, longueur);
	
		// Ecrit en sortie le fichier audio
		AudioSystem.write(audioFusionne, AudioFileFormat.Type.WAVE, new File("src/resources/Audio/RecordAudioBis.wav"));
	}	
}

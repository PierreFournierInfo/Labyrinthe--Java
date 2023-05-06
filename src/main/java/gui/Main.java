package main.java.gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import main.java.gui.Menu.Menu;

public class Main {

    private Menu menu;

    public Main() {
        this.menu = new Menu();
        this.menu.setVisible(true);
    }

    public static boolean verify_exist(String s) {
        File f = new File(s);
        return f.exists();
    }

    public static void reset() {
        try {
            if (verify_exist("src/resources/Audio/RecordAudio.txt")) {
                Files.delete(Paths.get("src/resources/Audio/RecordAudio.txt"));
            }
            if (verify_exist("src/resources/Audio/RecordAudio.wav")) {
                Files.delete(Paths.get("src/resources/Audio/RecordAudio.wav"));
            }
            if (verify_exist("src/resources/Audio/RecordAudioBis.wav")) {
                Files.delete(Paths.get("src/resources/Audio/RecordAudioBis.wav"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        reset();
        new Main();
    }

}
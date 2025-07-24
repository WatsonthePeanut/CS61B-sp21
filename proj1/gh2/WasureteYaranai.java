package gh2;

import java.io.File;

public class WasureteYaranai {
    public static void main(String[] args) {
        File midiFile = new File("D:/Projects/CS61B-sp21/proj1/gh2/WasureteYaranai.mid");
        GuitarPlayer player = new GuitarPlayer(midiFile);
        player.play();
    }
}

package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static double calFreq(int i) {
        return 440 * Math.pow(2, (i - 24) / 12.0);
    }

    public static void play(GuitarString[] instrument) {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.contains(String.valueOf(key))) {
                    int index = keyboard.indexOf(key);
                    instrument[index].pluck();
                } else {
                    continue;
                }
            }

            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); ++i) {
                sample += instrument[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < keyboard.length(); ++i) {
                instrument[i].tic();
            }
        }
    }

    public static void playTheGuitar() {
        GuitarString[] guitarStrings = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); ++i) {
            guitarStrings[i] = new GuitarString(calFreq(i));
        }
        play(guitarStrings);
    }

    public static void playTheHarp() {
        Harp[] harp = new Harp[keyboard.length()];
        for (int i = 0; i < keyboard.length(); ++i) {
            harp[i] = new Harp(calFreq(i));
        }
        play(harp);
    }

    public static void playTheDrum() {
        Drum[] drum = new Drum[keyboard.length()];
        for (int i = 0; i < keyboard.length(); ++i) {
            drum[i] = new Drum(calFreq(i));
        }
        play(drum);
    }

    public static void main(String[] args) {
        playTheGuitar();
//        playTheHarp();
//        playTheDrum();
    }
}

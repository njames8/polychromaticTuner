package com.nickjames.polychromatictuner.notes;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nick on 9/24/2016.
 */
public enum Note {
           // all eight octaves for each note
    C("C", Arrays.asList(16.35, 32.7, 65.41, 130.8, 261.6, 523.3, 1047.0, 2093.0, 4186.0)),
    C_SHARP("C#", Arrays.asList(17.32, 34.65, 69.3, 138.6, 277.2, 554.4, 1109.0, 2217.0, 4435.0)),
    D("D", Arrays.asList(18.35, 36.71, 73.42, 146.8, 293.7, 587.3, 587.3, 1175.0, 2349.0, 4699.0)),
    D_SHARP("D#", Arrays.asList(19.45, 38.89, 77.78, 155.6, 311.1, 622.3, 1245.0, 2489.0, 4978.0)),
    E("E", Arrays.asList(20.6, 41.2, 82.41, 164.8, 329.6, 659.3, 1319.0, 2637.0, 5274.0)),
    F("F", Arrays.asList(21.83, 43.65, 87.31, 174.6, 349.2, 698.5, 1397.0, 2794.0, 5588.0)),
    F_SHARP("F#", Arrays.asList(23.12, 46.25, 92.5, 185.0, 370.0, 740.0, 1480.0, 2960.0, 5920.0)),
    G("G", Arrays.asList(24.5, 49.0, 98.0, 196.0, 392.0, 784.0, 1568.0, 3136.0, 6272.0)),
    G_SHARP("G#", Arrays.asList(25.96, 51.91, 103.8, 207.7, 415.3, 830.6, 1661.0, 3322.0, 6645.0)),
    A("A", Arrays.asList(27.5, 55.0, 110.0, 220.0, 440.0, 880.0, 1760.0, 3520.0, 7040.0)),
    A_SHARP("A#", Arrays.asList(29.14,58.27,116.5, 233.1, 466.2, 932.3, 1865.0, 3729.0, 7459.0)),
    B("B", Arrays.asList(30.87, 61.74, 123.5, 246.9, 493.9, 987.8, 1976.0, 3951.0, 7902.0));

    private String displayName;
    private List<Double> frequencies;

    Note(String displayName, List<Double> frequencies){
        this.displayName = displayName;
        this.frequencies = frequencies;
    }

    public Note getNoteByFrequency(double freq) {
        for (Note n : Note.values()) {
            if (n.frequencies.contains(freq)) {
                return n;
            }
        }
        return null;
    }

    public String getDisplayName() {
        return displayName;
    }
}

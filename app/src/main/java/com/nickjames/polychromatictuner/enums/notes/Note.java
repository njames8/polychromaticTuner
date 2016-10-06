package com.nickjames.polychromatictuner.enums.notes;


/**
 * Each note in each octave with its frequency in Hz
 * Created by Nick on 9/24/2016.
 */
public enum Note {
    // all eight octaves for each note
    C1("C", 32.7),
    C2("C", 65.41),
    C3("C", 130.8),
    C4("C", 261.6),
    C5("C", 523.3),
    C6("C", 1047.0),
    C7("C", 2093.0),
    C8("C", 4186.0),

    C_SHARP_1("C#", 34.65),
    C_SHARP_2("C#", 69.3),
    C_SHARP_3("C#", 138.6),
    C_SHARP_4("C#", 277.2),
    C_SHARP_5("C#", 554.4),
    C_SHARP_6("C#", 1109.0),
    C_SHARP_7("C#", 2217.0),
    C_SHARP_8("C#", 4435.0),

    D1("D", 36.71),
    D2("D", 73.42),
    D3("D", 146.8),
    D4("D", 293.7),
    D5("D", 587.3),
    D6("D", 1175.0),
    D7("D", 2349.0),
    D8("D", 4699.0),

    D_SHARP_1("D#", 38.89),
    D_SHARP_2("D#", 77.78),
    D_SHARP_3("D#", 155.6),
    D_SHARP_4("D#", 311.1),
    D_SHARP_5("D#", 622.3),
    D_SHARP_6("D#", 1245.0),
    D_SHARP_7("D#", 2489.0),
    D_SHARP_8("D#", 4978.0),

    E1("E", 41.2),
    E2("E", 82.41),
    E3("E", 164.8),
    E4("E", 329.6),
    E5("E", 659.3),
    E6("E", 1319.0),
    E7("E", 2637.0),
    E8("E", 5274.0),

    F1("F", 43.65),
    F2("F", 87.31),
    F3("F", 174.6),
    F4("F", 349.2),
    F5("F", 698.5),
    F6("F", 1397.0),
    F7("F", 2794.0),
    F8("F", 5588.0),

    F_SHARP_1("F#", 46.25),
    F_SHARP_2("F#", 92.5),
    F_SHARP_3("F#", 185.0),
    F_SHARP_4("F#", 370.0),
    F_SHARP_5("F#", 740.0),
    F_SHARP_6("F#", 1480.0),
    F_SHARP_7("F#", 2960.0),
    F_SHARP_8("F#", 5920.0),

    G1("G", 49.0),
    G2("G", 98.0),
    G3("G", 196.0),
    G4("G", 392.0),
    G5("G", 784.0),
    G6("G", 1568.0),
    G7("G", 3136.0),
    G8("G", 6272.0),

    G_SHARP_1("G#", 51.91),
    G_SHARP_2("G#", 103.8),
    G_SHARP_3("G#", 207.7),
    G_SHARP_4("G#", 415.3),
    G_SHARP_5("G#", 830.6),
    G_SHARP_6("G#", 1661.0),
    G_SHARP_7("G#", 3322.0),
    G_SHARP_8("G#", 6645.0),

    A1("A", 55.0),
    A2("A", 110.0),
    A3("A", 220.0),
    A4("A", 440.0),
    A5("A", 880.0),
    A6("A", 1760.0),
    A7("A", 3520.0),
    A8("A", 7040.0),

    A_SHARP_1("A#", 58.27),
    A_SHARP_2("A#", 116.5),
    A_SHARP_3("A#", 233.1),
    A_SHARP_4("A#", 466.2),
    A_SHARP_5("A#", 932.3),
    A_SHARP_6("A#", 1865.0),
    A_SHARP_7("A#", 3729.0),
    A_SHARP_8("A#", 7459.0),

    B1("B", 61.74),
    B2("B", 123.5),
    B3("B", 246.9),
    B4("B", 493.9),
    B5("B", 987.8),
    B6("B", 1976.0),
    B7("B", 3951.0),
    B8("B", 7902.0);


    private String displayName;
    private Double frequency;

    Note(String displayName, Double frequency){
        this.displayName = displayName;
        this.frequency = frequency;
    }

    public String getDisplayName() {
        return displayName;
    }
}

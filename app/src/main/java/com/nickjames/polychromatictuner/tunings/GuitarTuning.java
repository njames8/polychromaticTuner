package com.nickjames.polychromatictuner.tunings;

import com.nickjames.polychromatictuner.notes.Note;

import java.util.Arrays;
import java.util.List;

import static com.nickjames.polychromatictuner.notes.Note.*;

/**
 * Created by Nick on 9/24/2016.
 */
public enum GuitarTuning implements Tuning {
    //Standard tunings and variations
    STANDARD("Standard", E, A, D, G, B, E),
    E_FLAT("E Flat", D_SHARP, G_SHARP, C_SHARP, A_SHARP, D_SHARP),
    DROP_D("Drop D", D, A, D, G, B, E),
    DROP_D_FLAT("Drop D Flat", C_SHARP, G_SHARP, C_SHARP, A_SHARP, D_SHARP),
    D_STANDARD("D Standard", D, G, C, F, A, D),
    DROP_C("Drop C", C, G, C, F, A, D),
    // Open Maj tunings
    OPEN_C("Open C maj", C, G, C, G, C, E),

    OPEN_D("Open D maj", D, A, D, F_SHARP, A, D),
    OPEN_E("Open E maj", E, B, E, G_SHARP, B, E),
    OPEN_F("Open F maj", C, F, C, F, A, C),
    OPEN_G("Open G maj", D, G, D, G, B, D),
    OPEN_A("Open A maj", E, A, C_SHARP, E, A, E),
    OPEN_B("Open B maj", B, F_SHARP, B, F_SHARP, B, D_SHARP),
    // OPEN MIN TUNINGS
    OPEN_C_MIN("Open C min", C, G, C, G, C, D_SHARP),

    OPEN_D_MIN("Open D min", D, A, D, F, A, D),
    OPEN_E_MIN("Open E min", E, B, E, G, B, E),
    OPEN_F_MIN("Open F min", C, F, C, F, G_SHARP, C),
    OPEN_G_MIN("Open G min", D, G, D, G, A_SHARP, D),
    OPEN_A_MIN("Open A min", E, A, C, E, A, E),
    OPEN_B_MIN("Open B min", B, F_SHARP, B, F_SHARP, B, D),

    ;
    public static final long SerialId = 1L;
    String displayName;
    List<Note> notes;



    GuitarTuning(String displayName, Note... notes) {
        this.displayName = displayName;
        this.notes = Arrays.asList(notes);
    }

    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    @Override
    public int getOrdinal(){
        return super.ordinal();
    }

}

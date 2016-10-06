package com.nickjames.polychromatictuner.enums.tunings;

import com.nickjames.polychromatictuner.enums.notes.Note;

import java.util.Arrays;
import java.util.List;

import static com.nickjames.polychromatictuner.enums.notes.Note.*;

/**
 * All of the common guitar tunings
 * Created by Nick on 9/24/2016.
 */
public enum GuitarTuning implements Tuning {
    //Standard tunings and variations
    STANDARD("Standard", E2, A2, D3, G3, B3, E4),
    E_FLAT("E Flat", D_SHARP_2, G_SHARP_2, C_SHARP_3, A_SHARP_3, D_SHARP_4),
    DROP_D("Drop D", D2, A2, D3, G3, B3, E4),
    DROP_D_FLAT("Drop D Flat", C_SHARP_2, G_SHARP_2, C_SHARP_3, A_SHARP_3, D_SHARP_4),
    D_STANDARD("D Standard", D2, G2, C3, F3, A3, D4),
    DROP_C("Drop C", C2, G2, C3, F3, A3, D4),
    // Open Maj tunings
    OPEN_C("Open C maj", C2, G2, C3, G3, C4, E4),

    OPEN_D("Open D maj", D2, A2, D3, F_SHARP_3, A3, D4),
    OPEN_E("Open E maj", E2, B2, E3, G_SHARP_3, B3, E4),
    OPEN_F("Open F maj", C2, F2, C3, F3, A3, C4),
    OPEN_G("Open G maj", D2, G2, D3, G3, B3, D4),
    OPEN_A("Open A maj", E2, A2, C_SHARP_3, E3, A3, E4),
    OPEN_B("Open B maj", B1, F_SHARP_2, B2, F_SHARP_3, B3, D_SHARP_4),
    // OPEN MIN TUNINGS
    OPEN_C_MIN("Open C min", C2, G2, C3, G3, C4, D_SHARP_4),

    OPEN_D_MIN("Open D min", D2, A2, D3, F3, A3, D4),
    OPEN_E_MIN("Open E min", E2, B2, E3, G3, B3, E4),
    OPEN_F_MIN("Open F min", C2, F2, C3, F3, G_SHARP_3, C4),
    OPEN_G_MIN("Open G min", D2, G2, D3, G3, A_SHARP_3, D4),
    OPEN_A_MIN("Open A min", E2, A2, C3, E3, A3, E4),
    OPEN_B_MIN("Open B min", B1, F_SHARP_2, B2, F_SHARP_3, B3, D4),

    ;
    String displayName;
    List<Note> notes;

    GuitarTuning(String displayName, Note... notes) {
        this.displayName = displayName;
        this.notes = Arrays.asList(notes);
    }

    @Override
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

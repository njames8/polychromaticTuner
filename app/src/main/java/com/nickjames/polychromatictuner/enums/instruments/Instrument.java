package com.nickjames.polychromatictuner.enums.instruments;

import com.nickjames.polychromatictuner.enums.tunings.GuitarTuning;
import com.nickjames.polychromatictuner.enums.tunings.Tuning;

/**
 * Represents an instrument. Each instrument has an enum holding all of the common tunings of that
 * instrument
 * Created by Nick on 9/24/2016.
 */

public enum Instrument {
    GUITAR("Guitar", GuitarTuning.values());

    String displayName;
    Tuning[] tunings;
    Instrument(String displayName, Tuning[] tunings) {
        this.displayName = displayName;
        this.tunings = tunings;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Tuning[] getTunings() {
        return tunings;
    }

    public Tuning getTuningByDisplayName(String tuningDisplayName) {
        for (Tuning t : tunings) {
            if (t.getDisplayName().equals(tuningDisplayName)) {
                return t;
            }
        }
        return null;
    }
}

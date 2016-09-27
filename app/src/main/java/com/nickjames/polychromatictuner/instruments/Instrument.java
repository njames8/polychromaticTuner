package com.nickjames.polychromatictuner.instruments;

import com.nickjames.polychromatictuner.tunings.GuitarTuning;
import com.nickjames.polychromatictuner.tunings.Tuning;

/**
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
}

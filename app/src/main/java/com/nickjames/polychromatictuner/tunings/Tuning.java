package com.nickjames.polychromatictuner.tunings;

/**
 * Represents a tuning of an instrument. Enums will implement this for each tuning of an instrument.
 * Created by Nick on 9/24/2016.
 */
public interface Tuning {
    String getDisplayName();
    int getOrdinal();
}

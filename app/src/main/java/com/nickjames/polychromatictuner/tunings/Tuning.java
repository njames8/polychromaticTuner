package com.nickjames.polychromatictuner.tunings;

import java.io.Serializable;

/**
 * Created by Nick on 9/24/2016.
 */
public interface Tuning extends Serializable {
    String getDisplayName();
    int getOrdinal();
}

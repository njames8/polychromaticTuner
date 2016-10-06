package com.nickjames.polychromatictuner.audioProcessing;

import android.media.AudioRecord;

import com.nickjames.polychromatictuner.enums.notes.Note;
import com.nickjames.polychromatictuner.enums.tunings.Tuning;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all of the tuners for each note in a tuning.
 * Created by Nick on 10/6/2016.
 */

public class TunerManager {
    private AudioRecord audioRecord;
    private List<Tuner> tuners;

    public TunerManager(Tuning t) {
        tuners = createTuners(t.getNotes());
    }

    private List<Tuner> createTuners(List<Note> notes) {
        List<Tuner> rtn = new ArrayList<>();
        for (Note n : notes) {
            rtn.add(new Tuner(n));
        }
        return rtn;
    }
}

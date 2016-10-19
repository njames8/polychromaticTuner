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
    private Tuning tuning;

    public TunerManager(Tuning t) {
        this.tuning = t;
        this.tuners = createTuners(t.getNotes());
    }

    private List<Tuner> createTuners(List<Note> notes) {
        List<Tuner> rtn = new ArrayList<>();
        for (Note n : notes) {
            rtn.add(new Tuner(n));
        }
        return rtn;
    }

    private void startTuners() {
        for (Tuner t : tuners) {
            t.start();
        }
    }

    private void stopTuners() {
        for (Tuner t : tuners) {
            t.stop();
        }
    }

    private void updateTuners() {
        stopTuners();
        tuners = createTuners(tuning.getNotes());
        startTuners();
    }

    public void start() {
        startTuners();
    }

    public void stop() {
        stopTuners();
    }

    private void cleanUp() {
        stopTuners();
        tuners = null;
        audioRecord = null;
        tuning = null;
    }

    public void setTuning(Tuning tuning) {
        this.tuning = tuning;
        updateTuners();
    }
}

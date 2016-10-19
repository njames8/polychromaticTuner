package com.nickjames.polychromatictuner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nickjames.polychromatictuner.audioProcessing.TunerManager;
import com.nickjames.polychromatictuner.enums.instruments.Instrument;
import com.nickjames.polychromatictuner.enums.tunings.Tuning;


public class TunerActivity extends AppCompatActivity {
    public static final String TUNING = "com.nickjames.polychromatictuner.TunerActivity.TUNING";
    public static final String INSTRUMENT = "com.nickjames.polychromatictuner.TunerActivity.INSTRUMENT";
    public static final int SELECT_TUNING_ACTIVITY = 1;
    public static final int REQUEST_RECORD_AUDIO = 0;

    Button tuningButton;
    Tuning currentTuning;
    Instrument currentInstrument;
    TunerManager tunerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuner);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tuner);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        this.tuningButton = (Button) findViewById(R.id.button);
        if (currentTuning != null) {
            tuningButton.setText(currentTuning.getDisplayName());
        } else {
            tuningButton.setText(R.string.select_tuning);
        }

        requestPermissionToTune();
    }

    public void sendMessage(View view) {
        stopTunerManager();
        Intent intent = new Intent(this, SelectTuningActivity.class);
        if (currentTuning != null) {
            intent.putExtra(TUNING, currentTuning.getOrdinal());
        }
        if (currentInstrument != null) {
            intent.putExtra(INSTRUMENT, currentInstrument.ordinal());
        }
        startActivityForResult(intent, SELECT_TUNING_ACTIVITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_TUNING_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    processIntent(data);
                }
                break;
        }
    }

    private void processIntent(Intent data) {
        StringBuilder sb = new StringBuilder();
        int ci = data.getIntExtra(SelectTuningActivity.INSTRUMENT, -1);
        if (ci != -1) {
            currentInstrument = Instrument.values()[ci];
            sb.append(currentInstrument.getDisplayName());
            sb.append(" - ");
            int ct = data.getIntExtra(SelectTuningActivity.TUNING, -1);
            if (ct != -1) {
                currentTuning = currentInstrument.getTunings()[ct];
                getOrCreateTunerManager().setTuning(currentTuning);
                sb.append(currentTuning.getDisplayName());
            }
            tuningButton.setText(sb.toString());
        }
    }

    private TunerManager getOrCreateTunerManager(){
        if (this.tunerManager != null) {
            return tunerManager;
        }
        return new TunerManager(currentTuning);
    }

    private void startTunerManager() {
        if (currentTuning != null) {
            tunerManager = new TunerManager(currentTuning);
            tunerManager.start();
        }
    }

    private void stopTunerManager() {
        if (tunerManager != null) {
            tunerManager.stop();
        }
    }

    private void requestPermissionToTune() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO);
            return;
        } else {
            startTunerManager();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the thing!

                    startTunerManager();
                } else {
                    Toast.makeText(this, "Denied Permissions", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}


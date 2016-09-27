package com.nickjames.polychromatictuner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nickjames.polychromatictuner.instruments.Instrument;
import com.nickjames.polychromatictuner.tunings.Tuning;

public class TunerActivity extends AppCompatActivity {
    public static final String TUNING = "com.nickjames.polychromatictuner.TunerActivity.TUNING";
    public static final String INSTRUMENT = "com.nickjames.polychromatictuner.TunerActivity.INSTRUMENT";

    Button tuningButton;
    Tuning currentTuning;
    Instrument currentInstrument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuner);
        this.tuningButton = (Button) findViewById(R.id.button);
        if (currentTuning != null) {
            tuningButton.setText(currentTuning.getDisplayName());
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, SelectTuningActivity.class);
        intent.putExtra(TUNING, currentTuning);
        intent.putExtra(INSTRUMENT, currentInstrument);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                StringBuilder sb = new StringBuilder();
                int ci = data.getIntExtra(SelectTuningActivity.INSTRUMENT, -1);
                if (ci != -1) {
                    currentInstrument = Instrument.values()[ci];
                    sb.append(currentInstrument.getDisplayName());
                    sb.append(" - ");
                }
                int ct = data.getIntExtra(SelectTuningActivity.TUNING, -1);
                if (ct != -1) {
                    currentTuning =
                    sb.append(currentTuning.getDisplayName());
                }
                tuningButton.setText(sb.toString());

            }
        }
    }
}


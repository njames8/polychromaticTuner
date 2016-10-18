package com.nickjames.polychromatictuner;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.nickjames.polychromatictuner.enums.instruments.Instrument;
import com.nickjames.polychromatictuner.enums.tunings.Tuning;

import java.util.ArrayList;


public class SelectTuningActivity extends AppCompatActivity {
    public static String TUNING = "com.nickjames.polychromatictuner.SelectTuningActivity.TUNING";
    public static String INSTRUMENT = "com.nickjames.polychromatictuner.SelectTuningActivity.INSTRUMENT";

    Spinner spinner;
    Instrument currentInstrument;
    ArrayList<RadioButton> radioButtons;
    Tuning currentTuning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tuning);

        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        this.spinner = (Spinner) findViewById(R.id.spinner);

        final ArrayAdapter<Instrument> adapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                Instrument.values()
        );
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentInstrument = adapter.getItem(position);
                updateRadioButtons();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            int i = intent.getIntExtra(TunerActivity.INSTRUMENT, -1);
            if (i != -1) {
                currentInstrument = Instrument.values()[i];
                spinner.setSelection(adapter.getPosition(currentInstrument));
                int t = intent.getIntExtra(TunerActivity.TUNING, -1);
                if (t != -1) {
                    updateRadioButtons();
                } else if (radioButtons != null && radioButtons.size() > 0) {
                }
            }
        }
    }

    private void updateRadioButtons() {
        if (currentInstrument != null) {
            ArrayAdapter<Tuning> tuningArrayAdapter = new ArrayAdapter<Tuning>(
                    this,
                    R.layout.list_item_tuning,
                    R.id.list_item_radiobutton,
                    currentInstrument.getTunings()
            );
            ListView lv = (ListView) findViewById(R.id.tuning_list_view);
            if (lv != null) {
                lv.setAdapter(tuningArrayAdapter);
            }
        }
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, TunerActivity.class);
        if (currentInstrument != null) {
            i.putExtra(INSTRUMENT, currentInstrument.ordinal());
        }
        if (currentTuning != null) {
            i.putExtra(TUNING, currentTuning.getOrdinal());
        }
        setResult(RESULT_OK, i);
        finish();
    }

}

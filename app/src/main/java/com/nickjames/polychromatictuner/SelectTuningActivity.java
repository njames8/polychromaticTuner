package com.nickjames.polychromatictuner;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.nickjames.polychromatictuner.instruments.Instrument;
import com.nickjames.polychromatictuner.tunings.Tuning;

import java.io.Serializable;
import java.util.ArrayList;


public class SelectTuningActivity extends AppCompatActivity {
    public static String TUNING = "com.nickjames.polychromatictuner.SelectTuningActivity.TUNING";
    public static String INSTRUMENT = "com.nickjames.polychromatictuner.SelectTuningActivity.INSTRUMENT";

    Spinner spinner;
    Instrument currentInstrument;
    RadioGroup tuningGroup;
    SparseArray<Tuning> tuningRadioButtonMap;
    ArrayList<RadioButton> radioButtons;

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

        tuningGroup = (RadioGroup) findViewById(R.id.tuning_list);
        if (tuningGroup != null) {
            tuningGroup.setOrientation(RadioGroup.VERTICAL);
        }


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

        // we have to make the radiobuttons before setting the selected one

        Intent intent = getIntent();
        if (intent != null) {
            Serializable i = intent.getSerializableExtra(TunerActivity.INSTRUMENT);
            Serializable s = intent.getSerializableExtra(TunerActivity.TUNING);
            if (i != null) {
                currentInstrument = (Instrument) i;
                spinner.setSelection(adapter.getPosition(currentInstrument));
                if (s != null) {
                    updateRadioButtons();
                    int index = tuningRadioButtonMap.indexOfValue((Tuning) s);
                    int key = tuningRadioButtonMap.keyAt(index);
                    setSelectedRadioButton(key);
                } else if (radioButtons != null && radioButtons.size() > 0) {
                    setSelectedRadioButton(radioButtons.get(0).getId());
                }
            }
        }
    }

    private void updateRadioButtons() {
        Tuning [] tunings = currentInstrument.getTunings();
        tuningRadioButtonMap = new SparseArray<>();
        radioButtons = new ArrayList<>();
        for (Tuning t : tunings) {
            RadioButton rb = new RadioButton(this);
            rb.setText(t.getDisplayName());
            rb.setGravity(Gravity.CENTER_HORIZONTAL);
            tuningGroup.addView(rb);
            tuningRadioButtonMap.put(rb.getId(), t);
            radioButtons.add(rb);
        }

    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, TunerActivity.class);
        Tuning t = tuningRadioButtonMap.get(tuningGroup.getCheckedRadioButtonId());
        i.putExtra(TUNING, t.getOrdinal());
        i.putExtra(INSTRUMENT, currentInstrument.ordinal());
        setResult(RESULT_OK, i);
        finish();
    }

    private void setSelectedRadioButton(int id) {
        for (RadioButton rb : radioButtons) {
            if (rb.getId() == id) {
                rb.setSelected(true);
            } else {
                rb.setSelected(false);
            }
        }
    }
}

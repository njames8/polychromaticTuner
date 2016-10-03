package com.nickjames.polychromatictuner;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.nickjames.polychromatictuner.instruments.Instrument;
import com.nickjames.polychromatictuner.tunings.Tuning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SelectTuningActivity extends AppCompatActivity {
    public static String TUNING = "com.nickjames.polychromatictuner.SelectTuningActivity.TUNING";
    public static String INSTRUMENT = "com.nickjames.polychromatictuner.SelectTuningActivity.INSTRUMENT";

    Spinner spinner;
    Instrument currentInstrument;
    RadioGroup tuningGroup;
    // switch to radio -> tuning
    HashMap<Integer, Integer> tuningOrdinalToRadioButtonId;
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
            tuningGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                }
            });
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
            int i = intent.getIntExtra(TunerActivity.INSTRUMENT, -1);
            if (i != -1) {
                currentInstrument = Instrument.values()[i];
                spinner.setSelection(adapter.getPosition(currentInstrument));
                int s = intent.getIntExtra(TunerActivity.TUNING, -1);
                if (s != -1) {
                    updateRadioButtons();
                    System.out.println(s);
                    System.out.println(tuningOrdinalToRadioButtonId.toString());
                    int radioId = tuningOrdinalToRadioButtonId.get(s);
                    setSelectedRadioButton(radioId);
                } else if (radioButtons != null && radioButtons.size() > 0) {
                    setSelectedRadioButton(radioButtons.get(0).getId());
                }
            }
        }
    }

    private void updateRadioButtons() {
        Tuning [] tunings = currentInstrument.getTunings();
        tuningOrdinalToRadioButtonId = new HashMap<>();
        radioButtons = new ArrayList<>();
        for (Tuning t : tunings) {
            RadioButton rb = new RadioButton(this);
            rb.setText(t.getDisplayName());
            rb.setGravity(Gravity.CENTER_HORIZONTAL);
            tuningGroup.addView(rb);
            tuningOrdinalToRadioButtonId.put(t.getOrdinal(), rb.getId());
            radioButtons.add(rb);
        }
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, TunerActivity.class);
        int radioId = tuningGroup.getCheckedRadioButtonId();
        for (Map.Entry<Integer, Integer> e : tuningOrdinalToRadioButtonId.entrySet()) {
            if (e.getValue().equals(radioId)) {
                i.putExtra(TUNING, e.getKey());
            }
        }
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

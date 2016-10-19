package com.nickjames.polychromatictuner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nickjames.polychromatictuner.enums.instruments.Instrument;
import com.nickjames.polychromatictuner.enums.tunings.Tuning;


/**
 * This activity allows the user to select their desired instrument and tuning.
 */
public class SelectTuningActivity extends AppCompatActivity {
    public static String TUNING = "com.nickjames.polychromatictuner.SelectTuningActivity.TUNING";
    public static String INSTRUMENT = "com.nickjames.polychromatictuner.SelectTuningActivity.INSTRUMENT";

    Instrument currentInstrument;
    Tuning currentTuning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tuning);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_select_tuning);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_select_tuning);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get the spinner that will be used to select the desired instrument
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        if (spinner != null) {
            final ArrayAdapter<Instrument> adapter = new ArrayAdapter<>(
                    this,
                    R.layout.support_simple_spinner_dropdown_item,
                    Instrument.values() // all of the possible choices for an instrument
            );
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Instrument i = adapter.getItem(position);
                    if (i != null && !i.equals(currentInstrument)) {
                        currentInstrument = i;
                        currentTuning = null;
                        updateListView();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // we dont need to do anything on nothing selected
                }
            });

            // If the tuner activity has a selected tuning already,
            // grab it and set it as the default
            Intent intent = getIntent();
            if (intent != null) {
                int i = intent.getIntExtra(TunerActivity.INSTRUMENT, -1);
                if (i != -1) {
                    // I used the ordinals here because of the way inheritance works with enums
                    currentInstrument = Instrument.values()[i];
                    spinner.setSelection(adapter.getPosition(currentInstrument));
                    int t = intent.getIntExtra(TunerActivity.TUNING, -1);
                    if (t != -1) {
                        currentTuning = currentInstrument.getTunings()[t];
                        updateListView();

                    }
                }
            }
        }

    }

    /**
     * updates the list view based off of the current instrument
     */
    private void updateListView() {
        if (currentInstrument != null) {
            ListView lv = (ListView) findViewById(R.id.tuning_list_view);
            if (lv != null) {
                final TuningAdapter tuningAdapter = new TuningAdapter(currentInstrument.getTunings());
                lv.setAdapter(tuningAdapter);
                lv.setOnItemClickListener( new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        currentTuning = tuningAdapter.getItem(position);
                    }
                });
                if (currentTuning != null) {
                    lv.setSelection(currentTuning.getOrdinal());
                    lv.setItemChecked(currentTuning.getOrdinal(), true);
                }
            }
        }
    }

    private void createAndSetOKIntent() {
        Intent i = new Intent(this, TunerActivity.class);
        if (currentInstrument != null && currentTuning != null) {
            // used the ordinal because of inheritance with enums
            i.putExtra(INSTRUMENT, currentInstrument.ordinal());
            i.putExtra(TUNING, currentTuning.getOrdinal());
        }
        setResult(RESULT_OK, i);
    }

    @Override
    public void onBackPressed() {
        createAndSetOKIntent();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                createAndSetOKIntent();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This is a special adapter made for displaying Tuning objects in a ListView
     */
    private class TuningAdapter extends BaseAdapter {

        Tuning[] tunings;

        TuningAdapter(Tuning[]tunings) {
            this.tunings = tunings;
        }

        @Override
        public int getCount() {
            return tunings.length;
        }

        @Override
        public Tuning getItem(int position) {
            return tunings[position];
        }

        @Override
        public long getItemId(int position) {
            return tunings[position].hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_tuning, container, false);
            }

            ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getDisplayName());
            return convertView;
        }
    }

}

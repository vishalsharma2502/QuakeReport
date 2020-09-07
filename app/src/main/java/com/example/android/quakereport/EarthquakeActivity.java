
package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("7.2","San Francisco","02/02/2016"));
        earthquakes.add(new Earthquake("6.1","Landom","02/02/2016"));
        earthquakes.add(new Earthquake("3.9","Tokyo","02/02/2016"));
        earthquakes.add(new Earthquake("5.4","Mexico City","02/02/2016"));
        earthquakes.add(new Earthquake("2.8","Moscow","02/02/2016"));
        earthquakes.add(new Earthquake("4.9","Rio de Janeiro","02/02/2016"));
        earthquakes.add(new Earthquake("1.6","Paris","02/02/2016"));

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this,  earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}

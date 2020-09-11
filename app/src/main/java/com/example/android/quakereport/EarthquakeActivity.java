
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    /**
     * Adapter for the list of earthquakes
     */
    private EarthquakeAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView emptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        emptyStateTextView = (TextView) findViewById(R.id.empty_view);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setEmptyView(emptyStateTextView);

        // Create a new {@link ArrayAdapter} of earthquakesArrayList
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long Id) {

                Earthquake earthquake = (Earthquake) mAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(earthquake.getUrl()));
                startActivity(intent);
            }
        });

        //Use incase of AsyncTask subclass not AsyncTaskLoader class
//        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
//        task.execute(USGS_REQUEST_URL);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        Log.v(LOG_TAG,"in MainActivity class");


    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG,"oncreate");
        // Create a new loader for the given URL
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {

        // Hide loading indicator because the data has been loaded
        View loadIndicator = findViewById(R.id.loading_spinner);
        loadIndicator.setVisibility(View.GONE);

        emptyStateTextView.setText(R.string.no_earthquakes);
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        Log.v(LOG_TAG,"onLoadFinished");
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null)
            mAdapter.addAll(earthquakes);

    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.v(LOG_TAG,"onReset");
        //Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }


//    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>>{
//
//        @Override
//        protected List<Earthquake> doInBackground(String... urls) {
//            if(urls.length <1 || urls[0] == null)
//                return null;
//
//            List<Earthquake> result = QueryUtils.fetchEarthquakeData(urls[0]);
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(List<Earthquake> data) {
//            mAdapter.clear();
//
//            if(data != null && !data.isEmpty())
//                mAdapter.addAll(data);
//        }
//    }
}

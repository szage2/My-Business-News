package com.example.gerda.mybusinessnews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private String mQueryUrl = "http://content.guardianapis.com/search?q=business&from-date=2016-12-12&api-key=test";

    private NewsAdapter mAdapter;

    private static final int NEWS_LOADER_ID = 1;

    // TextView that is displayed when the the list is empty
    private TextView myEmptyStateTextView;

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(this, mQueryUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newses) {
        // Clear the adapter of previous news data
        mAdapter.clear();

        // Hide the loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No newses found"
        myEmptyStateTextView.setText(R.string.no_news);

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newses != null && !newses.isEmpty()) {
            mAdapter.addAll(newses);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        // Find reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.news_list);

        // Find reference to the TextView in the layout
        myEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(myEmptyStateTextView);

        // Create a new adapter that takes an empty list of news as input
        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // So the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                // Get details on the currently active default data network
                getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get the reference to the LoadManager, in order to interact with orders
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader, Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the Loader  Callbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface.
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            // Otherwise, display error.
            //First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            myEmptyStateTextView.setText(R.string.no_internet);
        }
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find the current news that was clicked
                News currentNews = mAdapter.getItem(position);
                // Convert the String URL into a Uri object (to pass into the intent constructor)
                Uri newsUri = Uri.parse(currentNews.getWebUrl());
                // Create a new intent to launch a new activity
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }
}

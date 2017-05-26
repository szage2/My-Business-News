package com.example.gerda.mybusinessnews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Gerda on 12/12/2016.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /** Create a new {@link NewsLoader
     *
     * @param context of the activity
     * @param url to load data form
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() { forceLoad(); }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform on the network request, parse the response, and extract a list of books.
        List<News> newses = QueryUtils.fetchNewsData(mUrl);
        return newses;
    }
}

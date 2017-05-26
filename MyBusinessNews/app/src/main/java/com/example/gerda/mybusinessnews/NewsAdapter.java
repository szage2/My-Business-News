package com.example.gerda.mybusinessnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TimeFormatException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @link NewsAdapter} is an {@link ArrayAdapter} which is capable to provide the layout for
 * each list item according to the data source, what is the source of {@link News} objects.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    /** Create a new {@link NewsAdapter} object
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param newses is the list of {@link News}es to be displayed.
     */
    public NewsAdapter(Context context, ArrayList<News> newses) { super(context, 0, newses); }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }
        // Get the {@link News} object located at this position in the list
        News currentNews = getItem(position);

        // Find the Text View with the id title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        // Get the value into the string variable
        String title = new String(currentNews.getTitle());
        // Display the title of the current book in that TextView
        titleTextView.setText(title);

        // Find the Text View with the id published date
        TextView sectionNameTextView = (TextView) listItemView.findViewById(R.id.section_name);
        // Get the value into the string variable
        String section = new String (currentNews.getSectionName());
        // Display the date of the current book when is's been published in that TextView
        sectionNameTextView.setText(section);

        // Find the Text View with the id published date
        TextView publicationDateTextView = (TextView) listItemView.findViewById(R.id.publication_date);
        // Get the value into the string variable
        String publishedOn = new String (currentNews.getPublicationDate());

        // Separate the date from the time
        String separateDate = publishedOn.split("T")[0];
        // Separate the time from the date
        String separateTime = publishedOn.split("T")[1];
        // Replace a unused character
        String replacedTime = separateTime.replace("Z", "");

        // Display the date of the current book when is's been published in that TextView
        publicationDateTextView.setText(separateDate);
        // Find the Text View with the id published time
        TextView publicationTimeTextView = (TextView) listItemView.findViewById(R.id.publication_time);

        // Display the time of the current book when is's been published in that TextView
        publicationTimeTextView.setText(replacedTime);

        // Return the list of news(es)
        return listItemView;
    }
}

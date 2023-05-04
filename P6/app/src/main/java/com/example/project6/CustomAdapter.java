package com.example.project6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Nasa> {

    public CustomAdapter(Context context, List<Nasa> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Nasa item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Lookup view for data population
        TextView titleTextView = convertView.findViewById(R.id.title);
        TextView subtitleTextView = convertView.findViewById(R.id.subtitle);

        // Populate the data into the template view using the data object
        titleTextView.setText(item.getTitle());
        subtitleTextView.setText(item.getUrl());

        // Return the completed view to render on screen
        return convertView;
    }
}
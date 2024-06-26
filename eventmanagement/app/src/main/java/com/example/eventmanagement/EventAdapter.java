package com.example.eventmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    private Context context;
    private List<Event> events;

    public EventAdapter(Context context, List<Event> events) {
        super(context, 0, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false);
        }

        Event event = events.get(position);

        TextView eventNameTextView = view.findViewById(R.id.eventName);
        TextView eventDescriptionTextView = view.findViewById(R.id.eventDescription);
        TextView eventDateTextView = view.findViewById(R.id.eventDate);
        TextView eventTimeTextView = view.findViewById(R.id.eventTime);
        TextView eventLocationTextView = view.findViewById(R.id.eventLocation);

        eventNameTextView.setText(event.getName());
        eventDescriptionTextView.setText(event.getDescription());
        eventDateTextView.setText(event.getDate());
        eventTimeTextView.setText(event.getTime());
        eventLocationTextView.setText(event.getLocation());

        return view;
    }
}
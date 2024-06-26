package com.example.eventmanagement;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EventListActivity extends AppCompatActivity {

    private ListView eventListView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        eventListView = findViewById(R.id.eventListView);
        databaseHelper = new DatabaseHelper(this);

        List<Event> events = databaseHelper.getAllEvents();
        EventAdapter eventAdapter = new EventAdapter(this, events);
        eventListView.setAdapter(eventAdapter);
    }
}

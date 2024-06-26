package com.example.eventmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private EditText eventNameEditText;
    private EditText eventDescriptionEditText;
    private EditText eventDateEditText;
    private EditText eventTimeEditText;
    private EditText eventLocationEditText;
    private Button updateButton;

    private DatabaseHelper databaseHelper;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        eventNameEditText = findViewById(R.id.eventName);
        eventDescriptionEditText = findViewById(R.id.eventDescription);
        eventDateEditText = findViewById(R.id.eventDate);
        eventTimeEditText = findViewById(R.id.eventTime);
        eventLocationEditText = findViewById(R.id.eventLocation);
        updateButton = findViewById(R.id.updateButton);

        databaseHelper = new DatabaseHelper(this);

        eventId = getIntent().getIntExtra("eventId", 0);
        Event event = databaseHelper.getEvent(eventId);
        if (event!= null) {
            eventNameEditText.setText(event.getName());
            eventDescriptionEditText.setText(event.getDescription());
            eventDateEditText.setText(event.getDate());
            eventTimeEditText.setText(event.getTime());
            eventLocationEditText.setText(event.getLocation());
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = eventNameEditText.getText().toString();
                String eventDescription = eventDescriptionEditText.getText().toString();
                String eventDate = eventDateEditText.getText().toString();
                String eventTime = eventTimeEditText.getText().toString();
                String eventLocation = eventLocationEditText.getText().toString();

                if (!eventName.isEmpty() &&!eventDescription.isEmpty() &&!eventDate.isEmpty() &&!eventTime.isEmpty() &&!eventLocation.isEmpty()) {
                    Event event = new Event(eventId, eventName, eventDescription, eventDate, eventTime, eventLocation);
                    databaseHelper.updateEvent(event);
                    Toast.makeText(UpdateActivity.this, "Event updated successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
                notify();
                finish();
            }
        });
    }
}

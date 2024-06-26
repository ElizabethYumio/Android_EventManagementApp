package com.example.eventmanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText eventNameEditText;
    private EditText eventDescriptionEditText;
    private EditText eventDateEditText;
    private EditText eventTimeEditText;
    private EditText eventLocationEditText;
    private Button registerButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eventNameEditText = findViewById(R.id.eventName);
        eventDescriptionEditText = findViewById(R.id.eventDescription);
        eventDateEditText = findViewById(R.id.eventDate);
        eventTimeEditText = findViewById(R.id.eventTime);
        eventLocationEditText = findViewById(R.id.eventLocation);
        registerButton = findViewById(R.id.registerButton);

        databaseHelper = new DatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = eventNameEditText.getText().toString();
                String eventDescription = eventDescriptionEditText.getText().toString();
                String eventDate = eventDateEditText.getText().toString();
                String eventTime = eventTimeEditText.getText().toString();
                String eventLocation = eventLocationEditText.getText().toString();

                if (!eventName.isEmpty() &&!eventDescription.isEmpty() &&!eventDate.isEmpty() &&!eventTime.isEmpty() &&!eventLocation.isEmpty()) {
                    Event event = new Event(0, eventName, eventDescription, eventDate, eventTime, eventLocation);
                    databaseHelper.insertEvent(event);
                    Toast.makeText(RegisterActivity.this, "Event registered successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
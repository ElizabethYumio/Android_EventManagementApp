package com.example.eventmanagement;

public class Event {
    private int id;
    private String name;
    private String description;
    private String date;
    private String time;
    private String location;

    public Event(int id, String name, String description, String date, String time, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
    }

}
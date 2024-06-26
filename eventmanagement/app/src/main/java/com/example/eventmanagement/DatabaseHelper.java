package com.example.eventmanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_LOCATION = "location";

    private static final String CREATE_TABLE_EVENTS = "CREATE TABLE " + TABLE_EVENTS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_TIME + " TEXT, " +
            COLUMN_LOCATION + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    public void insertEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_EVENTS + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_DATE + ", " + COLUMN_TIME + ", " + COLUMN_LOCATION + ") VALUES (?, ?, ?, ?, ?)",
                new Object[]{event.getName(), event.getDescription(), event.getDate(), event.getTime(), event.getLocation()});
        db.close();
    }

    public void updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_EVENTS + " SET " + COLUMN_NAME + " = ?, " + COLUMN_DESCRIPTION + " = ?, " + COLUMN_DATE + " = ?, " + COLUMN_TIME + " = ?, " + COLUMN_LOCATION + " = ? WHERE " + COLUMN_ID + " = ?",
                new Object[]{event.getName(), event.getDescription(), event.getDate(), event.getTime(), event.getLocation(), event.getId()});
        db.close();
    }

    public void deleteEvent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_ID + " = ?", new Object[]{id});
        db.close();
    }

    public Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        Event event = null;
        if (cursor.moveToFirst()) {
            event = new Event(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
        }
        cursor.close();
        db.close();
        return event;
    }

    public List<Event> getAllEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENTS, null);
        List<Event> events = new ArrayList<>();
        while (cursor.moveToNext()) {
            events.add(new Event(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            ));
        }
        cursor.close();
        db.close();
        return events;
    }
}

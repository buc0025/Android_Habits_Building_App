package com.example.habitscalendar.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.habitscalendar.models.Habit;

import java.util.ArrayList;

public class HabitManager extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Habit.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "habit_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_HABIT = "habit";
    private static final String COLUMN_REASON = "reason";
    private static final String COLUMN_START = "start";

    public HabitManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void addHabit(Habit habit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_HABIT, habit.getHabit());
        contentValues.put(COLUMN_REASON, habit.getReason());
        contentValues.put(COLUMN_START, String.valueOf(habit.getStartDate()));

        long result = database.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Habit> getAllHabits() {
        List<Habit> listOfAllHabits = new ArrayList<>();

        return listOfAllHabits;
    }

    public boolean isHabitCompleted(Habit habit) {
        Date today;
        return isHabitCompleted(habit, today);
    }

    public boolean isHabitCompleted(Habit habit, Date date) {

    }

    public List<Date> getHabitCompletedDates(Habit habit) {
        // Method pulls data to populate monthly view
        List<Date> listOfCompletedDates = new ArrayList<>();

        return listOfCompletedDates;
    }

    public int getCurrentStreak(Habit habit) {
        int currentStreak;
        return currentStreak;
    }

    public int getMaxStreak(Habit habit) {
        int maxStreak;
        return maxStreak;
    }

    public void deleteHabit(Habit habit) {
        // Method deletes habit from database

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_HABIT + " VARCHAR, " +
                        COLUMN_REASON + " VARCHAR, " +
                        COLUMN_START + " DATE);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
/*
 ***************Table Statements for SQL******************
 *
 * CREATE TABLE habit_table (
 *   id VARCHAR PRIMARY KEY,
 *   name VARCHAR,
 *   reason VARCHAR, // Might reach char limit
 *   start DATE);
 *
 * CREATE TABLE habit_dates (
 *   id VARCHAR,
 *   date DATE);
 *
 */

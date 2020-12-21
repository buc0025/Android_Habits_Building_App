package com.example.habitscalendar.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.habitscalendar.models.Habit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HabitManager extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "HabitCalendar.db";
    private static final int DATABASE_VERSION = 1;

    private static final String HABIT_TABLE_NAME = "habit_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_HABIT = "habit";
    private static final String COLUMN_REASON = "reason";
    private static final String COLUMN_START = "start";
    private static final String DATE_TABLE_NAME = "date_table";
    private static final String COLUMN_DATE = "date";

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

        long result = database.insert(HABIT_TABLE_NAME, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 12/21/2020
     * add date to corresponding habit
     * @return
     */
    public void addDate(String habit, String date) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_HABIT, habit);
        contentValues.put(COLUMN_DATE, date);

        long result = database.insert(DATE_TABLE_NAME, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + HABIT_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public List<Habit> getAllHabits() {
        Cursor cursor = readAllData();

        List<Habit> listOfAllHabits = new ArrayList<>(cursor.getCount());

        while (cursor.moveToNext()) {
            Habit habit = new Habit(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));

            listOfAllHabits.add(habit);
        }
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
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(HABIT_TABLE_NAME, "_id=?", new String[] {habit.getHabitId()});
        if (result == -1) {
            Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =
                "CREATE TABLE " + HABIT_TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_HABIT + " VARCHAR, " +
                        COLUMN_REASON + " VARCHAR, " +
                        COLUMN_START + " VARCHAR);";
        sqLiteDatabase.execSQL(query);

        String queryDates =
                "CREATE TABLE " + DATE_TABLE_NAME + " (" +
                        COLUMN_HABIT + " VARCHAR, " +
                        COLUMN_DATE + " VARCHAR);";
        sqLiteDatabase.execSQL(queryDates);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HABIT_TABLE_NAME);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATE_TABLE_NAME);
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

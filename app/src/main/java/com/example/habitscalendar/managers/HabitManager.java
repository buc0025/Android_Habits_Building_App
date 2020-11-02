package com.example.habitscalendar.managers;

import java.util.HashMap;

public class HabitManager {

    public void updateDailyHabit(Date day) {
        // Method updates database with daily activity from habit

    }

    public boolean isHabitCompleted(Date day) {
        // Method checks the weekly and monthly view to see if habit is completed
        return true;
    }

    public Map<Date, Boolean> showProgress() {
        // Method pulls data to populate monthly view
        Map<Date, Boolean> map = new HashMap<>();

        return map;
    }

    public void deleteHabit(Habit habit) {
        // Method deletes habit from database

    }
}
/*
 ***************Table Statements for SQL******************
 *
 * CREATE TABLE habit_streak (
 *   id INTEGER PRIMARY KEY AUTOINCREMENT,
 *   habit TEXT,
 *   streak INTEGER);
 *
 * CREATE TABLE habit_dates (
 *   id INTEGER PRIMARY KEY AUTOINCREMENT,
 *   habit TEXT,
 *   date DATE);
 *
 */

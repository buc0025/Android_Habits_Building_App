package com.example.habitscalendar.managers;

import java.util.ArrayList;

public class HabitManager {

    public void addHabit(Habit habit) {

    }

    public void updateDailyHabit(Habit habit) {
        // Method updates database with daily activity from habit

    }

    public boolean isHabitCompleted(Habit habit) {
        // Method checks the weekly and monthly view to see if habit is completed
        return true;
    }

    public List<Date> getHabit(Habit habit) {
        // Method pulls data to populate monthly view
        List<Date> list = new ArrayList<>();

        return list;
    }

    public int getStreak(Habit habit) {
        int streak;
        return streak;
    }

    public void deleteHabit(Habit habit) {
        // Method deletes habit from database

    }
}
/*
 ***************Table Statements for SQL******************
 *
 * CREATE TABLE habit_table (
 *   id VARCHAR,
 *   name VARCHAR,
 *   reason VARCHAR,
 *   start DATE,
 *   streak INTEGER);
 *
 * CREATE TABLE habit_dates (
 *   id VARCHAR,
 *   date DATE);
 *
 */

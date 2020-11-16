package com.example.habitscalendar.models;

import java.time.LocalDate;
import java.util.Date;

public class Habit {

    private String habitId;
    private String habit;
    private String reason;
    private Date startDate;

    public Habit(String habitId, String habit, String reason, Date startDate) {
        this.habitId = habitId;
        this.habit = habit;
        this.reason = reason;
        this.startDate = startDate;
    }

    public String getHabitId() {
        return habitId;
    }

    public void setHabitId(String habitId) {
        this.habitId = habitId;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}

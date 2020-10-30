package com.example.habitscalendar.models;

import java.time.LocalDate;

public class Habits {

    private String habitId;
    private String habit;
    private String reason;
    private LocalDate startDate;

    public Habits(String habitId, String habit, String reason, LocalDate startDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}

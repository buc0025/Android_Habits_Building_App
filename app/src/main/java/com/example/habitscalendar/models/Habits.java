package com.example.habitscalendar.models;

public class Habits {

    private String habit;
    private String reason;
    private int consecutiveDays;

    public Habits(String habit, String reason, int consecutiveDays) {
        this.habit = habit;
        this.reason = reason;
        this.consecutiveDays = consecutiveDays;
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

    public int getConsecutiveDays() {
        return consecutiveDays;
    }

    public void setConsecutiveDays(int consecutiveDays) {
        this.consecutiveDays = consecutiveDays;
    }
}

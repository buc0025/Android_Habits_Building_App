package com.example.habitscalendar.models;

public class WeekDay {
    Integer dayPics;
    String dayNames;

    public WeekDay(Integer dayPics, String dayNames) {
        this.dayPics = dayPics;
        this.dayNames = dayNames;
    }

    public Integer getDayPics() {
        return dayPics;
    }

    public String getDayNames() {
        return dayNames;
    }
}

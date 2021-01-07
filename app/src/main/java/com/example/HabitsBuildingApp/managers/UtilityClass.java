package com.example.HabitsBuildingApp.managers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UtilityClass {

    private static final int DAY_IN_EPOCH_TIME = 86400000;

    public static String listViewDates(int day) {
        // Get calendar set to current date and time
        Calendar calendar = Calendar.getInstance();

        // Set calendar to Sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd");

        // Set dates of the current week starting on Sunday
        for (int i = 0; i < 7; i++) {
            if (i == 0 && day == 0) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 1 && day == 1) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 2 && day == 2) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 3 && day == 3) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 4 && day == 4) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 5 && day == 5) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 6 && day == 6) {
                return dateFormat.format(calendar.getTime());
            }
            calendar.add(Calendar.DATE, 1);
        }

        return dateFormat.format(calendar.getTime());
    }

    public static String weekDayAdapterDates(int day) {
        // Get calendar set to current date and time
        Calendar calendar = Calendar.getInstance();

        // Set calendar to Sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Set dates of the current week starting on Sunday
        for (int i = 0; i < 7; i++) {
            if (i == 0 && day == 0) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 1 && day == 1) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 2 && day == 2) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 3 && day == 3) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 4 && day == 4) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 5 && day == 5) {
                return dateFormat.format(calendar.getTime());
            }
            if (i == 6 && day == 6) {
                return dateFormat.format(calendar.getTime());
            }
            calendar.add(Calendar.DATE, 1);
        }

        return dateFormat.format(calendar.getTime());
    }

    public static long convertToEpoch(Date dateClicked) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        final String selectedDate = simpleDateFormat.format(dateClicked);
        long epoch = 0;

        try {
            Date date = simpleDateFormat.parse(selectedDate);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epoch;
    }

    public static Date stringToDate(String date) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();

        try {
            today = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return today;
    }

    public static String getStreak(List<Long> dates) {
        int streak = 0;
        Date today = Calendar.getInstance().getTime();
        long todayDate = UtilityClass.convertToEpoch(today);
        Collections.sort(dates);
        Collections.reverse(dates);

        if (dates.size() == 0) {
            return String.valueOf(streak);
        }

        if (dates.get(0) == todayDate || dates.get(0) == todayDate - DAY_IN_EPOCH_TIME) {
            streak = 1;
        } else {
            return String.valueOf(streak);
        }

        for (int i = 1; i < dates.size(); i++) {
            if (dates.get(i) + DAY_IN_EPOCH_TIME == dates.get(i - 1)) {
                streak++;
            } else {
                break;
            }
        }
        return String.valueOf(streak);
    }

    public static String streakDayOrDays(int numberOfDays) {
        if (numberOfDays == 1) {
            return " day";
        } else {
            return " days";
        }
    }
}

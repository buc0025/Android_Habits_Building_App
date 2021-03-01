package com.example.HabitsBuildingApp.managers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UtilityClass {

    private static final int DAY_IN_EPOCH_TIME = 86400000;
    public static HashMap<Integer, String> dayAbbrev;
    public static HashMap<String, String> monthAbbrev;

    public static String weekViewDate(int day) {
        // Get calendar set to current date and time
        Calendar calendar = Calendar.getInstance();

        // Set calendar to Sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DateFormat dateFormat = new SimpleDateFormat("dd");

        // Set dates of the current week starting on Sunday
        for (int i = 0; i < 7; i++) {
            if (i == 6 - day && day == 6 - i) {
                return dateFormat.format(calendar.getTime());
            }
            calendar.add(Calendar.DATE, 1);
        }

        Calendar lastWeek = Calendar.getInstance();
        lastWeek.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        lastWeek.add(Calendar.DATE, -1);

        for (int i = 7; i < 14; i++) {
            if (i == day && day == i) {
                return dateFormat.format(lastWeek.getTime());
            }
            lastWeek.add(Calendar.DATE, -1);
        }

        return dateFormat.format(calendar.getTime());
    }

    public static String monthAbbreviation(String month) {
        return monthAbbrev.get(month.substring(0,2));
    }

    public static String weekViewAdapterDate(int day) {
        // Get calendar set to current date and time
        Calendar calendar = Calendar.getInstance();

        // Set calendar to Sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Set dates of the current week starting on Sunday
        for (int i = 0; i < 7; i++) {
            if (i == 6 - day && day == 6 - i) {
                return dateFormat.format(calendar.getTime());
            }
            calendar.add(Calendar.DATE, 1);
        }

        Calendar lastWeek = Calendar.getInstance();
        lastWeek.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        lastWeek.add(Calendar.DATE, -1);

        for (int i = 7; i < 14; i++) {
            if (i == day && day == i) {
                return dateFormat.format(lastWeek.getTime());
            }
            lastWeek.add(Calendar.DATE, -1);
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

    public static long weekDayPositionToLong(int position) {
        return convertToEpoch(stringToDate(weekAdapterDates(position)));
    }

    public static String weekAdapterDates(int day) {
        // Get calendar set to current date and time
        Calendar calendar = Calendar.getInstance();

        // Set calendar to Sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Set dates of the current week starting on Sunday
        for (int i = 0; i < 7; i++) {
            if (i == 6 - day && day == 6 - i) {
                return dateFormat.format(calendar.getTime());
            }
            calendar.add(Calendar.DATE, 1);
        }

        Calendar lastWeek = Calendar.getInstance();
        lastWeek.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        lastWeek.add(Calendar.DATE, -1);

        for (int i = 7; i < 14; i++) {
            if (i == day && day == i) {
                return dateFormat.format(lastWeek.getTime());
            }
            lastWeek.add(Calendar.DATE, -1);
        }

        return dateFormat.format(calendar.getTime());
    }

    static {
        dayAbbrev = new HashMap<>();
        dayAbbrev.put(13, "Sun");
        dayAbbrev.put(12, "Mon");
        dayAbbrev.put(11, "Tue");
        dayAbbrev.put(10, "Wed");
        dayAbbrev.put(9, "Thur");
        dayAbbrev.put(8, "Fri");
        dayAbbrev.put(7, "Sat");
        dayAbbrev.put(6, "Sun");
        dayAbbrev.put(5, "Mon");
        dayAbbrev.put(4, "Tue");
        dayAbbrev.put(3, "Wed");
        dayAbbrev.put(2, "Thur");
        dayAbbrev.put(1, "Fri");
        dayAbbrev.put(0, "Sat");

        monthAbbrev = new HashMap<>();
        monthAbbrev.put("01", "Jan");
        monthAbbrev.put("02", "Feb");
        monthAbbrev.put("03", "Mar");
        monthAbbrev.put("04", "Apr");
        monthAbbrev.put("05", "May");
        monthAbbrev.put("06", "Jun");
        monthAbbrev.put("07", "Jul");
        monthAbbrev.put("08", "Aug");
        monthAbbrev.put("09", "Sept");
        monthAbbrev.put("10", "Oct");
        monthAbbrev.put("11", "Nov");
        monthAbbrev.put("12", "Dec");
    }
}

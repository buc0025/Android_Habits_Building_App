package com.example.HabitsBuildingApp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.HabitsBuildingApp.R;
import com.example.HabitsBuildingApp.managers.HabitManager;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.github.sundeepk.compactcalendarview.CompactCalendarView.FILL_LARGE_INDICATOR;
import static com.github.sundeepk.compactcalendarview.CompactCalendarView.NO_FILL_LARGE_INDICATOR;

public class CalendarActivity extends AppCompatActivity {

    private TextView calendarReason;
    private TextView calendarStartDate;
    CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
    TextView calendarMonth;
    private HabitManager habitManager;
    private List<Long> epochTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarReason = (TextView) findViewById(R.id.calendarReason);
        calendarStartDate = (TextView) findViewById(R.id.calendarStartDate);

        Intent intent = getIntent();
        final String habitName = intent.getExtras().getString("HabitName");
        final String habitReason = intent.getExtras().getString("HabitReason");
        final String habitStartDate = intent.getExtras().getString("HabitStartDate");
        final String habitId = intent.getExtras().getString("HabitId");

        // Title for action bar and back button
        getSupportActionBar().setTitle(habitName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        habitManager = new HabitManager(CalendarActivity.this);
        epochTimes = habitManager.getHabitCompletedDates(habitId);

        calendarReason.setText(habitReason);
        calendarStartDate.setText(habitStartDate);

        calendarMonth = (TextView) findViewById(R.id.calendarMonth);
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendarView);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        compactCalendarView.setEventIndicatorStyle(FILL_LARGE_INDICATOR);

        for (int i = 0; i < epochTimes.size(); i++) {
            Event completedDate = new Event(Color.GREEN, epochTimes.get(i), "Some extra data that I want to store.");
            compactCalendarView.addEvent(completedDate);
        }

        // Add event1 on December 3, 2020
        Event event = new Event(Color.GREEN, 1607026803000L, "Some extra data that I want to store.");
        compactCalendarView.addEvent(event);

        // Setting calendar month as current month until onMonthScroll() is called
        calendarMonth.setText(dateFormatMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        // Current date has circle around date
        compactCalendarView.setCurrentDayIndicatorStyle(NO_FILL_LARGE_INDICATOR);

        // Current date will have no color until another date is selected
        compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.TRANSPARENT);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                // Formatting dateClicked and converting dateClicked to epoch
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                final String selectedDate = simpleDateFormat.format(dateClicked);
                long epoch = 0;
                try {
                    Date date = simpleDateFormat.parse(selectedDate);
                    epoch = date.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // If date already exists in List<Long> epochTimes and clicked, alert dialog gives option to delete date
                if (epochTimes.contains(epoch)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this);
                    builder.setMessage("Are you sure you want to delete date?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    habitManager.deleteDate(habitId, selectedDate);

                                    long epoch = 0;
                                    try {
                                        Date date = simpleDateFormat.parse(selectedDate);
                                        epoch = date.getTime();
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    int index = epochTimes.indexOf(epoch);
                                    epochTimes.remove(index);
                                    compactCalendarView.removeEvents(epoch);
                                }
                            })
                            .setNegativeButton("No", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    // If date doesn't exist in List<Long> epochTimes and clicked, alert dialog gives option to add date
                    AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this);
                    builder.setMessage("Are you sure you want to add date?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    habitManager.addDate(habitId, selectedDate);
                                    long epoch = 0;
                                    try {
                                        Date date = simpleDateFormat.parse(selectedDate);
                                        epoch = date.getTime();
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Event newDate = new Event(Color.GREEN, epoch, "Some extra data that I want to store.");
                                    compactCalendarView.addEvent(newDate);
                                    epochTimes.add(epoch);
//                                compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.GREEN);
                                }
                            })
                            .setNegativeButton("No", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                calendarMonth.setText(dateFormatMonth.format(firstDayOfNewMonth));

                // First day of each month is no longer highlighted
                compactCalendarView.shouldSelectFirstDayOfMonthOnScroll(false);
            }
        });
    }
}
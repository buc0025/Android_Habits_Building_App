package com.example.HabitsBuildingApp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.HabitsBuildingApp.R;
import com.example.HabitsBuildingApp.managers.HabitManager;
import com.example.HabitsBuildingApp.managers.UtilityClass;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.github.sundeepk.compactcalendarview.CompactCalendarView.FILL_LARGE_INDICATOR;
import static com.github.sundeepk.compactcalendarview.CompactCalendarView.NO_FILL_LARGE_INDICATOR;

public class CalendarActivity extends AppCompatActivity {

    private CompactCalendarView compactCalendarView;
    private TextView calendarReason;
    private TextView calendarStartDate;
    private TextView calendarStreak;
    private TextView calendarStreakDays;
    private TextView calendarMonth;
    private HabitManager habitManager;
    private List<Long> epochTimes;
    private SimpleDateFormat dateFormatMonth;
    private String shareHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();
        final String habitName = intent.getExtras().getString("HabitName");
        final String habitReason = intent.getExtras().getString("HabitReason");
        final String habitStartDate = intent.getExtras().getString("HabitStartDate");
        final String habitId = intent.getExtras().getString("HabitId");
        shareHabit = intent.getExtras().getString("HabitName");

        calendarReason = (TextView) findViewById(R.id.calendarReason);
        calendarStartDate = (TextView) findViewById(R.id.calendarStartDate);
        calendarStreak = (TextView) findViewById(R.id.calendarStreak);
        calendarStreakDays = (TextView) findViewById(R.id.calendarStreakDays);
        calendarMonth = (TextView) findViewById(R.id.calendarMonth);
        dateFormatMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        habitManager = new HabitManager(CalendarActivity.this);
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendarView);
        epochTimes = habitManager.getHabitCompletedDates(habitId);

        // Title for action bar and back button
        getSupportActionBar().setTitle(habitName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarReason.setText(habitReason);
        calendarStartDate.setText(habitStartDate);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        compactCalendarView.setEventIndicatorStyle(FILL_LARGE_INDICATOR);
        calendarMonth.setText(dateFormatMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendarView.setCurrentDayIndicatorStyle(0);

        Date today = Calendar.getInstance().getTime();

        if (epochTimes.contains(UtilityClass.convertToEpoch(today))) {
            compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.GREEN);
        } else {
            compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.WHITE);
        }

        for (int i = 0; i < epochTimes.size(); i++) {
            Event completedDate = new Event(Color.GREEN, epochTimes.get(i), habitName);
            compactCalendarView.addEvent(completedDate);
        }

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(final Date dateClicked) {
                // Formatting dateClicked and converting dateClicked to epoch
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                final String selectedDate = simpleDateFormat.format(dateClicked);

                AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this);

                Date today = Calendar.getInstance().getTime();

                // If future date is clicked on, alert dialog pops up and user can't add date
                if (UtilityClass.convertToEpoch(dateClicked) > UtilityClass.convertToEpoch(today)) {
                    builder.setMessage("Future dates can't be added")
                            .setPositiveButton("Ok", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.TRANSPARENT);
                    // If date already exists in List<Long> epochTimes and clicked, alert dialog gives option to delete date
                } else if (epochTimes.contains(UtilityClass.convertToEpoch(dateClicked))) {
                    compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.GREEN);
                    builder.setMessage("Are you sure you want to delete date?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    habitManager.deleteDate(habitId, selectedDate);

                                    int index = epochTimes.indexOf(UtilityClass.convertToEpoch(dateClicked));
                                    epochTimes.remove(index);
                                    compactCalendarView.removeEvents(UtilityClass.convertToEpoch(dateClicked));
                                    compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.TRANSPARENT);
                                    calendarStreak.setText(UtilityClass.getStreak(epochTimes));
                                    calendarStreakDays.setText(UtilityClass.streakDayOrDays(Integer.valueOf(UtilityClass.getStreak(epochTimes))));
                                }
                            })
                            .setNegativeButton("No", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.TRANSPARENT);
                    // If date doesn't exist in List<Long> epochTimes and clicked, alert dialog gives option to add date
                    builder.setMessage("Are you sure you want to add date?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    habitManager.addDate(habitId, selectedDate);

                                    Event newDate = new Event(Color.GREEN, UtilityClass.convertToEpoch(dateClicked), habitName);
                                    compactCalendarView.addEvent(newDate);
                                    epochTimes.add(UtilityClass.convertToEpoch(dateClicked));
                                    compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.GREEN);
                                    calendarStreak.setText(UtilityClass.getStreak(epochTimes));
                                    calendarStreakDays.setText(UtilityClass.streakDayOrDays(Integer.valueOf(UtilityClass.getStreak(epochTimes))));
                                }
                            })
                            .setNegativeButton("No", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                if (epochTimes.contains(UtilityClass.convertToEpoch(today))) {
                    compactCalendarView.setCurrentDayBackgroundColor(Color.GREEN);
                }
                else {
                    compactCalendarView.setCurrentDayBackgroundColor(Color.TRANSPARENT);
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                calendarMonth.setText(dateFormatMonth.format(firstDayOfNewMonth));

                // First day of each month is no longer highlighted
                compactCalendarView.shouldSelectFirstDayOfMonthOnScroll(false);
            }
        });
        calendarStreak.setText(UtilityClass.getStreak(epochTimes));
        calendarStreakDays.setText(UtilityClass.streakDayOrDays(Integer.valueOf(UtilityClass.getStreak(epochTimes))));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_habit_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.deleteHabit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this);
            builder.setMessage("Are you sure you want to delete habit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = getIntent();
                            final String habitId = intent.getExtras().getString("HabitId");
                            habitManager.deleteHabit(habitId);
                            Intent goToMain = new Intent(CalendarActivity.this, MainActivity.class);
                            startActivity(goToMain);
                        }
                    })
                    .setNegativeButton("No", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        if (id == R.id.shareHabit) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "The habit I'm going to build is " + shareHabit;
            String shareSubject = "I want to build a new habit!";

            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

            startActivity(Intent.createChooser(sharingIntent, "Share Using"));
        }
        return super.onOptionsItemSelected(item);
    }

}
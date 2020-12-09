package com.example.habitscalendar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.habitscalendar.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.github.sundeepk.compactcalendarview.CompactCalendarView.FILL_LARGE_INDICATOR;

public class CalendarActivity extends AppCompatActivity {

    private TextView calendarHabit;
    private TextView calendarReason;
    private TextView calendarStartDate;
    CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
    TextView calendarMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarMonth = (TextView) findViewById(R.id.calendarMonth);
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendarView);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        compactCalendarView.setEventIndicatorStyle(FILL_LARGE_INDICATOR);

        // Add event1 on December 3, 2020
        Event event = new Event(Color.RED, 1607026803000L, "Some extra data that I want to store.");
        compactCalendarView.addEvent(event);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                calendarMonth.setText(dateFormatMonth.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                calendarMonth.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

        calendarHabit = (TextView) findViewById(R.id.calendarHabit);
        calendarReason = (TextView) findViewById(R.id.calendarReason);
        calendarStartDate = (TextView) findViewById(R.id.calendarStartDate);

        Intent intent = getIntent();
        final String habitName = intent.getExtras().getString("HabitName");
        final String habitReason = intent.getExtras().getString("HabitReason");
        final String habitStartDate = intent.getExtras().getString("HabitStartDate");

        calendarHabit.setText(habitName);
        calendarReason.setText(habitReason);
        calendarStartDate.setText(habitStartDate);
    }
}
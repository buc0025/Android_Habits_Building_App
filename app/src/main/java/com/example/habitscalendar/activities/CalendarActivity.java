package com.example.habitscalendar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.habitscalendar.R;

public class CalendarActivity extends AppCompatActivity {

    private TextView calendarHabit;
    private TextView calendarReason;
    private TextView calendarStartDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

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
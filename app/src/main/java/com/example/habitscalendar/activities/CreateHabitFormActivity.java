package com.example.habitscalendar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.habitscalendar.R;
import com.example.habitscalendar.managers.HabitManager;
import com.example.habitscalendar.models.Habit;

import java.util.Date;

public class CreateHabitFormActivity extends AppCompatActivity {

    EditText edtTextHabitName, edtTextReason, edtTextStartDate;
    Button btnCreateHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit_form);

        edtTextHabitName = findViewById(R.id.edtTextHabitName);
        edtTextReason = findViewById(R.id.edtTextReason);
        edtTextStartDate = findViewById(R.id.edtTextStartDate);
        btnCreateHabit = findViewById(R.id.btnCreateHabit);

        btnCreateHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HabitManager habitManager = new HabitManager(CreateHabitFormActivity.this);
                Habit habit = new Habit(edtTextHabitName.getText().toString().trim(),
                        edtTextHabitName.getText().toString().trim(),
                        edtTextReason.getText().toString().trim(),
                        edtTextStartDate.getText().toString().trim());

                habitManager.addHabit(habit);
            }
        });
    }
}
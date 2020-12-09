package com.example.habitscalendar.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.habitscalendar.R;
import com.example.habitscalendar.managers.HabitManager;
import com.example.habitscalendar.models.Habit;
import com.example.habitscalendar.models.WeekDay;
import com.example.habitscalendar.models.WeekDayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HabitManager habitManager;
    private ListView listView;
    private ImageView noHabitsWarningImage;
    private TextView noHabitsTextView;
    private List<Habit> habitList;
    private RecyclerView recyclerView;
    private ArrayList<WeekDay> weekDays;
    private WeekDayAdapter weekDayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        noHabitsWarningImage = findViewById(R.id.noHabitsWarningImage);
        noHabitsTextView = findViewById(R.id.noHabitsTextView);
        habitManager = new HabitManager(MainActivity.this);
        habitList = habitManager.getAllHabits();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        Integer[] dayPics = {R.drawable.ic_day, R.drawable.ic_day, R.drawable.ic_day, R.drawable.ic_day
                , R.drawable.ic_day, R.drawable.ic_day, R.drawable.ic_day};

        // Create string array
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};

        // Initialize ArrayList
        weekDays = new ArrayList<>();
        for (int i = 0; i < dayNames.length; i++) {
            WeekDay weekDay = new WeekDay(dayPics[i], dayNames[i]);
            weekDays.add(weekDay);
        }

        // Design horizontal layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        weekDayAdapter = new WeekDayAdapter(MainActivity.this, weekDays);
        recyclerView.setAdapter(weekDayAdapter);

        listView = (ListView) findViewById(R.id.listView);
        noHabitsWarningImage = findViewById(R.id.noHabitsWarningImage);
        noHabitsTextView = findViewById(R.id.noHabitsTextView);

        habitManager = new HabitManager(MainActivity.this);
        habitList = habitManager.getAllHabits();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            showStartDialog();
        }

        if (habitManager.getAllHabits().size() == 0) {
            noHabitsWarningImage.setVisibility(View.VISIBLE);
            noHabitsTextView.setVisibility(View.VISIBLE);
        }

        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.habit_row, habitManager.getAllHabits());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                intent.putExtra("HabitName", habitList.get(position).getHabit());
                intent.putExtra("HabitReason", habitList.get(position).getReason());
                intent.putExtra("HabitStartDate", habitList.get(position).getStartDate());
                startActivity(intent);
            }
        });
    }

    private void showStartDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Welcome and start your habits today!")
                .setMessage("Please create a new habit.")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, CreateHabitFormActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .create().show();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_habit_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addHabit) {
            Intent intent = new Intent(MainActivity.this, CreateHabitFormActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
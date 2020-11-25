package com.example.habitscalendar.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.habitscalendar.R;
import com.example.habitscalendar.managers.HabitManager;
import com.example.habitscalendar.models.Habit;

public class MainActivity extends AppCompatActivity {

    HabitManager habitManager;
    ListView listView;
    ImageView noHabitsWarningImage;
    TextView noHabitsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        noHabitsWarningImage = findViewById(R.id.noHabitsWarningImage);
        noHabitsTextView = findViewById(R.id.noHabitsTextView);
        habitManager = new HabitManager(MainActivity.this);

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
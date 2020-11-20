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
    ImageView empty_imageview;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        habitManager = new HabitManager(MainActivity.this);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            showStartDialog();
        }



        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.habit_row, habitManager.getAllHabits());
        listView.setAdapter(adapter);
    }

    private void storeData() {
        Cursor cursor = habitManager.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                Habit habit = new Habit(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3));
                habitManager.allHabits.add(habit);
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
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
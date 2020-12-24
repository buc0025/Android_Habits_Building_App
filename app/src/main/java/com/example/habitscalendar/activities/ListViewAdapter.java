package com.example.habitscalendar.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitscalendar.R;
import com.example.habitscalendar.models.Habit;
import com.example.habitscalendar.models.WeekDayAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Habit> {

    private Context context;
    private int resource;
    private List<Habit> habitList;
    private WeekDayAdapter weekDayAdapter;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, int resource, List<Habit> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        habitList = objects;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        weekDayAdapter = new WeekDayAdapter(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String habitName = habitList.get(position).getHabit();

        // Get calendar set to current date and time
        Calendar calendar = Calendar.getInstance();

        // Set calendar to Sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String sundayAsString = null;
        String mondayAsString = null;
        String tuesdayAsString = null;
        String wednesdayAsString = null;
        String thursdayAsString = null;
        String fridayAsString = null;
        String saturdayAsString = null;

        // Set dates of the current week starting on Sunday
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                sundayAsString = dateFormat.format(calendar.getTime());
            }
            if (i == 1) {
                mondayAsString = dateFormat.format(calendar.getTime());
            }
            if (i == 2) {
                tuesdayAsString = dateFormat.format(calendar.getTime());
            }
            if (i == 3) {
                wednesdayAsString = dateFormat.format(calendar.getTime());
            }
            if (i == 4) {
                thursdayAsString = dateFormat.format(calendar.getTime());
            }
            if (i == 5) {
                fridayAsString = dateFormat.format(calendar.getTime());
            }
            if (i == 6) {
                saturdayAsString = dateFormat.format(calendar.getTime());
            }
            calendar.add(Calendar.DATE, 1);
        }

        // inflating recyclerView
        View view = layoutInflater.inflate(R.layout.habit_row, null, false);
        TextView habitTextView = (TextView) view.findViewById(R.id.habitName);
        habitTextView.setText(habitName);

        TextView sun = view.findViewById(R.id.sunday);
        sun.setText(sundayAsString);
        TextView mon = view.findViewById(R.id.monday);
        mon.setText(mondayAsString);
        TextView tues = view.findViewById(R.id.tuesday);
        tues.setText(tuesdayAsString);
        TextView wed = view.findViewById(R.id.wednesday);
        wed.setText(wednesdayAsString);
        TextView thur = view.findViewById(R.id.thursday);
        thur.setText(thursdayAsString);
        TextView fri = view.findViewById(R.id.friday);
        fri.setText(fridayAsString);
        TextView sat = view.findViewById(R.id.saturday);
        sat.setText(saturdayAsString);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(weekDayAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}

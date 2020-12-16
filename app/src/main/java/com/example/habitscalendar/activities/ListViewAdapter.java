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

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -3);
        Date sunday = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date monday = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tuesday = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date wednesday = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date thursday = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date friday = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date saturday = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("MM/dd");

        String sundayAsString = dateFormat.format(sunday);
        String mondayAsString = dateFormat.format(monday);
        String tuesdayAsString = dateFormat.format(tuesday);
        String wednesdayAsString = dateFormat.format(wednesday);
        String thursdayAsString = dateFormat.format(thursday);
        String fridayAsString = dateFormat.format(friday);
        String saturdayAsString = dateFormat.format(saturday);

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

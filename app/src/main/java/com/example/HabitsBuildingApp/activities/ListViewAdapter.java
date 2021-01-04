package com.example.HabitsBuildingApp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.HabitsBuildingApp.R;
import com.example.HabitsBuildingApp.managers.UtilityClass;
import com.example.HabitsBuildingApp.models.Habit;
import com.example.HabitsBuildingApp.models.WeekDayAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        weekDayAdapter = new WeekDayAdapter(context, position);
        String habitName = habitList.get(position).getHabit();

        // inflating recyclerView
        View view = layoutInflater.inflate(R.layout.habit_row, null, false);
        TextView habitTextView = (TextView) view.findViewById(R.id.habitName);
        habitTextView.setText(habitName);

        TextView sun = view.findViewById(R.id.sunday);
        sun.setText(UtilityClass.listViewDates(0));
        TextView mon = view.findViewById(R.id.monday);
        mon.setText(UtilityClass.listViewDates(1));
        TextView tues = view.findViewById(R.id.tuesday);
        tues.setText(UtilityClass.listViewDates(2));
        TextView wed = view.findViewById(R.id.wednesday);
        wed.setText(UtilityClass.listViewDates(3));
        TextView thur = view.findViewById(R.id.thursday);
        thur.setText(UtilityClass.listViewDates(4));
        TextView fri = view.findViewById(R.id.friday);
        fri.setText(UtilityClass.listViewDates(5));
        TextView sat = view.findViewById(R.id.saturday);
        sat.setText(UtilityClass.listViewDates(6));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(weekDayAdapter);

        if (position == 0) {
            recyclerView.setBackgroundColor(0xFFf542f5);
        }
        if (position == 1) {
            recyclerView.setBackgroundColor(0xFF180cf2);
        }
        if (position == 2) {
            recyclerView.setBackgroundColor(0xFFf2230c);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}

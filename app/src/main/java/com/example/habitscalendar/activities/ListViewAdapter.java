package com.example.habitscalendar.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.habitscalendar.R;
import com.example.habitscalendar.models.Habit;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Habit> {

    private Context context;
    private int resource;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<Habit> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String id = getItem(position).getHabitId();
        String habitName = getItem(position).getHabit();
        String reason = getItem(position).getReason();
        String start = getItem(position).getStartDate();

        Habit habit = new Habit(id, habitName, reason, start);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView habitTextView = (TextView) convertView.findViewById(R.id.habitName);

        habitTextView.setText(habitName);

        return convertView;
    }
}

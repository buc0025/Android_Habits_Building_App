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
    private List<Habit> habitList;

    public ListViewAdapter(Context context, int resource, List<Habit> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        habitList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String habitName = habitList.get(position).getHabit();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView habitTextView = (TextView) convertView.findViewById(R.id.habitName);

        habitTextView.setText(habitName);

        return convertView;
    }
}

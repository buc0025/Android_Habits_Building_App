package com.example.HabitsBuildingApp.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.HabitsBuildingApp.R;
import com.example.HabitsBuildingApp.activities.ListViewAdapter;
import com.example.HabitsBuildingApp.activities.MainActivity;
import com.example.HabitsBuildingApp.managers.HabitManager;
import com.example.HabitsBuildingApp.managers.UtilityClass;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.ViewHolder> {

    private Context context;
    private HabitManager habitManager;
    private List<Habit> habitList;
    private List<Long> epochTimes;
    private int listPosition;
    private static final int DAYS_IN_TWO_WEEKS = 14;

    public WeekDayAdapter (Context context, int listPosition) {
        this.context = context;
        this.listPosition = listPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        habitManager = new HabitManager(context);
        habitList = habitManager.getAllHabits();
        String habitId = habitList.get(listPosition).getHabitId();
        epochTimes = habitManager.getHabitCompletedDates(habitId);

        for (int i = 0; i < 14; i++) {
            if (position == i) {
                holder.weekDayTextView.setText(UtilityClass.dayAbbrev.get(i));
                holder.weekViewDate.setText(UtilityClass.weekViewDate(i));
                holder.weekViewMonth.setText(UtilityClass.monthAbbreviation(UtilityClass.weekViewAdapterDate(i)));
            }
            if (position == i && epochTimes.contains(UtilityClass.weekDayPositionToLong(position))) {
                holder.cardView.setCardBackgroundColor(Color.GREEN);
            }
        }
    }

    @Override
    public int getItemCount() {
        return DAYS_IN_TWO_WEEKS;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        CardView cardView2;
        TextView weekDayTextView;
        TextView weekViewMonth;
        TextView weekViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            weekDayTextView = (TextView) itemView.findViewById(R.id.weekDayTextView);
            cardView2 = (CardView) itemView.findViewById(R.id.cardView2);
            weekViewMonth = (TextView) itemView.findViewById(R.id.weekViewMonth);
            weekViewDate = (TextView) itemView.findViewById(R.id.weekViewDate);

            weekViewDate.setOnClickListener(this);
            weekViewMonth.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            habitManager = new HabitManager(context);

            String day = "";

            for (int i = 0; i < 7; i++) {
                if (getAdapterPosition() == i) {
                    day = UtilityClass.weekViewAdapterDate(6 - i);
                }
            }

            for (int i = 7; i < 14; i++) {
                if (getAdapterPosition() == i) {
                    day = UtilityClass.weekViewAdapterDate(i);
                }
            }

            habitList = habitManager.getAllHabits();

            // Need to pass in position of ListView
            final String habitID = habitList.get(listPosition).getHabitId();

            epochTimes = habitManager.getHabitCompletedDates(habitID);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            if (v.getId() == weekViewDate.getId() || v.getId() == weekViewMonth.getId()) {
                Date today = Calendar.getInstance().getTime();
                final Date dateClicked = UtilityClass.stringToDate(day);
                final String selectedDate = day;

                if (UtilityClass.convertToEpoch(dateClicked) > UtilityClass.convertToEpoch(today)) {
                    builder.setMessage("Future dates can't be added")
                            .setPositiveButton("Ok", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (epochTimes.contains(UtilityClass.convertToEpoch(dateClicked))) {
                    builder.setMessage("Are you sure you want to delete date?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    habitManager.deleteDate(habitID, selectedDate);

                                    int index = epochTimes.indexOf(UtilityClass.convertToEpoch(dateClicked));
                                    epochTimes.remove(index);
                                    Intent intent = new Intent(context, MainActivity.class);
                                    ListViewAdapter.context.startActivity(intent);
                                    cardView2.setCardBackgroundColor(Color.WHITE);
                                }
                            })
                            .setNegativeButton("No", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    habitManager.addDate(habitID, day);
                    epochTimes.add(UtilityClass.convertToEpoch(dateClicked));
                    Intent intent = new Intent(context, MainActivity.class);
                    ListViewAdapter.context.startActivity(intent);
                    cardView2.setCardBackgroundColor(Color.GREEN);
                }
            }
        }
    }
}

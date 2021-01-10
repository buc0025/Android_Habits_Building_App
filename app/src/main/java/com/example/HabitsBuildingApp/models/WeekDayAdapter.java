package com.example.HabitsBuildingApp.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.HabitsBuildingApp.R;
import com.example.HabitsBuildingApp.managers.HabitManager;
import com.example.HabitsBuildingApp.managers.UtilityClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.ViewHolder> {

    private Context context;
    private HabitManager habitManager;
    private List<Habit> habitList;
    private List<Long> epochTimes;
    private int listPosition;
    private static final int DAYS_IN_A_WEEK = 7;

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
        if (position == 0) {
            holder.weekDayTextView.setText("Sun");
        } else if (position == 1) {
            holder.weekDayTextView.setText("Mon");
        } else if (position == 2) {
            holder.weekDayTextView.setText("Tue");
        } else if (position == 3) {
            holder.weekDayTextView.setText("Wed");
        } else if (position == 4) {
            holder.weekDayTextView.setText("Thur");
        } else if (position == 5) {
            holder.weekDayTextView.setText("Fri");
        } else {
            holder.weekDayTextView.setText("Sat");
        }
    }

    @Override
    public int getItemCount() {
        return DAYS_IN_A_WEEK;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView weekDayTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            weekDayTextView = (TextView) itemView.findViewById(R.id.weekDayTextView);
            weekDayTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            habitManager = new HabitManager(context);

            String day = "";
            switch (getAdapterPosition()) {
                case 0:
                    day = UtilityClass.weekDayAdapterDates(0);
                    break;
                case 1:
                    day = UtilityClass.weekDayAdapterDates(1);
                    break;
                case 2:
                    day = UtilityClass.weekDayAdapterDates(2);
                    break;
                case 3:
                    day = UtilityClass.weekDayAdapterDates(3);
                    break;
                case 4:
                    day = UtilityClass.weekDayAdapterDates(4);
                    break;
                case 5:
                    day = UtilityClass.weekDayAdapterDates(5);
                    break;
                case 6:
                    day = UtilityClass.weekDayAdapterDates(6);
                    break;
            }

            habitList = habitManager.getAllHabits();

            // Need to pass in position of ListView
            final String habitID = habitList.get(listPosition).getHabitId();

            epochTimes = habitManager.getHabitCompletedDates(habitID);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            if (v.getId() == weekDayTextView.getId()) {
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
                                }
                            })
                            .setNegativeButton("No", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    habitManager.addDate(habitID, day);
                    epochTimes.add(UtilityClass.convertToEpoch(dateClicked));
                }
            }
        }
    }
}

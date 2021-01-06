package com.example.HabitsBuildingApp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private Integer[] dayPics = {R.drawable.sunday, R.drawable.monday, R.drawable.tuesday, R.drawable.wednesday
            , R.drawable.thursday, R.drawable.friday, R.drawable.saturday};

    private Context context;
    private HabitManager habitManager;
    public List<Habit> habitList;
    private int listPosition;

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
        holder.weekDayImageView.setImageResource(dayPics[position]);
    }

    @Override
    public int getItemCount() {
        return dayPics.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView weekDayImageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekDayImageView = itemView.findViewById(R.id.weekDayImageView);
            cardView = itemView.findViewById(R.id.cardView);
            weekDayImageView.setOnClickListener(this);
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
            String habitID = habitList.get(listPosition).getHabitId();

            if (v.getId() == weekDayImageView.getId()) {
                Date today = Calendar.getInstance().getTime();
                Date dateClicked = UtilityClass.stringToDate(day);

                if (UtilityClass.convertToEpoch(dateClicked) > UtilityClass.convertToEpoch(today)) {
                    Toast.makeText(v.getContext(), "Future dates can't be added", Toast.LENGTH_SHORT).show();
                } else {
                    habitManager.addDate(habitID, day);
                }
            }
        }
    }
}

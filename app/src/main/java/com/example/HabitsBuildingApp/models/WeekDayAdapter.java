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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

            Calendar calendar = Calendar.getInstance();
            // Dates are added and converted into string and Toast message is displayed as "MM/dd/YYYY"
            // when days of the week in recyclerView is clicked
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
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

            String day = "";
            switch (getAdapterPosition()) {
                case 0:
                    day = sundayAsString;
                    break;
                case 1:
                    day = mondayAsString;
                    break;
                case 2:
                    day = tuesdayAsString;
                    break;
                case 3:
                    day = wednesdayAsString;
                    break;
                case 4:
                    day = thursdayAsString;
                    break;
                case 5:
                    day = fridayAsString;
                    break;
                case 6:
                    day = saturdayAsString;
                    break;
            }

            habitList = habitManager.getAllHabits();

            // Need to pass in position of ListView
            String habitID = habitList.get(listPosition).getHabitId();

            if (v.getId() == weekDayImageView.getId()) {
                habitManager.addDate(habitID, day);
                Toast.makeText(v.getContext(), "day pressed = " + day, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

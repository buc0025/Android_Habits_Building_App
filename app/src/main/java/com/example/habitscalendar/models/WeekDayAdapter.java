package com.example.habitscalendar.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitscalendar.R;
import com.example.habitscalendar.managers.HabitManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.ViewHolder> {
    private Integer[] dayPics = {R.drawable.sunday, R.drawable.monday, R.drawable.tuesday, R.drawable.wednesday
            , R.drawable.thursday, R.drawable.friday, R.drawable.saturday};

    private Context context;
    private HabitManager habitManager;

    public WeekDayAdapter (Context context) {
        this.context = context;
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
            calendar.add(Calendar.DAY_OF_YEAR, -1);
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

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            String sundayAsString = dateFormat.format(sunday);
            String mondayAsString = dateFormat.format(monday);
            String tuesdayAsString = dateFormat.format(tuesday);
            String wednesdayAsString = dateFormat.format(wednesday);
            String thursdayAsString = dateFormat.format(thursday);
            String fridayAsString = dateFormat.format(friday);
            String saturdayAsString = dateFormat.format(saturday);
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

            if (v.getId() == weekDayImageView.getId()) {
                habitManager.addDate(???, day);
                Toast.makeText(v.getContext(), "day pressed = " + day, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

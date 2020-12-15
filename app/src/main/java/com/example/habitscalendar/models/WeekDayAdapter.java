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

import java.util.ArrayList;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.ViewHolder> {
    private Integer[] dayPics = {R.drawable.sunday, R.drawable.monday, R.drawable.tuesday, R.drawable.wednesday
            , R.drawable.thursday, R.drawable.friday, R.drawable.saturday};

    private Context context;

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
            String day = "";
            switch (getAdapterPosition()) {
                case 0:
                    day = "Sunday";
                    break;
                case 1:
                    day = "Monday";
                    break;
                case 2:
                    day = "Tuesday";
                    break;
                case 3:
                    day = "Wednesday";
                    break;
                case 4:
                    day = "Thursday";
                    break;
                case 5:
                    day = "Friday";
                    break;
                case 6:
                    day = "Saturday";
                    break;
            }

            if (v.getId() == weekDayImageView.getId()) {
                Toast.makeText(v.getContext(), "day pressed = " + day, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

package com.example.habitscalendar.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitscalendar.R;

import java.util.ArrayList;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.ViewHolder> {
    ArrayList<WeekDay> weekDays;
    Context context;

    public WeekDayAdapter (Context context, ArrayList<WeekDay> weekDays) {
        this.context = context;
        this.weekDays = weekDays;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.weekDayImageView.setImageResource(weekDays.get(position).getDayPics());
        holder.weekDayTextView.setText(weekDays.get(position).getDayNames());
    }

    @Override
    public int getItemCount() {
        return weekDays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView weekDayImageView;
        TextView weekDayTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekDayImageView = itemView.findViewById(R.id.weekDayImageView);
            weekDayTextView = itemView.findViewById(R.id.weekDayTextView);
        }
    }
}

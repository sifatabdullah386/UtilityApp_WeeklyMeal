package com.smilesifat.weeklymeal.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smilesifat.weeklymeal.R;
import com.smilesifat.weeklymeal.listeners.WeekDayListeners;
import com.smilesifat.weeklymeal.model.WeekNameModel;

import java.util.ArrayList;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.MyViewHolder> {
    Context context;
    ArrayList<WeekNameModel> weekDayModel;
    WeekDayListeners weekDayListener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public WeekDayAdapter(Context context, ArrayList<WeekNameModel> weekDayModel, WeekDayListeners weekDayListener) {
        this.context = context;
        this.weekDayModel = weekDayModel;
        this.weekDayListener = weekDayListener;
    }

    @NonNull
    @Override
    public WeekDayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_week_name, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.dayName.setText(weekDayModel.get(position).getName());
        holder.dayName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weekDayListener.onWeekDayClick(weekDayModel.get(position).getId(), weekDayModel.get(position).getName());
            }
        });

        holder.itemView.setSelected(selectedPosition == position);
        holder.itemView.setOnClickListener(v -> {
            notifyItemChanged(selectedPosition);
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return weekDayModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dayName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.tv_day_name);
        }
    }
}
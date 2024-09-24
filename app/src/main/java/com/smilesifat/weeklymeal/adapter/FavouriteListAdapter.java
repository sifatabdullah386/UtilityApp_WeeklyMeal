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
import com.smilesifat.weeklymeal.model.FavouriteModel;

import java.util.ArrayList;

public class FavouriteListAdapter extends RecyclerView.Adapter<FavouriteListAdapter.MyViewHolder> {
    Context context;
    ArrayList<FavouriteModel> favouriteModel;

    public FavouriteListAdapter(Context context, ArrayList<FavouriteModel> favouriteModel) {
        this.context = context;
        this.favouriteModel = favouriteModel;
    }

    @NonNull
    @Override
    public FavouriteListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_favourite_item, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.favouriteItem.setText(favouriteModel.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return favouriteModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView favouriteItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            favouriteItem = itemView.findViewById(R.id.tv_favourite_item);
        }
    }
}
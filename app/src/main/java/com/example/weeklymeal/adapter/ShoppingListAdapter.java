package com.example.weeklymeal.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weeklymeal.R;
import com.example.weeklymeal.model.ShoppingListModel;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.MyViewHolder> {
    Context context;
    ArrayList<ShoppingListModel> shoppingListModel;

    public ShoppingListAdapter(Context context, ArrayList<ShoppingListModel> shoppingListModel) {
        this.context = context;
        this.shoppingListModel = shoppingListModel;
    }

    @NonNull
    @Override
    public ShoppingListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_shopping_item, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.shoppingItem.setText(shoppingListModel.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return shoppingListModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView shoppingItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shoppingItem = itemView.findViewById(R.id.tv_shopping_item);
        }
    }
}
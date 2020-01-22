package com.example.baity.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baity.Activities.Slider_result;
import com.example.baity.Model.Favourite_model;
import com.example.baity.Model.MyData;
import com.example.baity.R;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    Favourite_model[] favourite_models;
    public FavouriteAdapter(Favourite_model[] favourite_models){
        this.favourite_models=favourite_models;
    }
    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.my_favourite_item, parent, false);
        FavouriteAdapter.ViewHolder viewHolder = new FavouriteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {
        final Favourite_model favourite_model = favourite_models[position];
        holder.title.setText(favourite_model.getTitle());
        holder.description.setText(favourite_model.getDescription());
        holder.imageFavourtie.setImageResource(favourite_model.getImage());
    }

    @Override
    public int getItemCount() {
        return favourite_models.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,description;
        ImageView imageFavourtie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.favourite_title);
            description = itemView.findViewById(R.id.favourite_description);
            imageFavourtie = itemView.findViewById(R.id.image_favourite);
        }
    }
}

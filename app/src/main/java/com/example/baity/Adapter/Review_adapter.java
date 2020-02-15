package com.example.baity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baity.Designs.MyTextViewNormal;
import com.example.baity.Model.Review_model;
import com.example.baity.Model.Review_user_item;
import com.example.baity.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Review_adapter extends RecyclerView.Adapter<Review_adapter.ViewHolder> {
    List<Review_user_item> review_user_items;

    public Review_adapter(List<Review_user_item> review_user_items) {
        this.review_user_items = review_user_items;
    }


    @NonNull
    @Override
    public Review_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.review_item, parent, false);
        Review_adapter.ViewHolder viewHolder = new Review_adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Review_adapter.ViewHolder holder, int position) {
        String image = review_user_items.get(position).getImage();
        Bitmap decodeTxt=decodeBase64(image);
        holder.reviewImage.setImageBitmap(decodeTxt);
        holder.username.setText(review_user_items.get(position).getName());
        double rate = review_user_items.get(position).getRate();
        holder.ratingBar.setRating((float) rate);
    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
    @Override
    public int getItemCount() {
        return review_user_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView reviewImage;
        RatingBar ratingBar;
        MyTextViewNormal username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewImage = itemView.findViewById(R.id.reviewImage);
            username = itemView.findViewById(R.id.review_username);
            ratingBar = itemView.findViewById(R.id.rate);

        }
    }
}

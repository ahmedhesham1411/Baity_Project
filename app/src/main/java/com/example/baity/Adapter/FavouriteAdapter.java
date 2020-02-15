package com.example.baity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baity.Activities.Child.Sub_category;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.Favourite_Slider_model;
import com.example.baity.Model.Favourite_model;
import com.example.baity.Model.MyFavouriteModel;
import com.example.baity.Model.Sub_parent_model;
import com.example.baity.Model.Sub_slider_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {

    List<MyFavouriteModel> myFavouriteModels;
    Context context;
    Preferences preferences;
    List<Favourite_Slider_model> myFavourite_Slider_model;
    List<String> imagesList;

    public FavouriteAdapter( List<MyFavouriteModel> myFavouriteModels, Context context) {
        this.myFavouriteModels = myFavouriteModels;
        this.context = context;
    }


    @NonNull
    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_favourite_item, null);
        return new FavouriteAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.MyViewHolder holder, int position) {
        String nameA = myFavouriteModels.get(position).getNameA();
        String nameE = myFavouriteModels.get(position).getNameE();
        int id = myFavouriteModels.get(position).getId();
        myFavourite_Slider_model = myFavouriteModels.get(position).getAdsVMs();
        //MyFavouriteModel myFavouriteModel = new MyFavouriteModel(id,nameA,nameE,myFavourite_Slider_model);

        holder.favourite_title.setGravity(View.TEXT_ALIGNMENT_TEXT_END);
        holder.favourite_title.setText(nameA);
        holder.favourite_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.Save_Sub_id(v.getContext(),id);
                Intent intent = new Intent(v.getContext(), Sub_category.class);
                v.getContext().startActivity(intent);
            }
        });


        List<Favourite_Slider_model> favourite_slider_modelss = myFavouriteModels.get(position).getAdsVMs();

        imagesList = new ArrayList<String>();

        for (int j = 0; j < favourite_slider_modelss.size();j++){
            imagesList.add(favourite_slider_modelss.get(j).getImagepath());
        }



        myFavouriteSliderAdapter childRecyclerViewAdapter = new myFavouriteSliderAdapter(context,myFavourite_Slider_model,id);
        holder.child.setSliderAdapter(childRecyclerViewAdapter);
        holder.child.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        holder.child.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        holder.child.setScrollTimeInSec(4);
        holder.child.startAutoCycle();
        childRecyclerViewAdapter.notifyDataSetChanged();

        holder.favouriteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(imagesList);
                preferences.Save_listt(context,json);

                preferences.Save_Sub_id(v.getContext(),id);
                Intent intent = new Intent(v.getContext(), Sub_category.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myFavouriteModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SliderView child;
        MyTextViewBold favourite_title;
        LinearLayoutCompat favouriteLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            child = itemView.findViewById(R.id.favouriteSlider);
            favourite_title = itemView.findViewById(R.id.favourite_title);
            favouriteLayout = itemView.findViewById(R.id.favouriteLayout);
        }
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
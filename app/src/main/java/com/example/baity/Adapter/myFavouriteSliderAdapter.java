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

import com.example.baity.Activities.Child.Sub_category;
import com.example.baity.Model.Favourite_Slider_model;
import com.example.baity.Model.Favourite_model;
import com.example.baity.Model.Sub_parent_model;
import com.example.baity.Model.Sub_slider_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class myFavouriteSliderAdapter extends SliderViewAdapter<myFavouriteSliderAdapter.SliderAdapterVH> {

    private Context context;
    List<Favourite_Slider_model> favourite_slider_models;
    List<String> imagesList;
    int sub_id;
    Preferences preferences;

    public myFavouriteSliderAdapter(Context context,List<Favourite_Slider_model> favourite_slider_models,int sub_id) {
        this.context = context;
        this.favourite_slider_models = favourite_slider_models;
        this.sub_id = sub_id;
    }

    @Override
    public myFavouriteSliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_image, null);
        return new myFavouriteSliderAdapter.SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(myFavouriteSliderAdapter.SliderAdapterVH viewHolder, int position) {

        List<Favourite_Slider_model> favourite_slider_modelss = favourite_slider_models;
        imagesList = new ArrayList<String>();

        for (int j = 0; j < favourite_slider_modelss.size();j++){
            String image = favourite_slider_modelss.get(j).getImagepath();
            Bitmap decodeTxt=decodeBase64(image);
            viewHolder.imageViewBackground.setImageBitmap(decodeTxt);
            imagesList.add(favourite_slider_modelss.get(j).getImagepath());
        }

        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(imagesList);
                preferences.Save_listt(context,json);

                preferences.Save_Sub_id(v.getContext(),sub_id);
                Intent intent = new Intent(v.getContext(), Sub_category.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getCount() {
        return favourite_slider_models.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.fav_image);
            this.itemView = itemView;
        }
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}

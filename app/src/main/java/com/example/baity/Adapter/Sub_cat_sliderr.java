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

import com.example.baity.Activities.Reviews.Review_company;
import com.example.baity.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Sub_cat_sliderr extends SliderViewAdapter<Sub_cat_sliderr.SliderAdapterVH> {

    Context context;
    List<String> sub_slider_models;
    ArrayList<String> imagesList;


    public Sub_cat_sliderr(Context context,ArrayList<String> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @Override
    public Sub_cat_sliderr.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_itemmmm, null);
        return new Sub_cat_sliderr.SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(Sub_cat_sliderr.SliderAdapterVH viewHolder, int position) {

        ArrayList<String> imageslist1 = imagesList;
        for (int j=0; j < imageslist1.size(); j++){

            String image = imageslist1.get(position);
            Bitmap decodeTxt=decodeBase64(image);
            viewHolder.imageViewBackground.setImageBitmap(decodeTxt);

        }

        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Review_company.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imgwwwxxx);
            this.itemView = itemView;
        }
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}


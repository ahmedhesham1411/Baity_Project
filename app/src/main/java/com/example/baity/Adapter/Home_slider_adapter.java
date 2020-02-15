package com.example.baity.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.baity.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Home_slider_adapter extends SliderViewAdapter<Home_slider_adapter.SliderAdapterVH> {

    Context context;
    List<String> imagesList;

    public Home_slider_adapter(Context context,List<String> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @Override
    public Home_slider_adapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_home, null);
        return new Home_slider_adapter.SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(Home_slider_adapter.SliderAdapterVH viewHolder, int position) {

        List<String> imageslist1 = imagesList;
        for (int j=0; j < imageslist1.size(); j++){

            String image = imageslist1.get(position);
            Bitmap decodeTxt=decodeBase64(image);
            viewHolder.imageViewBackground.setImageBitmap(decodeTxt);

        }

        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            imageViewBackground = itemView.findViewById(R.id.imageHome);
            this.itemView = itemView;
        }
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
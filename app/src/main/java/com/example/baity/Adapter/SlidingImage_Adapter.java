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

import com.example.baity.Model.Sub_parent_model;
import com.example.baity.Model.Sub_slider_model;
import com.example.baity.R;
import com.example.baity.Activities.Child.Sub_category;
import com.example.baity.Utils.Preferences;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlidingImage_Adapter extends SliderViewAdapter<SlidingImage_Adapter.SliderAdapterVH> {

    private Context context;
    Sub_parent_model sub_parent_model;
    List<Sub_slider_model> sub_slider_models;
    Preferences preferences;
    String namea;
    int sub_id,companyid;
    List<String> imagesList;

    public SlidingImage_Adapter(Context context,Sub_parent_model sub_parent_model,List<Sub_slider_model> sub_slider_models) {
        this.context = context;
        this.sub_parent_model = sub_parent_model;
        this.sub_slider_models = sub_slider_models;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        List<Sub_slider_model> sub_slider_modelss = sub_slider_models;

        imagesList = new ArrayList<String>();

        for (int j = 0; j < sub_slider_modelss.size();j++){
            String image = sub_slider_modelss.get(position).getImagepath();
            Bitmap decodeTxt=decodeBase64(image);
            viewHolder.imageViewBackground.setImageBitmap(decodeTxt);
            imagesList.add(sub_slider_modelss.get(j).getImagepath());
        }

        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namea =  sub_parent_model.getNameA();
                preferences.Save_Sub_name(context,namea);
                sub_id = sub_parent_model.getId();
                preferences.Save_Sub_id(context,sub_id);
                companyid = sub_slider_modelss.get(position).getCompany_id();
                preferences.Save_company_id(context,companyid);

                Gson gson = new Gson();
                String json = gson.toJson(imagesList);
                preferences.Save_listt(context,json);

                Intent intent = new Intent(context, Sub_category.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getCount() {
        return sub_slider_models.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imgwww);
            this.itemView = itemView;
        }
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
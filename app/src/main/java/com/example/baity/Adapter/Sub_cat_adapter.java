/*
package com.example.baity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.Child_model;
import com.example.baity.Model.Sub_parent_model;
import com.example.baity.Model.Sub_slider_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class Sub_cat_adapter extends RecyclerView.Adapter<Sub_cat_adapter.MyViewHolder> {

    List<Child_model> child_models;
    Context context;
    Preferences preferences;

    public Sub_cat_adapter( List<Child_model> child_models, Context context) {
        this.child_models = child_models;
        this.context = context;
    }

    @NonNull
    @Override
    public Sub_cat_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_row, null);
        return new Sub_cat_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Sub_cat_adapter.MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return sub_parent_models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SliderView child;
        MyTextViewBold textViewBold;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            child = itemView.findViewById(R.id.imageSliderr);
            textViewBold = itemView.findViewById(R.id.slider_namewe);
        }
    }

}*/

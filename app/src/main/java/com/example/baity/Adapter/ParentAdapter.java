package com.example.baity.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.Sub_parent_model;
import com.example.baity.Model.Sub_slider_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;
import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder> {

    List<Sub_parent_model> sub_parent_models;
    Context context;
    Preferences preferences;

    public ParentAdapter( List<Sub_parent_model> sub_parent_models, Context context) {
        this.sub_parent_models = sub_parent_models;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_row, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String nameA = sub_parent_models.get(position).getNameA();
        String nameE = sub_parent_models.get(position).getNameE();
        int id = sub_parent_models.get(position).getId();
        List<Sub_slider_model> sub_slider_models = sub_parent_models.get(position).getSub_slider_models();
        Sub_parent_model subParentModel = new Sub_parent_model(id,nameA,nameE,sub_slider_models);


        holder.textViewBold.setText( sub_parent_models.get(position).getNameA());

        SlidingImage_Adapter childRecyclerViewAdapter = new SlidingImage_Adapter(context,subParentModel,sub_slider_models);
        holder.child.setSliderAdapter(childRecyclerViewAdapter);
        holder.child.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        holder.child.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        holder.child.setScrollTimeInSec(4);
        holder.child.startAutoCycle();
        childRecyclerViewAdapter.notifyDataSetChanged();

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

}
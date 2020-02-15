package com.example.baity.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baity.Model.Home_model;
import com.example.baity.R;
import com.example.baity.Activities.Slider_Result.Slider_result;
import com.example.baity.Utils.Constant;
import com.example.baity.Utils.Preferences;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<Home_model> home_models;
    Preferences preferences;

    public List<Home_model> getHome_models() {
        return home_models;
    }

    public void setHome_models(List<Home_model> home_models) {
        this.home_models = home_models;
    }

    public HomeAdapter(List<Home_model> home_models,Context context) {
        this.context = context;
        this.home_models = home_models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(home_models.get(position).getNameA());
        String image = Constant.BASEURL +"Content/Images/" + home_models.get(position).getImagepath();
        Glide.with(context).load(image).into(holder.imageView);
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Slider_result.class);

                preferences.SaveMainCat_id(context,home_models.get(position).getId());
                preferences.SaveMainCat_name(context,home_models.get(position).getNameA());
                preferences.SaveMainCat_image(context,image);

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return home_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        FrameLayout frameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            frameLayout = itemView.findViewById(R.id.frame_id);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

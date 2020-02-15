package com.example.baity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baity.Activities.Child_resault.Sub_category_result;
import com.example.baity.Model.Child_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import java.util.List;

public class Sub_items_adapter extends RecyclerView.Adapter<Sub_items_adapter.ViewHolder> {
    Context context;
    List<Child_model> child_models;
    Preferences preferences;


    public Sub_items_adapter(List<Child_model> child_models,Context context) {
        this.child_models = child_models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sub_category_item, parent, false);
        Sub_items_adapter.ViewHolder viewHolder = new Sub_items_adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String des = child_models.get(position).getNameA();
        holder.textView.setText(des);
        preferences.Save_Child_id(context,child_models.get(position).getId());
        preferences.Save_Child_name(context,des);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Sub_category_result.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return child_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayoutCompat linearLayoutCompat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.listTextView);
            linearLayoutCompat = itemView.findViewById(R.id.sub_item_layout);
        }
    }
}

package com.example.baity.Activities.MyFavourite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baity.Activities.BaseActivity;
import com.example.baity.Adapter.FavouriteAdapter;
import com.example.baity.Adapter.HomeAdapter;
import com.example.baity.Model.Favourite_model;
import com.example.baity.Model.MyData;
import com.example.baity.Model.MyFavouriteModel;
import com.example.baity.R;

import java.util.List;

public class My_favourite extends BaseActivity implements My_Favourite_Interface {
    My_Favourite_Presenter my_favourite_presenter;
    RecyclerView recyclerView;
    AppCompatImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);

        my_favourite_presenter = new My_Favourite_Presenter( this,this);

        my_favourite_presenter.GetFavourite();
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

    }

    @Override
    public void sendFavourite(List<MyFavouriteModel> myFavouriteModels) {
        recyclerView = findViewById(R.id.favourite_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(myFavouriteModels,this);
        recyclerView.setAdapter(favouriteAdapter);

    }
}

package com.example.baity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baity.Adapter.FavouriteAdapter;
import com.example.baity.Adapter.HomeAdapter;
import com.example.baity.Model.Favourite_model;
import com.example.baity.Model.MyData;
import com.example.baity.R;

public class My_favourite extends BaseActivity {
    RecyclerView recyclerView;
    AppCompatImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Favourite_model[] favourite_models = new Favourite_model[]{
                new Favourite_model("What is Lorem Ipsum?",
                        "Lorem Ipsum is simply dummy\n" +
                                "text of the printing and typesetting",
                        R.drawable.dekor),
                new Favourite_model("What is Lorem Ipsum?",
                        "Lorem Ipsum is simply dummy\n" +
                                "text of the printing and typesetting",
                        R.drawable.tasesat),
                new Favourite_model("What is Lorem Ipsum?",
                        "Lorem Ipsum is simply dummy\n" +
                                "text of the printing and typesetting",
                        R.drawable.azl),
                new Favourite_model("What is Lorem Ipsum?",
                        "Lorem Ipsum is simply dummy\n" +
                                "text of the printing and typesetting",
                        R.drawable.estsharat),
                new Favourite_model("What is Lorem Ipsum?",
                "Lorem Ipsum is simply dummy\n" +
                        "text of the printing and typesetting",
                R.drawable.dekor),
                new Favourite_model("What is Lorem Ipsum?",
                        "Lorem Ipsum is simply dummy\n" +
                                "text of the printing and typesetting",
                        R.drawable.azl),
                new Favourite_model("What is Lorem Ipsum?",
                        "Lorem Ipsum is simply dummy\n" +
                                "text of the printing and typesetting",
                        R.drawable.tasesat)
        };

        recyclerView = findViewById(R.id.favourite_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(favourite_models);
        recyclerView.setAdapter(favouriteAdapter);
    }
}

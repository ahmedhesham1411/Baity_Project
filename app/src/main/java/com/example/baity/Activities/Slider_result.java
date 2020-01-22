package com.example.baity.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baity.Adapter.SliderAdapterExample;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.ImageSlider;
import com.example.baity.R;
import com.smarteist.autoimageslider.SliderView;

public class Slider_result extends BaseActivity {
    MyTextViewBold textViewDescription;
    AppCompatImageView favoriteBtn,settingBtn,backBtn,closeAdBtn,favouriteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_result);

        showAdDialog();
        textViewDescription = findViewById(R.id.slider_name);
        favoriteBtn = findViewById(R.id.favorieBtn);
        settingBtn = findViewById(R.id.settingBtn);
        backBtn = findViewById(R.id.btnBack);
        favoriteBtn = findViewById(R.id.favorieBtn);

        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Integer image = intent.getIntExtra("image",0);

        ImageSlider imageSlider = new ImageSlider(name, image);
        SliderView sliderView = findViewById(R.id.imageSlider);
        SliderAdapterExample adapter = new SliderAdapterExample(this,imageSlider);
        sliderView.setSliderAdapter(adapter);

        textViewDescription.setText(name);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Slider_result.this,Setting.class);
                startActivity(intent);
            }
        });
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),My_favourite.class);
                startActivity(intent);
            }
        });

    }
    private void showAdDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.show_ad, viewGroup, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        closeAdBtn = dialogView.findViewById(R.id.closeAd);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        closeAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

}

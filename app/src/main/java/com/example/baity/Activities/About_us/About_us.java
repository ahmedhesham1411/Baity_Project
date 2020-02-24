package com.example.baity.Activities.About_us;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baity.Activities.BaseActivity;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.About_us_response;
import com.example.baity.R;

public class About_us extends BaseActivity implements About_us_interface {
    About_us_presenter about_us_presenter;
    AppCompatImageView backBtn;
    MyTextViewBold Des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        about_us_presenter = new About_us_presenter(this,this);
        about_us_presenter.GetAboutDes();
        backBtn = findViewById(R.id.btnBack);
        Des = findViewById(R.id.About_des);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void sendDes(About_us_response about_us_response) {
        String des = about_us_response.getDescription();
        Des.setText(des);
    }
}

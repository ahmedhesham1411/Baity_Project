package com.example.baity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.R;

public class MyProfile extends BaseActivity {
    MyTextViewBold userName,email,mobilePhone,password;
    MyButtonBold btnEdit;
    AppCompatImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        userName = findViewById(R.id.profileUserName);
        email = findViewById(R.id.profileEmail);
        mobilePhone = findViewById(R.id.profilePhone);
        password = findViewById(R.id.profilePassword);
        btnEdit = findViewById(R.id.btnEdit);
        btnBack = findViewById(R.id.btnBack);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Edit_myProfile.class);
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

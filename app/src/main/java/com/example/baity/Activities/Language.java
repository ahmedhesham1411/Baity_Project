package com.example.baity.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.baity.Activities.Login.LogIn;
import com.example.baity.R;

public class Language extends AppCompatActivity {

    Button btnEnglish,btnArabic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);

        btnEnglish = findViewById(R.id.btnEnglish);
        btnArabic = findViewById(R.id.btnArabic);

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Language.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        btnArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Language.this,LogIn.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

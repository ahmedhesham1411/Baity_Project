package com.example.baity.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.baity.R;

import pl.droidsonroids.gif.GifImageView;

import static com.example.baity.Activities.LocaleManeger.ARABIC;
import static com.example.baity.Activities.LocaleManeger.ENGLISH;
import static com.example.baity.Activities.LocaleManeger.getLanguagePref;

public class SplashScreen extends BaseActivity {
    private GifImageView gifImageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getLanguagePref(this);

        gifImageView = findViewById(R.id.gifImageView);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //progressBar.setVisibility(progressBar.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(SplashScreen.this, LogIn.class);
                startActivity(mainIntent);
                finish();
            }
        }, 4500);



    }
}

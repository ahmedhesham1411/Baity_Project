package com.example.baity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.baity.Activities.Home.Home;
import com.example.baity.Activities.Login.LogIn;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import pl.droidsonroids.gif.GifImageView;

import static com.example.baity.Activities.LocaleManeger.getLanguagePref;

public class SplashScreen extends BaseActivity  {
    private GifImageView gifImageView;
    private String Token;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getLanguagePref(this);

        gifImageView = findViewById(R.id.gifImageView);
        Token = preferences.GetToken(this);
        if (Token == "default"){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashScreen.this, LogIn.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 4500);
        }

        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashScreen.this, Home.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 4500);
        }
    }
}

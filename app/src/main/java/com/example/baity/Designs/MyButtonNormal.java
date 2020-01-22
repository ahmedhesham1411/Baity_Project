package com.example.baity.Designs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class MyButtonNormal extends Button {
    public MyButtonNormal(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "jozoor.ttf");
        this.setTypeface(face,Typeface.NORMAL);
    }

    public MyButtonNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "jozoor.ttf");
        this.setTypeface(face,Typeface.NORMAL);
    }

    public MyButtonNormal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "jozoor.ttf");
        this.setTypeface(face,Typeface.NORMAL);
    }

    @SuppressLint("NewApi")
    public MyButtonNormal(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "jozoor.ttf");
        this.setTypeface(face,Typeface.NORMAL);
    }
}

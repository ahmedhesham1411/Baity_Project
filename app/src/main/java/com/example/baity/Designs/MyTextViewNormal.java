package com.example.baity.Designs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class MyTextViewNormal extends TextView {


    public MyTextViewNormal(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "jozoor.ttf");
        this.setTypeface(face,Typeface.NORMAL);
    }

    public MyTextViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "jozoor.ttf");
        this.setTypeface(face,Typeface.NORMAL);
    }

    public MyTextViewNormal(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face= Typeface.createFromAsset(context.getAssets(), "jozoor.ttf");
        this.setTypeface(face,Typeface.NORMAL);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }

}
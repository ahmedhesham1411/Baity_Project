package com.example.baity.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baity.Adapter.HomeAdapter;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.MyData;
import com.example.baity.R;
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;

import java.util.Calendar;
import java.util.Locale;


public class Home extends BaseActivity {
    RecyclerView recyclerView;
    MyTextViewBold islamicDate;
    String day,month,year;
    AppCompatImageView backBtn,settingBtn,closeAdBtn;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        showAdDialog();
        backBtn = findViewById(R.id.btnBack);
        settingBtn  = findViewById(R.id.settingBtn);
        islamicDate = findViewById(R.id.islamicDate);

        Calendar cal = new UmmalquraCalendar();
        day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)); // 11
        month = String.valueOf(cal.getDisplayName(Calendar.MONTH, Calendar.LONG,new Locale("ar")));
        year = String.valueOf(cal.get(Calendar.YEAR));
        islamicDate.setText(day + " " + month  + " " + year);

        MyData[] myData = new MyData[]{
                new MyData("استشارات هندسية",R.drawable.estsharat),
                new MyData("مراحل البناء",R.drawable.mrahl),
                new MyData("العزل",R.drawable.azl),
                new MyData(" الشبكة المركزية",R.drawable.shbka),
                new MyData("الديكور",R.drawable.dekor),
                new MyData("التأسيسات",R.drawable.tasesat),
                new MyData("خدمات",R.drawable.khdmat),
        };

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        HomeAdapter homeAdapter = new HomeAdapter(myData);
        recyclerView.setAdapter(homeAdapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Setting.class);
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

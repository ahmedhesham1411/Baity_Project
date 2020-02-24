package com.example.baity.Activities.Qibla;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.Compass;
import com.example.baity.Model.Prayer;
import com.example.baity.R;
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Calendar;
import java.util.Locale;


public class Qibla extends AppCompatActivity implements PrayerTimeInterface{


    private static final String TAG = "Qibla and Prayer Time";
    private AppCompatImageView menuBtn;

    //for qibla
    private AppCompatImageView qibla;
    private Compass compass;
    private float currentAzimuth;
    SharedPreferences prefs;
    GPSTracker gps;

    //for prayer time
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Boolean locationPermissionGranted = false;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private PrayerTimePresenter prayerTimePresenter;
    private Location mlocation;


    //for date
    private int mYear, mMonth, mDay;
    private TextView fajr, zuhr, asr, maghrb, isha;
    AppCompatImageView btnBack;
    MyTextViewBold islamicDate;
    String day,month,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qibla);

        getId();

        islamicDate = findViewById(R.id.islamicDate);
        Calendar cal = new UmmalquraCalendar();
        day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)); // 11
        month = String.valueOf(cal.getDisplayName(Calendar.MONTH, Calendar.LONG,new Locale("ar")));
        year = String.valueOf(cal.get(Calendar.YEAR));
        islamicDate.setText(day + " " + month  + " " + year);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        prefs = getSharedPreferences("", MODE_PRIVATE);
        gps = new GPSTracker(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setupCompass();
        menuBtn.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
/*
            Animatoo.animateSwipeLeft(Qibla.this);
*/
        });


        init();
        getDate();
        prayerTimePresenter = new PrayerTimePresenter(this, this);
    }

    void getId() {
        menuBtn = findViewById(R.id.menuBTN);
        qibla = findViewById(R.id.qibla_img);
        fajr = findViewById(R.id.fajr);
        zuhr = findViewById(R.id.zuhr);
        asr = findViewById(R.id.asr);
        maghrb = findViewById(R.id.maghrb);
        isha = findViewById(R.id.isha);
        btnBack = findViewById(R.id.btnBack);
    }


    private void setupCompass() {
        Boolean permission_granted = GetBoolean("permission_granted");
        if(permission_granted) {
            getBearing();
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }
        }

        compass = new Compass(this);
        Compass.CompassListener cl = azimuth -> adjustGambarDial(azimuth);
        compass.setListener(cl);
    }

    public void adjustGambarDial(float azimuth) {
        float kiblat_derajat = GetFloat("kiblat_derajat");
        Animation an = new RotateAnimation(-(currentAzimuth)+kiblat_derajat, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = (azimuth);
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        qibla.startAnimation(an);
    }
    @SuppressLint("MissingPermission")
    public void getBearing(){

        float kiblat_derajat = GetFloat("kiblat_derajat");
        if(kiblat_derajat > 0.0001){
        }else
        {
            fetch_GPS();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {

            /// for compass
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SaveBoolean("permission_granted", true);
                } else {
                    Toast.makeText(getApplicationContext(),"permission_required", Toast.LENGTH_LONG).show();
                    Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                    finish();
                }
                return;
            }

            //for prayer time
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if (grantResults.length>0){
                    for (int i = 0 ; i< grantResults.length ; i++){
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            // onGPS();
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    locationPermissionGranted = true;
                }
            }
        }
    }


    public  void SaveString(String Judul, String tex){
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(Judul, tex);
        edit.apply();
    }
    public String GetString(String Judul){
        String Stringxxx = prefs.getString(Judul, "");
        return Stringxxx;
    }

    public  void SaveBoolean(String Judul, Boolean bbb){
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(Judul, bbb);
        edit.apply();
    }
    public Boolean GetBoolean(String Judul){
        Boolean result = prefs.getBoolean(Judul, false);
        return result;
    }
    public  void Savelong(String Judul, Long bbb){
        SharedPreferences.Editor edit = prefs.edit();
        edit.putLong(Judul, bbb);
        edit.apply();
    }
    public Long Getlong(String Judul){
        Long xxxxxx = prefs.getLong(Judul, 0);
        return xxxxxx;
    }

    public void SaveFloat(String Judul, Float bbb){
        SharedPreferences.Editor edit = prefs.edit();
        edit.putFloat(Judul, bbb);
        edit.apply();
    }
    public Float GetFloat(String Judul){
        Float xxxxxx = prefs.getFloat(Judul, 0);
        return xxxxxx;
    }

    public void fetch_GPS(){

        double result = 0;
        gps = new GPSTracker(this);
        if(gps.canGetLocation()){
            Log.e("TAG", "GPS is on");
            double lat_saya = gps.getLatitude ();
            double lon_saya = gps.getLongitude ();
            if(lat_saya < 0.001 && lon_saya < 0.001) {
            }else{
                double longitude2 = 39.826206;
                double longitude1 = lon_saya;
                double latitude2 = Math.toRadians(21.422487);
                double latitude1 = Math.toRadians(lat_saya);
                double longDiff= Math.toRadians(longitude2-longitude1);
                double y= Math.sin(longDiff)*Math.cos(latitude2);
                double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);
                result = (Math.toDegrees(Math.atan2(y, x))+360)%360;
                float result2 = (float)result;
                SaveFloat("kiblat_derajat", result2);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        if (compass != null) {
            compass.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (compass != null) {
            compass.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (compass != null) {
            compass.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        if (compass != null) {
            compass.stop();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        super.onBackPressed();
        //Animatoo.animateSwipeLeft(Qibla.this);
    }


    //////////////////////////////////////////////
    ///////////////////////////////////////////////
    //for prayer time
    private void init() {
        getLocationPermission();

        if (locationPermissionGranted)
            getDeviceLocation();

    }

    /////// prayer time
    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting device current location");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (locationPermissionGranted) {
                Task location = fusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location");
                            Location currentLocation = (Location) task.getResult();
                            prayerTimePresenter.getTimeData(currentLocation.getLatitude(), currentLocation.getLongitude(), 9, mMonth, mYear);
                            prayerTimePresenter.toString();
                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(Qibla.this, "unable to get current location", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }

        } catch (SecurityException e) {
            Log.d(TAG, "getDeviceLocation: SecurityException" + e.getMessage());
        }

    }


    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permission = {FINE_LOCATION, COURSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
                // initMap();
            } else {
                ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void getDate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mMonth++;
        mDay = c.get(Calendar.DAY_OF_MONTH);

    }


    @Override
    public void sendData(Prayer prayer) {
        int day = mDay--;
        fajr.setText(prayer.getData().get(day).getTimings().getFajr());
        zuhr.setText(prayer.getData().get(day).getTimings().getDhuhr());
        asr.setText(prayer.getData().get(day).getTimings().getAsr());
        maghrb.setText(prayer.getData().get(day).getTimings().getMaghrib());
        isha.setText(prayer.getData().get(day).getTimings().getIsha());
        //Toast.makeText(this, day + " / " + mMonth + " / " + mYear, Toast.LENGTH_SHORT).show();
    }

}

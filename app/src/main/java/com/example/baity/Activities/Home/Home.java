package com.example.baity.Activities.Home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.baity.Activities.BaseActivity;
import com.example.baity.Activities.Reviews.Review_company;
import com.example.baity.Activities.Setting;
import com.example.baity.Adapter.HomeAdapter;
import com.example.baity.Adapter.Home_slider_adapter;
import com.example.baity.Adapter.Search_adapter;
import com.example.baity.Adapter.Sub_cat_slider;
import com.example.baity.Designs.MyEditTextNormal;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Home_model;
import com.example.baity.Model.Home_model_response;
import com.example.baity.Model.Home_slider_model;
import com.example.baity.Model.MyData;
import com.example.baity.Model.Search_image_model;
import com.example.baity.Model.Search_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import com.smarteist.autoimageslider.SliderView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class Home extends BaseActivity implements Home_interface {
    Home_presenter home_presenter;
    RecyclerView recyclerView,recyclerView1;
    AppCompatTextView movingText;
    MyTextViewBold islamicDate;
    String day,month,year;
    AppCompatImageView settingBtn,closeAdBtn;
    Preferences preferences;
    String token,adImage;
    AppCompatImageView image_ad;
    AutoCompleteTextView search;
    Button cancelSearch;
    int company_id;
    LinearLayoutCompat searchLayout,homeLayout,searchBar;
    MyEditTextNormal goToSearch;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        home_presenter = new Home_presenter(this,this);
        token = preferences.GetToken(this);
        home_presenter.GetMarqee(token);
        home_presenter.GetAd();

        showAdDialog();
        settingBtn  = findViewById(R.id.settingBtn);
        islamicDate = findViewById(R.id.islamicDate);
        movingText = findViewById(R.id.txtShortMessage);
        search  = findViewById(R.id.bahs);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView1 = findViewById(R.id.recycler_view1);
        searchLayout = findViewById(R.id.search_layout);
        cancelSearch = findViewById(R.id.cancelSearch);
        homeLayout = findViewById(R.id.home_layout);
        searchBar = findViewById(R.id.searchBar);
        goToSearch = findViewById(R.id.goToSearch);
        recyclerView.setVisibility(View.VISIBLE);

        home_presenter.GetSearchList();

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLayout.setVisibility(View.VISIBLE);
                homeLayout.setVisibility(View.GONE);
            }
        });

        goToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLayout.setVisibility(View.VISIBLE);
                homeLayout.setVisibility(View.GONE);
            }
        });

        cancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLayout.setVisibility(View.GONE);
                homeLayout.setVisibility(View.VISIBLE);

            }
        });
        Animation marquee = AnimationUtils.loadAnimation(this, R.anim.marquee);
        movingText.startAnimation(marquee);

        Calendar cal = new UmmalquraCalendar();
        day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)); // 11
        month = String.valueOf(cal.getDisplayName(Calendar.MONTH, Calendar.LONG,new Locale("ar")));
        year = String.valueOf(cal.get(Calendar.YEAR));
        islamicDate.setText(day + " " + month  + " " + year);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Setting.class);
                startActivity(intent);
            }
        });
    }

    private void showAdDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        Rect displayRectangle = new Rect();
        Window window = Home.this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.show_ad, viewGroup, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        closeAdBtn = dialogView.findViewById(R.id.closeAd);
        image_ad = dialogView.findViewById(R.id.imageAd);
        dialogView.setMinimumWidth((int)(displayRectangle.width() * 1f));
        dialogView.setMinimumHeight((int)(displayRectangle.height() * 0.8f));
        image_ad.setMinimumWidth((int)(displayRectangle.width() * 1f));
        image_ad.setMinimumHeight((int)(displayRectangle.height() * 0.8f));
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        builder.setView(dialogView);
        image_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.Save_company_id(v.getContext(),company_id);
                Intent intent = new Intent(v.getContext(), Review_company.class);
                v.getContext().startActivity(intent);
            }
        });

        closeAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                home_presenter.GetData(token);
                home_presenter.GetAdsSlider();
            }
        });
    }

    @Override
    public void sendData(List<Home_model> home_models) {
        List<Home_model> home_models1 = home_models;
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        HomeAdapter homeAdapter = new HomeAdapter(home_models1,this);
        homeAdapter.setHome_models(home_models1);
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void sendMarqee(List<String> list) {

        List<String> list1 = list;
        String listString = "";

        for (String s : list1)
        {
            listString += s + "\t";
        }
        movingText.setText(listString);
    }

    @Override
    public void sendAd(AdModel adModel) {
        AdModel adModel1 = adModel;
        adImage = adModel1.getImagepath();
        Bitmap decodeTxt=decodeBase64(adImage);
        image_ad.setImageBitmap(decodeTxt);
        company_id = adModel.getCompany_id();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void sendSearchList(List<Search_model> search_models) {
        List<Search_model> search_models1 = search_models;

        ArrayList<String> arrayList = new ArrayList<String>();
        List<String> dataList = new ArrayList<String>();
        List<Integer> idList = new ArrayList<Integer>();
        for (int i = 0; i < search_models1.size();i++){
           dataList.add(search_models1.get(i).getNameA());
        }
        String[] stringArray = arrayList.stream().toArray(String[]::new);
        Integer[] idArray = arrayList.stream().toArray(Integer[]::new);

        Search_adapter adapter = new Search_adapter(this, R.layout.custim_list_item, dataList,search_models1,idArray);
        search.setAdapter(adapter);

    }

    @Override
    public void sendAdsSlider(List<Home_slider_model> home_slider_models) {
        List<String> imagesList = new ArrayList<String>();
        List<Home_slider_model> home_slider_models1 = home_slider_models;

        for (int i = 0;i < home_slider_models1.size();i++){
            imagesList.add(home_slider_models1.get(i).getImagepath());
        }

        SliderView sliderView = findViewById(R.id.imageSliderHome);
        Home_slider_adapter adapterr = new Home_slider_adapter(this,imagesList);
        sliderView.setSliderAdapter(adapterr);


    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String[] GetStringArray(ArrayList<String> arr)
    {
        String str[] = new String[arr.size()];
        for (int j = 0; j < arr.size(); j++) {
            str[j] = arr.get(j);
        }
        return str;
    }
}


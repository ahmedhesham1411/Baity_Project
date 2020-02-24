package com.example.baity.Activities.Qibla;

import android.content.Context;
import android.util.Log;

import com.example.baity.Model.Prayer;
import com.example.baity.Network.NetworkUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class PrayerTimePresenter {
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    private PrayerTimeInterface prayerTimeInterface;

    public PrayerTimePresenter(Context context, PrayerTimeInterface prayerTimeInterface) {
        this.context = context;
        this.prayerTimeInterface = prayerTimeInterface;
    }

    void getTimeData(double lat, double lang, Integer method, Integer month, Integer year){
        mSubscriptions.add(NetworkUtil.getRetrofittNoHeader()
                .getTiming(lat,lang,method,month,year)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }


    private void handleResponse(Prayer prayer) {
            prayerTimeInterface.sendData(prayer);
            Log.d("sss", "handleResponse: "+prayer.toString());
    }


    private void handleError(Throwable throwable) {
        Log.d("aaa", "handleError: "+ throwable.getMessage());
    }

/*    private void handleResponse(com.shtito.hajjomrah.models.prayerTime.Prayer prayer) {
        prayerTimeInterface.sendData(prayer);
        Log.d("sss", "handleResponse: "+prayer.toString());
    }

    private void handleError(Throwable throwable) {
        if (dialogLoader.isAdded())
            dialogLoader.dismiss();
        Log.d("aaa", "handleError: "+ throwable.getMessage());
    }*/
}
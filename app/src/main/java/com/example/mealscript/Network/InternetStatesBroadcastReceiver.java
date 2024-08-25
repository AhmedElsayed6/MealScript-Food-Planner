package com.example.mealscript.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.example.mealscript.Home.Views.HomeActivityInterface;

public class InternetStatesBroadcastReceiver extends BroadcastReceiver {

    private final HomeActivityInterface view;

    public InternetStatesBroadcastReceiver(HomeActivityInterface view) {
        this.view = view;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
          view.ShowSnackbar("No internet connection");
        }
    }
}

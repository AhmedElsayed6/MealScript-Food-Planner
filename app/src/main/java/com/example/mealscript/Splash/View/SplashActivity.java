package com.example.mealscript.Splash.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Auth.Views.AuthActivity;
import com.example.mealscript.Home.Views.HomeActivity;
import com.example.mealscript.R;
import com.example.mealscript.Splash.Presenter.SplashPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {
    public static String TAG = "OBONE";
    public SplashPresenter presenter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter=  SplashPresenter.getInstance();
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (presenter.isUserLoggedIn()) {
                        navigateToHomeScreen();
                    } else
                        navigateToAuthScreen();
                });
    }

    private void navigateToAuthScreen() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToHomeScreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
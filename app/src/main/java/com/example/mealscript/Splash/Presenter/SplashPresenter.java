package com.example.mealscript.Splash.Presenter;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Auth.Presenters.AuthPresenter;

public class SplashPresenter implements AuthPresenter {
    private AuthManager authManager;
    private static SplashPresenter instance = null;

    private SplashPresenter() {
        authManager = new AuthManager();
    }
    public static SplashPresenter getInstance() {
        if (instance == null) {
            instance = new SplashPresenter();
        }
        return instance;
    }

    public boolean isUserLoggedIn(){
        return authManager.isUserLoggedIn();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}

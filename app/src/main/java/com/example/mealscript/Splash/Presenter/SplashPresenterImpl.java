package com.example.mealscript.Splash.Presenter;

import com.example.mealscript.Model.AuthManager;

public class SplashPresenterImpl implements SplashPresenter {
    private AuthManager authManager;
    private static SplashPresenter instance = null;

    private SplashPresenterImpl() {
        authManager = new AuthManager();
    }
    public static SplashPresenter getInstance() {
        if (instance == null) {
            instance = new SplashPresenterImpl();
        }
        return instance;
    }
    @Override
    public boolean isUserLoggedIn(){
        return authManager.isUserLoggedIn();
    }
}

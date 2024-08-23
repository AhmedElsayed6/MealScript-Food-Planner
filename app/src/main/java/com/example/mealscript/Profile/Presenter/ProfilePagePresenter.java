package com.example.mealscript.Profile.Presenter;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Splash.Presenter.SplashPresenter;

public class ProfilePagePresenter {
    private AuthManager authManager;
    private static ProfilePagePresenter instance = null;

    private ProfilePagePresenter() {
        authManager = new AuthManager();
    }

    public static ProfilePagePresenter getInstance() {
        if (instance == null) {
            instance = new ProfilePagePresenter();
        }
        return instance;
    }

    public void SignOut(){
        authManager.SignOut();
    }
}

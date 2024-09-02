package com.example.mealscript.Auth.Presenters;

import android.content.Intent;

import com.example.mealscript.Auth.Views.WelcomePageInterface;
import com.example.mealscript.Model.AuthManager;

public class WelcomePresenterImpl implements AuthPresenter, WelcomePresenter {

    private WelcomePageInterface view;
    private AuthManager authManager;


    public WelcomePresenterImpl(WelcomePageInterface view) {
        this.view = view;
    }

    @Override
    public void SignInUpWithGoogle(Intent data) {
        authManager = new AuthManager();
        authManager.SignInUpWithGoogle(data, this);
    }

    @Override
    public void setUpGuestMode() {
        authManager = new AuthManager();
        authManager.setGuestMode(true);
    }

    @Override
    public void onSuccess() {
        authManager.setUpUserId();
        view.onGoogleSuccess();
    }

    @Override
    public void onFail(String message) {
        view.onGoogleFail(message);
    }
}

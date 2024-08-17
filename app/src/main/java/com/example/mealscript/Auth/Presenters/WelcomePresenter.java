package com.example.mealscript.Auth.Presenters;

import android.content.Intent;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Auth.Views.WelcomePageInterface;
public class WelcomePresenter implements AuthPresenter {

    private WelcomePageInterface view;
    private AuthManager authManager;
    private static WelcomePresenter instance = null;


    private WelcomePresenter(WelcomePageInterface view) {
        this.view = view;

    }

    public static WelcomePresenter getInstance(WelcomePageInterface view) {
        if (instance == null)
            instance = new WelcomePresenter(view);
        return instance;
    }


    public void SignInUpWithGoogle(Intent data ) {
        authManager = AuthManager.getInstance(this);
        authManager.SignInUpWithGoogle(data);
    }



    @Override
    public void onSuccess() {
        view.onGoogleSuccess();
    }

    @Override
    public void onFail() {

    }
}

package com.example.mealscript.Auth.Presenters;

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


    public void SignInUpWithGoogle() {
        authManager = AuthManager.getInstance(this);
        authManager.SignInUpWithGoogle();
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}

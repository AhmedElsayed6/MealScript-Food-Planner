package com.example.mealscript.Auth.Presenters;

import android.content.Intent;

import com.example.mealscript.Auth.Views.WelcomePageInterface;
import com.example.mealscript.Model.AuthManager;
public class WelcomePresenterImpl implements AuthPresenter, WelcomePresenter {

    private static WelcomePageInterface view;
    private AuthManager authManager;
    private static WelcomePresenter instance = null;


    private WelcomePresenterImpl(WelcomePageInterface view) {
        this.view = view;
    }
    public static WelcomePresenter getInstance(WelcomePageInterface view) {
        if (instance == null)
            instance = new WelcomePresenterImpl(view);
        WelcomePresenterImpl.view = view;
        return instance;
    }
    @Override
    public void SignInUpWithGoogle(Intent data) {
        authManager = new  AuthManager();
        authManager.SignInUpWithGoogle(data,this);
    }
    @Override
    public  void setUpGuestMode(){
        authManager = new  AuthManager();
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

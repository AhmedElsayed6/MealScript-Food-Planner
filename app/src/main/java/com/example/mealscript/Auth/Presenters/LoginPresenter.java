package com.example.mealscript.Auth.Presenters;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Auth.Views.LoginPageInterface;

import java.util.regex.Pattern;

public class LoginPresenter implements AuthPresenter {

    private LoginPageInterface view;
    private AuthManager authManager;
    private static LoginPresenter instance = null;


    private LoginPresenter(LoginPageInterface view) {
        this.view = view;
    }

    public static LoginPresenter getInstance(LoginPageInterface view) {

        if (instance == null)
            instance = new LoginPresenter(view);
        return instance;

    }


    public void validateUser(String email, String password) {
        boolean isValid = true;

        if (email.isEmpty()) {
            view.showEmailTextError();
            isValid = false;
        } else if (!isValidEmail(email)) {
            view.showEmailTextError();
            isValid = false;
        }

        if (password.isEmpty() || password.length() < 6) {
            view.showPasswordTextError();
            isValid = false;
        }

        if (isValid) {
            authManager = AuthManager.getInstance(this);
            authManager.Login(email, password);
        }

    }


    private boolean isValidEmail(String email) {
        return Pattern.compile("^[a-zA-Z0-9._%+-]+@(gmail\\.com|outlook\\.com|yahoo\\.com)$").matcher(email).matches();
    }

    @Override
    public void onSuccess() {
        view.onLoginSuccess();
    }

    @Override
    public void onFail() {

    }
}

package com.example.mealscript.Auth.Presenters;

import com.example.mealscript.Auth.Views.LoginPageInterface;
import com.example.mealscript.Model.AuthManager;

import java.util.regex.Pattern;

public class LoginPresenterImpl implements AuthPresenter, LoginPresenter {

    private LoginPageInterface view;
    private AuthManager authManager;


    public LoginPresenterImpl(LoginPageInterface view) {
        this.view = view;
    }


    @Override
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
            authManager = new AuthManager();
            authManager.Login(email, password, this);
        }

    }


    private boolean isValidEmail(String email) {
        return Pattern.compile("^[a-zA-Z0-9._%+-]+@(gmail\\.com|outlook\\.com|yahoo\\.com)$").matcher(email).matches();
    }

    @Override
    public void onSuccess() {
        authManager.setUpUserId();
        view.onLoginSuccess();
    }

    @Override
    public void onFail(String message) {
        view.onLoginFail(message);
    }
}

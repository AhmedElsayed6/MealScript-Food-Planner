package com.example.mealscript.Auth.Presenters;


import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Auth.Views.SignupPageInterface;

import java.util.regex.Pattern;

public class SignupPresenter implements AuthPresenter {
    private AuthManager authManager;

    private SignupPageInterface view;
    private static SignupPresenter instance = null;

    private SignupPresenter(SignupPageInterface view) {
        this.view = view;
    }

    public static SignupPresenter getInstance(SignupPageInterface view) {

        if (instance == null)
            instance = new SignupPresenter(view);
        return instance;

    }


    public void validateUser(String name, String email, String password, String confirmPassword) {
        boolean isValid = true;

        if (email.isEmpty()) {
            view.showEmailTextError();
            isValid = false;
        } else if (!isValidEmail(email)) {
            view.showEmailTextError();
            isValid = false;
        }

        if (name.isEmpty()) {
            view.showNameTextError();
            isValid = false;
        } else if (!isValidName(name)) {
            view.showNameTextError();
            isValid = false;
        }
        if (password.isEmpty() || password.length() < 6) {
            view.showPasswordTextError();
            isValid = false;
        }
        if (!password.equals(confirmPassword)) {
            view.showConfirmPasswordTextError();
            isValid = false;
        }

        if (isValid) {
            authManager = AuthManager.getInstance(this);
            authManager.Signup(email, password);
        }

    }


    private boolean isValidEmail(String email) {
        return Pattern.compile("^[a-zA-Z0-9._%+-]+@(gmail\\.com|outlook\\.com|yahoo\\.com)$").matcher(email).matches();
    }

    private boolean isValidName(String name) {
        // Regex to allow letters and whitespace only
        return Pattern.compile("^[a-zA-Z\\s]+$").matcher(name).matches();
    }


    @Override
    public void onSuccess() {
        view.onSignupSuccess();
    }

    @Override
    public void onFail() {

    }
}

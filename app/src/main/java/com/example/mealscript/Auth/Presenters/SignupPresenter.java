package com.example.mealscript.Auth.Presenters;

public interface SignupPresenter {
    void validateUser(String name, String email, String password, String confirmPassword);
}

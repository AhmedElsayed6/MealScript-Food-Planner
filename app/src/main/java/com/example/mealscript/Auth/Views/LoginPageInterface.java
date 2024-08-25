package com.example.mealscript.Auth.Views;

public interface LoginPageInterface {
    public void showEmailTextError();
    public void showPasswordTextError();
    public void onLoginSuccess();
    public void onLoginFail(String message);

}

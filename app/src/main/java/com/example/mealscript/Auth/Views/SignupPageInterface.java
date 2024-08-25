package com.example.mealscript.Auth.Views;

public interface SignupPageInterface {

    public void showEmailTextError();
    public void showNameTextError();
    public void showPasswordTextError();
    public void showConfirmPasswordTextError();
    public void onSignupSuccess();
    public void onSignupFail(String message);


}

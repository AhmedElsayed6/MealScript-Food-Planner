package com.example.mealscript.Auth.Presenters;

import android.content.Intent;

public interface WelcomePresenter {
    void SignInUpWithGoogle(Intent data);

    void setUpGuestMode();
}

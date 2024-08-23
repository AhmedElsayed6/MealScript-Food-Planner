package com.example.mealscript.Profile.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealscript.Profile.Presenter.ProfilePagePresenter;
import com.example.mealscript.R;
import com.example.mealscript.Splash.View.SplashActivity;

public class ProfileActivity extends AppCompatActivity {
    Button btnSignOut ;
    ProfilePagePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnSignOut = findViewById(R.id.btnSignOut);
        presenter = presenter.getInstance();
        btnSignOut.setOnClickListener((e)->{
            presenter.SignOut();
            Intent goToSplash = new Intent(this, SplashActivity.class);
            startActivity(goToSplash);
        });




    }
}
package com.example.mealscript.Profile.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealscript.Auth.Presenters.CustomDialog;
import com.example.mealscript.Home.Views.HomeActivity;
import com.example.mealscript.Profile.Presenter.ProfilePagePresenter;
import com.example.mealscript.R;
import com.example.mealscript.Splash.View.SplashActivity;

public class ProfileActivity extends AppCompatActivity implements ProfilePageInterface {
    Button btnSignOut ,btnSync , btnBackUp;
    ProfilePagePresenter presenter;
    CustomDialog customDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnSignOut = findViewById(R.id.btnSignOut);
        btnSync = findViewById(R.id.btnSync);
        btnBackUp = findViewById(R.id.btnBackUp);
        customDialog = new CustomDialog(this);

        presenter = presenter.getInstance(this,this);
        btnSignOut.setOnClickListener((e)->{
            presenter.SignOut();
            Intent goToSplash = new Intent(this, SplashActivity.class);
            goToSplash.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(goToSplash);
            finish();
        });
        btnBackUp.setOnClickListener((e)->{
            customDialog.setTitle("Backup Favorite & Planner meals");
            customDialog.setMessage("Backing up your meals will override what's on the cloud, Do you wish to continue?");
            customDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    presenter.BackUpData();
                    dialog.dismiss();
                }
            });
            customDialog.showDialog();
        });


        btnSync.setOnClickListener((e)->{
            customDialog.setTitle("Synchronize Favorite & Planner meals");
            customDialog.setMessage("Syncing up your meals will override what's on the local database on this device, Do you wish to continue?");
            customDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.SyncLocalDataBase();
                    dialog.dismiss();
                }
            });
            customDialog.showDialog();
        });


    }
}
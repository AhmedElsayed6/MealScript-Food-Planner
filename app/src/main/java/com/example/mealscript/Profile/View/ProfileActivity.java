package com.example.mealscript.Profile.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mealscript.Auth.Presenters.CustomDialog;
import com.example.mealscript.Home.Views.HomeActivity;
import com.example.mealscript.Network.InternetStatesBroadcastReceiver;
import com.example.mealscript.Network.NetworkObserver;
import com.example.mealscript.Profile.Presenter.ProfilePagePresenter;
import com.example.mealscript.R;
import com.example.mealscript.Repository.Repository;
import com.example.mealscript.Splash.View.SplashActivity;
import com.google.android.material.snackbar.Snackbar;

public class ProfileActivity extends AppCompatActivity implements ProfilePageInterface , NetworkObserver {
    Button btnSignOut ,btnSync , btnBackUp;
    ProfilePagePresenter presenter;
    CustomDialog customDialog;
    Toolbar toolBarProfilePage;
    InternetStatesBroadcastReceiver internetReceiver;
    Snackbar snackbar;
    TextView textViewName,textViewEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnSignOut = findViewById(R.id.btnSignOut);
        btnSync = findViewById(R.id.btnSync);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewName = findViewById(R.id.textViewName);
        toolBarProfilePage = findViewById(R.id.toolBarProfilePage);
        btnBackUp = findViewById(R.id.btnBackUp);
        internetReceiver = new InternetStatesBroadcastReceiver(this);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internetReceiver, filter);

        customDialog = new CustomDialog(this);
        toolBarProfilePage.setTitle("Profile");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolBarProfilePage.setNavigationOnClickListener(v -> finish());
        presenter = presenter.getInstance(this, Repository.getInstance(this));
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

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setUserDetails();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserDetails(String userName, String userEmail) {
        textViewEmail.setText(userEmail);
        textViewName.setText(userName);
    }

    @Override
    public void showNetowrkErrorSnackBar(String message) {
        snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE).setAction("Retry", view -> {
            snackbar.dismiss();
            this.recreate();
        });
        snackbar.show();
    }
}
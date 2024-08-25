package com.example.mealscript.Home.Views;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mealscript.Favorite.View.FavoriteActivity;
import com.example.mealscript.Network.InternetStatesBroadcastReceiver;
import com.example.mealscript.Profile.View.ProfileActivity;
import com.example.mealscript.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;
import android.content.IntentFilter;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity implements  HomeActivityInterface{
    View btnGoToFavHomeScreen ;
    Toolbar toolbarHome;
    CircleImageView btnProfileImageInHomeScreen;
    private InternetStatesBroadcastReceiver internetReceiver;
    Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnGoToFavHomeScreen = findViewById(R.id.btnGoToFavHomeScreen);
        btnProfileImageInHomeScreen = findViewById(R.id.btnProfileImageInHomeScreen);
        toolbarHome = findViewById(R.id.toolbarHome);

        internetReceiver = new InternetStatesBroadcastReceiver(this);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internetReceiver, filter);

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView2);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        btnGoToFavHomeScreen.setOnClickListener(e->{
            Intent goToFavorites = new Intent(this, FavoriteActivity.class);
            startActivity(goToFavorites);
        });

        btnProfileImageInHomeScreen.setOnClickListener((e)->{
            Intent goToProfile = new Intent(this, ProfileActivity.class);
            startActivity(goToProfile);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(internetReceiver);
    }

    @Override
    public void ShowSnackbar(String message) {
        snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE).setAction("Retry", view -> {
            snackbar.dismiss();
            this.recreate();
        });
        snackbar.show();
    }
}
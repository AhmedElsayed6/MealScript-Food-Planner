package com.example.mealscript.Home.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mealscript.Favorite.View.FavoriteActivity;
import com.example.mealscript.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    View btnGoToFavHomeScreen ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnGoToFavHomeScreen = findViewById(R.id.btnGoToFavHomeScreen);
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView2);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        btnGoToFavHomeScreen.setOnClickListener(e->{
            Intent goToFavorites = new Intent(this, FavoriteActivity.class);
            startActivity(goToFavorites);
        });
    }
}
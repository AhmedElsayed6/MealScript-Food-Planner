package com.example.mealscript.Favorite.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealscript.Favorite.Presenter.FavoritePresenter;
import com.example.mealscript.Favorite.Presenter.FavoritePresenterImpl;
import com.example.mealscript.MealDetails.Views.MealDetailsActivity;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity implements  FavoriteActivityInterface {
    FavoritePresenter presenter;
    RecyclerView favoriteRecyclerView;
    FavoritesRecyclerViewAdapter favoriteRecyclerViewAdapter;
    Toolbar toolBarFavMeals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        favoriteRecyclerView = findViewById(R.id.favRecyclerView);
        toolBarFavMeals = findViewById(R.id.toolBarFavMeals);
        setSupportActionBar(toolBarFavMeals);
        toolBarFavMeals.setTitle("Favorites");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolBarFavMeals.setNavigationOnClickListener(v -> finish());
        presenter = new FavoritePresenterImpl(this,this);
        presenter.getFavoriteMeals();
        favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoriteRecyclerViewAdapter =new FavoritesRecyclerViewAdapter(this, new ArrayList<>());
        favoriteRecyclerView.setAdapter(favoriteRecyclerViewAdapter);
    }


    @Override
    public void showFavoriteMeals(List<FavoriteMeal> favoriteMeals) {
        favoriteRecyclerViewAdapter.setData(favoriteMeals);
    }

    @Override
    public void deleteMeal(FavoriteMeal meal) {
        presenter.deleteMeal(meal);
    }

    @Override
    public void requestToGoToDetailsPage(String mealID) {
        presenter.goToDetailsPage(mealID);

    }

    @Override
    public void goToDetailsPage(Meal meal) {
       Intent goToMealDetails = new Intent(this, MealDetailsActivity.class);
       meal.setFavorite(true);
        goToMealDetails.putExtra("meal",meal);
        startActivity(goToMealDetails);
    }

}
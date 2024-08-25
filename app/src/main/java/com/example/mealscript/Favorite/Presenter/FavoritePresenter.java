package com.example.mealscript.Favorite.Presenter;

import com.example.mealscript.Model.FavoriteMeal;

public interface FavoritePresenter {
    void getFavoriteMeals();

    void deleteMeal(FavoriteMeal meal);

    void goToDetailsPage(String mealID);
}

package com.example.mealscript.Favorite.View;

import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;

import java.util.List;

public interface FavoriteActivityInterface {
    public void showFavoriteMeals(List<FavoriteMeal> favoriteMeals);
    public void deleteMeal(FavoriteMeal meal);
    public void requestToGoToDetailsPage(String mealID);
    public void goToDetailsPage(Meal meal);
}

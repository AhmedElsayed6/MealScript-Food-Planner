package com.example.mealscript.Profile.Presenter;

import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;

import java.util.List;

public interface CallbackForProfile {
    public void updateFavoriteLocalDataBase(List<FavoriteMeal> favoriteMealList);
    public void updatePlannerLocalDataBase(List<PlannerMeal> plannerMealList);
    public void displayErrorMessage(String errorMessage);
    public void getUserDetails(String name, String email);
}

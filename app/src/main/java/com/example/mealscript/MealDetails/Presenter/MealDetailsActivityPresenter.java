package com.example.mealscript.MealDetails.Presenter;

import com.example.mealscript.Model.Meal;

public interface MealDetailsActivityPresenter {
    void onMealDataReceived(Meal meal);

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);
}

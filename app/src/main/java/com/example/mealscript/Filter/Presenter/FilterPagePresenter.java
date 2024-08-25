package com.example.mealscript.Filter.Presenter;

import com.example.mealscript.Model.Meal;

public interface FilterPagePresenter {
    void getMeals(String location, String type);

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);
}

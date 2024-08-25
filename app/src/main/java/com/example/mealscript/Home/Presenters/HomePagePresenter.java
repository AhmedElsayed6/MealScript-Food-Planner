package com.example.mealscript.Home.Presenters;

import com.example.mealscript.Model.Meal;

public interface HomePagePresenter {
    void getData();

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);
}

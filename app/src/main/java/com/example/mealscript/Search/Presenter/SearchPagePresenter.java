package com.example.mealscript.Search.Presenter;

import com.example.mealscript.Model.Meal;

public interface SearchPagePresenter {
    void search(String query, String category);

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);
}

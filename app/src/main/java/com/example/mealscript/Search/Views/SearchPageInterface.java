package com.example.mealscript.Search.Views;

import com.example.mealscript.Model.Meal;

import java.util.List;

public interface SearchPageInterface {

    public void viewData(List<Meal> meals);
    public void showErrorSnackBar(String message);
    public void addToFavorite(Meal meal);
    public void removeFromFavorite(Meal meal);
}

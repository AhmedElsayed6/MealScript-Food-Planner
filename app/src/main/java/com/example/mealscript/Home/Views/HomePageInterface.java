package com.example.mealscript.Home.Views;

import com.example.mealscript.Model.ContainerMealLists;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;

import java.util.List;

public interface HomePageInterface {
    public void setData(ContainerMealLists container );
    public void navigateToFilterActivity(String location, String type);
    public void navigateToDetailsActivity(Meal meal);
    public void addToFavorite(Meal meal);
    public void removeFromFavorite(Meal meal);
    public void showGuestModeMessage(String message);
    public void showDataError(String message);

}
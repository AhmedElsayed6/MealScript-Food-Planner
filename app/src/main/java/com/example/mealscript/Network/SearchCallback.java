package com.example.mealscript.Network;

import com.example.mealscript.Model.Meal;

import java.util.List;

public interface SearchCallback {
    public void returnMealsOnSuccess(List<Meal> meals );
    public void returnMealsOnFailure(List<Meal> meals );
}

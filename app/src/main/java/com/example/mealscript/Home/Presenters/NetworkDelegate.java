package com.example.mealscript.Home.Presenters;

import com.example.mealscript.Model.Meal;

import java.util.List;

public interface NetworkDelegate {
    public void getMeals(List<Meal> meals );
}

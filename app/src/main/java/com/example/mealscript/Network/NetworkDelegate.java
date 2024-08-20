package com.example.mealscript.Network;

import com.example.mealscript.Model.Category;
import com.example.mealscript.Model.Meal;

import java.util.List;

public interface NetworkDelegate {
    public void returnMeals(List<Meal> meals );
    public void returnCategories(List<Category> categories );
    public void returnAreas(List<Meal> areas);
}

package com.example.mealscript.Planner.View;

import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;

import java.util.List;

public interface CalenderPageInterface {
    public void showPlannerMeals(List<PlannerMeal> plannerMealList);
    public void deleteMeal(PlannerMeal meal);
}

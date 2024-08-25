package com.example.mealscript.Planner.Presenter;

import com.example.mealscript.Model.PlannerMeal;

public interface CalenderPagePresenter {
    void getPlannerMeals();

    void deleteMeal(PlannerMeal meal);

    void goToDetailsPage(String mealID);
}

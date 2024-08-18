package com.example.mealscript.MealDetails.Presenter;

import com.example.mealscript.MealDetails.Views.MealDetailsActivityInterface;
import com.example.mealscript.Model.Meal;

public class MealDetailsActivityPresenter {
    MealDetailsActivityInterface view;

    public MealDetailsActivityPresenter(MealDetailsActivityInterface view) {
        this.view = view;
    }

    public void onMealDataReceived(Meal meal) {
        if (meal != null)
            view.showMealDetails(meal);
    }
}
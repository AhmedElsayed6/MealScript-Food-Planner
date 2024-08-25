package com.example.mealscript.Planner.Presenter;

import android.content.Context;

import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Planner.View.CalenderPageInterface;
import com.example.mealscript.Repo.Repo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalenderPagePresenterImpl implements CalenderPagePresenter {

    private CalenderPageInterface view;
    private Repo repo;

    public CalenderPagePresenterImpl(CalenderPageInterface view, Context context) {
        this.view = view;
        this.repo = Repo.getInstance(context);
    }

    @Override
    public void getPlannerMeals() {
        repo.getPlannerMealsList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    view.showPlannerMeals(data);
                });
    }

    @Override
    public void deleteMeal(PlannerMeal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            repo.deleteByUserIdAndIdMealFromPlanner(authManager.getCurrentUserId(), meal.getIdMeal(), meal.getDayOfTheWeek()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }

    }

    @Override
    public void goToDetailsPage(String mealID) {
        repo.getMealById(mealID).subscribe(
                meal -> {
                    view.goToDetailsPage(meal.getMealsList().get(0));
                }
        );

    }


}
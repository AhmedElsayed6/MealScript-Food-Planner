package com.example.mealscript.Planner.Presenter;

import android.content.Context;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Favorite.View.FavoriteActivityInterface;
import com.example.mealscript.Model.DayOfTheWeekEnum;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Planner.View.CalenderPageInterface;
import com.example.mealscript.Repo.Repo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalenderPagePresenter {

    private CalenderPageInterface view;
    private Repo repo;

    public CalenderPagePresenter(CalenderPageInterface view , Context context) {
        this.view = view;
        this.repo = Repo.getInstance(context);
    }

    public void getPlannerMeals(){
        repo.getPlannerMealsList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                   view.showPlannerMeals(data);
                });
    }

    public void deleteMeal(PlannerMeal meal){
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            repo.deleteByUserIdAndIdMealFromPlanner(authManager.getCurrentUserId(),meal.getIdMeal(),meal.getDayOfTheWeek() ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                            },
                            throwable -> {
                                //view.showError(throwable.getMessage());
                            }
                    );
        } else {

        }

    }

    public void goToDetailsPage(String mealID){
        repo.getMealById(mealID).subscribe(
                meal -> {
                   view.goToDetailsPage(meal.getMealsList().get(0));
                },
                throwable -> {

                }
        );

    }


}
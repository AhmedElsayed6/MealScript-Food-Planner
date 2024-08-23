package com.example.mealscript.Planner.Presenter;

import android.content.Context;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Favorite.View.FavoriteActivityInterface;
import com.example.mealscript.Model.DayOfTheWeekEnum;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Repo.Repo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PlannerDialogPresenter {
    private FavoriteActivityInterface view;
    private Repo repo;

    public PlannerDialogPresenter(Context context) {
        this.view = view;
        this.repo = Repo.getInstance(context);
    }

    public void insertMeal(  String mealId, String mealName, String mealImage , String dayOfTheWeek){
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {

            PlannerMeal plannerMeal = new PlannerMeal(authManager.getCurrentUserId(), mealName , mealId ,mealImage , DayOfTheWeekEnum.getDayNumberByName(dayOfTheWeek));
            repo.insertPlannerMeal(plannerMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                            },
                            throwable -> {
                                //view.showError(throwable.getMessage());
                            }
                    );
        } else {
          //  view.showGuestModeMessage("You can't add or remove favorite items guest mode!");
        }

    }






}

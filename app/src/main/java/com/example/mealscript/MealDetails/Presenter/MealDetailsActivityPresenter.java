package com.example.mealscript.MealDetails.Presenter;

import android.content.Context;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.MealDetails.Views.MealDetailsActivityInterface;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Repo.Repo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MealDetailsActivityPresenter {
    private MealDetailsActivityInterface view;
    private Repo repo;

    public MealDetailsActivityPresenter(MealDetailsActivityInterface view, Context context) {
        this.view = view;
        this.repo = Repo.getInstance(context);
    }

    public void onMealDataReceived(Meal meal) {
        if (meal != null)
            view.showMealDetails(meal);
    }


    public void insertMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            FavoriteMeal favMeal = new FavoriteMeal(authManager.getCurrentUserId(), meal.getStrMeal(), meal.getIdMeal(), meal.getStrMealThumb());
            repo.insertFavoriteMeal(favMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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

    public void deleteMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            repo.deleteByUserIdAndIdMeal(authManager.getCurrentUserId(), meal.getIdMeal()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                            },
                            throwable -> {
                                //view.showError(throwable.getMessage());
                            }
                    );
        } else {
            // view.showGuestModeMessage("You can't add or remove favorite items guest mode!");
        }

    }


}

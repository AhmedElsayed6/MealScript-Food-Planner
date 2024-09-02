package com.example.mealscript.MealDetails.Presenter;

import android.content.Context;

import com.example.mealscript.MealDetails.Views.MealDetailsActivityInterface;
import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Repository.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MealDetailsActivityPresenterImpl implements MealDetailsActivityPresenter {
    private MealDetailsActivityInterface view;
    private Repository repository;

    public MealDetailsActivityPresenterImpl(MealDetailsActivityInterface view, Repository repo) {
        this.view = view;
        this.repository = repo;
    }

    @Override
    public void onMealDataReceived(Meal meal) {
        if (meal != null)
            view.showMealDetails(meal);
    }


    @Override
    public void insertMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            FavoriteMeal favMeal = new FavoriteMeal(authManager.getCurrentUserId(), meal.getStrMeal(), meal.getIdMeal(), meal.getStrMealThumb());
            repository.insertFavoriteMeal(favMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } else {
            view.showError("You can't add or remove favorite items in guest mode!");
        }

    }

    @Override
    public void deleteMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            repository.deleteByUserIdAndIdMeal(authManager.getCurrentUserId(), meal.getIdMeal()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } else {
            view.showError("You can't add or remove favorite items in guest mode!");
        }
    }


}

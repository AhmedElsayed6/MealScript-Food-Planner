package com.example.mealscript.Home.Presenters;

import android.content.Context;

import com.example.mealscript.Home.Views.HomePageInterface;
import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Repository.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePagePresenterImpl implements HomePagePresenter {
    private static HomePagePresenter instance = null;
    private  HomePageInterface view;
    private  Repository repository;

    private HomePagePresenterImpl(HomePageInterface view , Repository repo) {
        this.view = view;
        repository = repo;
    }


    public static HomePagePresenter getInstance(HomePageInterface view,  Repository repo) {
        if (instance == null)
            instance = new HomePagePresenterImpl(view, repo);
        return instance;
    }

    @Override
    public void getData() {
        repository.getHomePageData().subscribe(
                updatedItems -> {
                    view.setData(updatedItems);
                },
                error -> {
                    view.showDataError("Couldn't load data please check the internet connection");
                }
        );
    }

    @Override
    public void insertMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            FavoriteMeal favMeal = new FavoriteMeal(authManager.getCurrentUserId(), meal.getStrMeal(), meal.getIdMeal(), meal.getStrMealThumb());
            repository.insertFavoriteMeal(favMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                            },
                            error -> {
                                view.showDataError("Couldn't load data please check the internet connection");
                            });
        } else {
            view.showGuestModeMessage("You can't add or remove favorite items guest mode!");
        }

    }

    @Override
    public void deleteMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            repository.deleteByUserIdAndIdMeal(authManager.getCurrentUserId(), meal.getIdMeal()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } else {
            view.showGuestModeMessage("You can't add or remove favorite items guest mode!");
        }

    }


}

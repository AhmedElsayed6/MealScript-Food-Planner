package com.example.mealscript.Home.Presenters;

import android.content.Context;
import android.util.Log;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Home.Views.HomePageInterface;
import com.example.mealscript.Model.Categories;
import com.example.mealscript.Model.Category;
import com.example.mealscript.Model.ContainerMealLists;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Model.Meals;
import com.example.mealscript.Repo.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePagePresenter {
    private static HomePagePresenter instance = null;
    private static HomePageInterface view;
    private static Repo repo;

    private HomePagePresenter(HomePageInterface view, Context context) {
        this.view = view;
        HomePagePresenter.repo = Repo.getInstance(context);
    }


    public static HomePagePresenter getInstance(HomePageInterface view, Context context) {
        if (instance == null)
            instance = new HomePagePresenter(view, context);

        HomePagePresenter.repo = Repo.getInstance(context);
        HomePagePresenter.view = view;
        return instance;
    }

    public void getData() {
             repo.getHomePageData().subscribe(
                        updatedItems -> {view.setData(updatedItems);
                            Log.i("TAGHOMEPAGE", "onViewCreated: GOT to get Data" + updatedItems);},
                        error -> {
                            Log.i("TAGHOMEPAGE", "GOT ERROR: Requested to get Data");
                        }
                );
    }
        public void insertMeal(Meal meal){
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
                view.showGuestModeMessage("You can't add or remove favorite items guest mode!");
            }

        }
        public void deleteMeal(Meal meal){
            AuthManager authManager = new AuthManager();
            if (!authManager.isGuestMode()) {
                repo.deleteByUserIdAndIdMeal(authManager.getCurrentUserId(),meal.getIdMeal()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                },
                                throwable -> {
                                    //view.showError(throwable.getMessage());
                                }
                        );
            } else {
                view.showGuestModeMessage("You can't add or remove favorite items guest mode!");
            }

        }


    }

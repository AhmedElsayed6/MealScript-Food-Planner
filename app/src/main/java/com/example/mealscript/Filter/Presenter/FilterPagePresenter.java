package com.example.mealscript.Filter.Presenter;

import android.content.Context;

import com.example.mealscript.Filter.View.FilterPageInterface;
import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.ContainerMealLists;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Repo.Repo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilterPagePresenter {
    private FilterPageInterface view;
    private Repo repo;
    private ContainerMealLists items;

    public FilterPagePresenter(FilterPageInterface view, Context context) {
        this.view = view;
        this.repo = Repo.getInstance(context);
        this.items = new ContainerMealLists();
    }

    public void getMeals(String location, String type) {
        switch (type) {
            case "Country":
                repo.searchByArea(location)
                        .subscribe(
                                areaList -> {
                                    if (!areaList.isEmpty()) {
                                        view.viewData(areaList);
                                    } else {
                                        view.showErrorSnackBar("No meal by that name");
                                    }
                                }, error -> {
                                    view.showErrorSnackBar("No meal by that name");
                                }
                        );
                break;
            case "Category":
                repo.searchByCategory(location)
                        .subscribe(
                                returnedMeals -> {
                                    if (!returnedMeals.isEmpty()) {
                                        view.viewData(returnedMeals);
                                    } else {
                                        view.showErrorSnackBar("No meal by that name");
                                    }
                                }, error -> {
                                    view.showErrorSnackBar("No meal by that name");
                                }
                        );
                break;
        }
    }

    public void insertMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            FavoriteMeal favMeal = new FavoriteMeal(authManager.getCurrentUserId(), meal.getStrMeal(), meal.getIdMeal(), meal.getStrMealThumb());
            repo.insertFavoriteMeal(favMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } else {
            view.showErrorSnackBar("You can't add or remove favorite items guest mode!");
        }

    }

    public void deleteMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            repo.deleteByUserIdAndIdMeal(authManager.getCurrentUserId(), meal.getIdMeal()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } else {
            view.showErrorSnackBar("You can't add or remove favorite items guest mode!");
        }

    }

}
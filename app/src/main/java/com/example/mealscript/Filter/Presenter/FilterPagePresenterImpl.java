package com.example.mealscript.Filter.Presenter;

import com.example.mealscript.Filter.View.FilterPageInterface;
import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Repository.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilterPagePresenterImpl implements FilterPagePresenter {
    private FilterPageInterface view;
    private Repository repository;

    public FilterPagePresenterImpl(FilterPageInterface view, Repository repo) {
        this.view = view;
        this.repository = repo;

    }

    @Override
    public void getMeals(String location, String type) {
        switch (type) {
            case "Country":
                repository.searchByArea(location)
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
                repository.searchByCategory(location)
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

    @Override
    public void insertMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            FavoriteMeal favMeal = new FavoriteMeal(authManager.getCurrentUserId(), meal.getStrMeal(), meal.getIdMeal(), meal.getStrMealThumb());
            repository.insertFavoriteMeal(favMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } else {
            view.showErrorSnackBar("You can't add or remove favorite items guest mode!");
        }

    }

    @Override
    public void deleteMeal(Meal meal) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            repository.deleteByUserIdAndIdMeal(authManager.getCurrentUserId(), meal.getIdMeal()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } else {
            view.showErrorSnackBar("You can't add or remove favorite items guest mode!");
        }

    }

}
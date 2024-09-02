package com.example.mealscript.Search.Presenter;

import android.util.Log;

import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Repository.Repository;
import com.example.mealscript.Search.Views.SearchPageInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPagePresenterImpl implements SearchPagePresenter {

    private SearchPageInterface view;
    private Repository repository;

    public SearchPagePresenterImpl(SearchPageInterface view, Repository repo) {
        this.view = view;
        this.repository = repo;
    }


    @Override
    public void search(String query, String category) {
        switch (category) {
            case "Meal":
                repository.searchByName(query).subscribe(mealList -> {
                            if(mealList.getMealsList() == null ){
                                view.showErrorSnackBar("No Meal by that name");
                            }
                            else if (!mealList.getMealsList().isEmpty()) {
                                view.viewData(mealList.getMealsList());
                            }
                        }, error -> {
                            view.showErrorSnackBar("Network Error");
                        }
                );
                break;
            case "Country":
                repository.searchByArea(query).subscribe(areaList -> {
                    if (!areaList.isEmpty()) {
                        view.viewData(areaList);
                    } else {
                        view.showErrorSnackBar("No Country by that name");
                    }
                }, error -> {
                    view.showErrorSnackBar("Network Error");
                });
                break;
            case "Ingredient":
                repository.searchByIngredients(query)
                        .subscribe(
                                ingredientList -> {
                                    if (!ingredientList.isEmpty()) {
                                        view.viewData(ingredientList);
                                    } else {
                                        view.showErrorSnackBar("No Ingredient by that name");
                                    }
                                }, error -> {
                                    view.showErrorSnackBar("Network Error");
                                }
                        );
                break;
            case "Category":
                repository.searchByCategory(query).subscribe(
                        returnedMeals -> {
                            if (!returnedMeals.isEmpty()) {
                                view.viewData(returnedMeals);
                            } else {
                                view.showErrorSnackBar("No Category by that name");
                            }
                        }, error -> {
                            view.showErrorSnackBar("Network Error");
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
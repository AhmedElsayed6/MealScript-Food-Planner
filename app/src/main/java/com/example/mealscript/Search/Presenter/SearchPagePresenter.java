package com.example.mealscript.Search.Presenter;

import android.content.Context;

import com.example.mealscript.Auth.Model.AuthManager;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Repo.Repo;
import com.example.mealscript.Search.Views.SearchPageInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPagePresenter {

    private SearchPageInterface view;
    private Repo repo;

    public SearchPagePresenter(SearchPageInterface view, Context context) {
        this.view = view;
        this.repo = Repo.getInstance(context);
    }


    public void search(String query, String category) {
        switch (category) {
            case "Meal":
                repo.searchByName(query).subscribe(mealList -> {
                            if (!mealList.getMealsList().isEmpty()) {
                                view.viewData(mealList.getMealsList());
                            } else {
                                view.showErrorSnackBar("No meal by that name");
                            }
                        }, error -> {
                            view.showErrorSnackBar("No meal by that name");
                        }
                );
                break;
            case "Country":
                repo.searchByArea(query).subscribe(areaList -> {
                    if (!areaList.isEmpty()) {
                        view.viewData(areaList);
                    } else {
                        view.showErrorSnackBar("No meal by that name");
                    }
                }, error -> {
                    view.showErrorSnackBar("No meal by that name");
                });
                break;
            case "Ingredient":
                repo.searchByIngredients(query)
                        .subscribe(
                                ingredientList -> {
                                    if (!ingredientList.isEmpty()) {
                                        view.viewData(ingredientList);
                                    } else {
                                        view.showErrorSnackBar("No meal by that name");
                                    }
                                }, error -> {
                                    view.showErrorSnackBar("No meal by that name");
                                }
                        );
                break;
            case "Category":
                repo.searchByCategory(query).subscribe(
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
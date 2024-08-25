package com.example.mealscript.Favorite.Presenter;

import android.content.Context;

import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Favorite.View.FavoriteActivityInterface;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Repo.Repo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenter {
    private FavoriteActivityInterface view;
    private Repo repo;

    public FavoritePresenter(Context context, FavoriteActivityInterface view) {
        this.view = view;
        this.repo = Repo.getInstance(context);
    }

    public void getFavoriteMeals(){
        repo.getStoredFavoriteMealsList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    view.showFavoriteMeals(data);
                });
    }

    public void deleteMeal(FavoriteMeal meal){
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

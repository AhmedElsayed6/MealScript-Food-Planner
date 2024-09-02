package com.example.mealscript.Favorite.Presenter;

import android.content.Context;

import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Favorite.View.FavoriteActivityInterface;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Repository.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenterImpl implements FavoritePresenter {
    private FavoriteActivityInterface view;
    private Repository repository;

    public FavoritePresenterImpl( FavoriteActivityInterface view , Repository repo) {
        this.view = view;
        this.repository = repo;
    }

    @Override
    public void getFavoriteMeals(){
        repository.getStoredFavoriteMealsList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    view.showFavoriteMeals(data);
                });
    }

    @Override
    public void deleteMeal(FavoriteMeal meal){
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            repository.deleteByUserIdAndIdMeal(authManager.getCurrentUserId(),meal.getIdMeal()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(  );
        } else {

        }
    }

    @Override
    public void goToDetailsPage(String mealID){
        repository.getMealById(mealID).subscribe(
                meal -> {
                    view.goToDetailsPage(meal.getMealsList().get(0));
                },
                throwable -> {

                }
        );

    }




}

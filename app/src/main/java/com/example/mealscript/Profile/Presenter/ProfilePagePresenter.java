package com.example.mealscript.Profile.Presenter;

import android.util.Log;

import com.example.mealscript.DB.Remote.RemoteDataBase;
import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Profile.View.ProfilePageInterface;
import com.example.mealscript.Repository.Repository;

import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePagePresenter implements CallbackForProfile {
    private AuthManager authManager;
    private RemoteDataBase remoteDataBase;
    private Repository repository;
    private ProfilePageInterface view;
    private static ProfilePagePresenter instance = null;


    private ProfilePagePresenter(ProfilePageInterface view, Repository repo) {
        authManager = new AuthManager();
        this.view = view;
        repository = repo;
    }

    public static ProfilePagePresenter getInstance(ProfilePageInterface view, Repository repo) {
        if (instance == null) {
            instance = new ProfilePagePresenter(view, repo);
        }
        return instance;
    }

    public void SignOut() {
        authManager.setGuestMode(false);
        authManager.SignOut();
    }

    public void BackUpData() {
        remoteDataBase = new RemoteDataBase();
        repository.getStoredFavoriteMealsListForIcons().subscribeOn(Schedulers.single()).subscribe(
                favoriteMealList -> {
                    remoteDataBase.insertFavoriteMealList(favoriteMealList);
                }
        );
        repository.getPlannerMealsListSingle().subscribeOn(Schedulers.single()).subscribe(
                plannerMealList -> {
                    remoteDataBase.insertPlannerMealList(plannerMealList);
                }
        );

    }

    public void SyncLocalDataBase() {
        remoteDataBase = new RemoteDataBase();
        remoteDataBase.insertIntoFavoritesTable(this);
        remoteDataBase.insertIntoPlannerTable(this);
    }

    public void setUserDetails() {
        if (!authManager.isGuestMode()) {
            remoteDataBase = new RemoteDataBase();
            remoteDataBase.getUserDetails(this);
        } else
            view.setUserDetails("Guest", "Guest");
    }

    @Override
    public void updateFavoriteLocalDataBase(List<FavoriteMeal> favoriteMealList) {
        repository.replaceFavoriteMealListForFireStore(favoriteMealList).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void updatePlannerLocalDataBase(List<PlannerMeal> plannerMealList) {
        repository.replacePlannerMealListForFireStore(plannerMealList).subscribeOn(Schedulers.io()).subscribe();
    }


    @Override
    public void displayErrorMessage(String errorMessage) {

    }

    @Override
    public void getUserDetails(String name, String email) {
        Log.i("AHMEDEIID", "getUserDetails: " + name + email);
        view.setUserDetails(name, email);
    }
}

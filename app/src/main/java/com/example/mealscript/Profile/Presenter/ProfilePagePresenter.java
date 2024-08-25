package com.example.mealscript.Profile.Presenter;

import android.content.Context;

import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.DB.Remote.RemoteDataBase;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Profile.View.ProfilePageInterface;
import com.example.mealscript.Repo.Repo;

import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePagePresenter implements CallbackForProfile {
    private AuthManager authManager;
    private RemoteDataBase remoteDataBase;
    private static ProfilePagePresenter instance = null;
    private static Repo repo;
    private static Context context;
    private static ProfilePageInterface view;

    private ProfilePagePresenter() {
        authManager = new AuthManager();
    }

    public static ProfilePagePresenter getInstance(Context context, ProfilePageInterface view) {
        if (instance == null) {
            instance = new ProfilePagePresenter();
        }
        ProfilePagePresenter.view = view;
        ProfilePagePresenter.context = context;
        repo = Repo.getInstance(context);
        return instance;
    }

    public void SignOut() {
        authManager.setGuestMode(false);
        authManager.SignOut();
    }

    public void BackUpData() {
        remoteDataBase = new RemoteDataBase();
        repo.getStoredFavoriteMealsListForIcons().subscribeOn(Schedulers.single()).subscribe(
                favoriteMealList -> {
                    remoteDataBase.insertFavoriteMealList(favoriteMealList);
                }
        );
        repo.getPlannerMealsListSingle().subscribeOn(Schedulers.single()).subscribe(
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


    @Override
    public void updateFavoriteLocalDataBase(List<FavoriteMeal> favoriteMealList) {
        repo.replaceFavoriteMealListForFireStore(favoriteMealList).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void updatePlannerLocalDataBase(List<PlannerMeal> plannerMealList) {
        repo.replacePlannerMealListForFireStore(plannerMealList).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void displayErrorMessage(String errorMessage) {

    }
}

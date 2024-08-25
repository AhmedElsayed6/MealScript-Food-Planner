package com.example.mealscript.DB.Local;

import android.content.Context;

import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private static FavMealDao favMealDao;
    private static PlannerMealDao plannerMealDao;
    private static Flowable<List<FavoriteMeal>> favMealList;
    private static Flowable<List<PlannerMeal>> plannerMealList;
    private static MealLocalDataSource instance = null;
    private static AuthManager authManager;
    private static AppDataBase db;

    private MealLocalDataSourceImpl(Context context) {
        db = AppDataBase.getInstance(context.getApplicationContext());
        authManager = new AuthManager();
        favMealDao = db.getFavMealDao();
        plannerMealDao = db.getPlannerMealDao();
    }

    public static MealLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new MealLocalDataSourceImpl(context);
        }
        authManager = new AuthManager();
        favMealDao = db.getFavMealDao();
        plannerMealDao = db.getPlannerMealDao();
        favMealList = favMealDao.getAllFavoriteMeals(authManager.getCurrentUserId());
        plannerMealList = plannerMealDao.getAllPlannerMeals(authManager.getCurrentUserId());
        return instance;
    }

    @Override
    public Flowable<List<FavoriteMeal>> getFavoriteMealsList() {
        return favMealList;
    }

    @Override
    public Single<List<FavoriteMeal>> getFavoriteMealsListForIcons() {
        return favMealDao.getAllFavoriteMealsToSetIcons(authManager.getCurrentUserId());
    }


    @Override
    public Completable insertFavoriteMeal(FavoriteMeal meal) {
        if (!AuthManager.isGuestMode())
            return favMealDao.insertFavoriteMeal(meal);
        else return Completable.never();
    }

    @Override
    public Completable deleteByUserIdAndIdMealFromFav(String userId, String idMeal) {
        return favMealDao.deleteByUserIdAndIdMealFromFav(userId, idMeal);
    }


    @Override
    public Completable insertPlannerMeal(PlannerMeal meal) {
        if (!AuthManager.isGuestMode())
            return plannerMealDao.insertPlannerMeal(meal);
        else return Completable.never();
    }


    @Override
    public Completable deleteByUserIdAndIdMealFromPlanner(String userId, String idMeal, int dayOfTheWeek) {
        return plannerMealDao.deleteByUserIdAndIdMealFromPlanner(userId, idMeal, dayOfTheWeek);
    }

    @Override
    public Flowable<List<PlannerMeal>> getPlannerMealsList() {
        return plannerMealList;
    }

    @Override
    public Single<List<PlannerMeal>> getPlannerMealListSingle() {
        return plannerMealDao.getAllPlannerMealsSingle(authManager.getCurrentUserId());
    }

    @Override
    public Completable replaceFavoriteMealListForFireStore(List<FavoriteMeal> favoriteMealList) {
        return favMealDao.deleteAllFromFav(authManager.getCurrentUserId()).andThen(favMealDao.insertFavoriteMealList(favoriteMealList));

    }

    @Override
    public Completable replacePlannerMealListForFireStore(List<PlannerMeal> plannerMealList) {
        return plannerMealDao.deleteAllFromPlanner(authManager.getCurrentUserId()).andThen(plannerMealDao.insertPlannerMealList(plannerMealList));
    }


}


package com.example.mealscript.Local;

import android.content.Context;

import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class MealLocalDataSource {
    private FavMealDao favMealDao;
    private PlannerMealDao plannerMealDao;
    private Flowable<List<FavoriteMeal>> favMealList;
    private Flowable<List<PlannerMeal>> plannerMealList;
    private static MealLocalDataSource instance = null;

    private MealLocalDataSource(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        favMealDao = db.getFavMealDao();
        plannerMealDao = db.getPlannerMealDao();
        favMealList = favMealDao.getAllFavoriteMeals();
        plannerMealList = plannerMealDao.getAllPlannerMeals();
    }

    public static MealLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new MealLocalDataSource(context);
        }
        return instance;
    }

    public Flowable<List<FavoriteMeal>> getFavoriteMealsList() {
        return favMealList;
    }

    public Single<List<FavoriteMeal>> getFavoriteMealsListForIcons() {
        return favMealDao.getAllFavoriteMealsToSetIcons();
    }


    public Completable insertFavoriteMeal(FavoriteMeal meal) {
        return favMealDao.insertFavoriteMeal(meal);
    }

    public Completable deleteByUserIdAndIdMealFromFav(String userId, String idMeal) {
        return favMealDao.deleteByUserIdAndIdMealFromFav(userId, idMeal);
    }


    // insert
    public Completable insertPlannerMeal(PlannerMeal meal) {
        return plannerMealDao.insertPlannerMeal(meal);
    }
    // delete
    public Completable deleteByUserIdAndIdMealFromPlanner(String userId, String idMeal, int dayOfTheWeek) {
        return plannerMealDao.deleteByUserIdAndIdMealFromPlanner(userId, idMeal , dayOfTheWeek);
    }

    public Flowable<List<PlannerMeal>> getPlannerMealsList() {
        return plannerMealList;
    }





}


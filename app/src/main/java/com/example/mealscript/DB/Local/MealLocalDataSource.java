package com.example.mealscript.DB.Local;

import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MealLocalDataSource {
    Flowable<List<FavoriteMeal>> getFavoriteMealsList();

    Single<List<FavoriteMeal>> getFavoriteMealsListForIcons();

    Completable insertFavoriteMeal(FavoriteMeal meal);

    Completable deleteByUserIdAndIdMealFromFav(String userId, String idMeal);

    Completable insertPlannerMeal(PlannerMeal meal);

    Completable deleteByUserIdAndIdMealFromPlanner(String userId, String idMeal, int dayOfTheWeek);

    Flowable<List<PlannerMeal>> getPlannerMealsList();

    Single<List<PlannerMeal>> getPlannerMealListSingle();

    Completable replaceFavoriteMealListForFireStore(List<FavoriteMeal> favoriteMealList);

    Completable replacePlannerMealListForFireStore(List<PlannerMeal> plannerMealList);
}

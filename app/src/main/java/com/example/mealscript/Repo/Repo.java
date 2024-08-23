package com.example.mealscript.Repo;

import android.content.Context;

import com.example.mealscript.Local.MealLocalDataSource;
import com.example.mealscript.Local.Utils;
import com.example.mealscript.Model.Categories;
import com.example.mealscript.Model.Category;
import com.example.mealscript.Model.ContainerMealLists;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Model.Meals;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repo {
    private MealRemoteDataSource remoteDataSource;
    private MealLocalDataSource localDataSource;
    private Utils utils;
    private static Repo instance = null;
    private Context context;

    private Repo(Context context) {
        remoteDataSource = MealRemoteDataSource.getInstance();
        localDataSource = MealLocalDataSource.getInstance(context);
        this.context = context;
    }

    public static Repo getInstance(Context context) {
        if (instance == null) {
            instance = new Repo(context);
        }
        return instance;
    }

    public Observable<Meals> getDailyInspiration() {
        utils = new Utils(context);
        return remoteDataSource.getDailyInspirations(utils.getTodayLetter());
    }

    public Observable<Meals> getMoreYouMayLike() {
        utils = new Utils(context);
        return remoteDataSource.getMoreYouMayLike(utils.getRandomLetter());
    }

    public Observable<Categories> getCategories() {
        return remoteDataSource.getCategories();
    }

    public Observable<Meals> getMealById(String mealID) {
        return remoteDataSource.getMealByID(mealID);
    }


    public Observable<Meals> getAreas() {
        return remoteDataSource.getAreas();
    }

    public Observable<Meals> getDesserts() {
        return remoteDataSource.getDesserts();
    }

    public Observable<Meals> searchByName(String mealName) {
        return remoteDataSource.searchByName(mealName);
    }

    public Flowable<List<FavoriteMeal>> getStoredFavoriteMealsList() {
        return localDataSource.getFavoriteMealsList();
    }

    public Single<List<FavoriteMeal>> getStoredFavoriteMealsListForIcons() {
        return localDataSource.getFavoriteMealsListForIcons();
    }

    public Completable insertFavoriteMeal(FavoriteMeal product) {
        return localDataSource.insertFavoriteMeal(product);
    }

    public Completable deleteByUserIdAndIdMeal(String userId, String idMeal) {
        return localDataSource.deleteByUserIdAndIdMealFromFav(userId, idMeal);
    }

    public Observable<ContainerMealLists> getHomePageData() {
        Observable<List<Meal>> dailyInspirationObservable = getDailyInspiration()
                .map(item -> item.getMealsList().stream().limit(5).collect(Collectors.toList()))
                .subscribeOn(Schedulers.io());

        Observable<List<Meal>> moreYouMayLikeObservable = getMoreYouMayLike()
                .map(item -> item.getMealsList())
                .subscribeOn(Schedulers.io());

        Observable<List<Category>> categoriesObservable = getCategories()
                .map(categories -> ((Categories) categories).getCategories())
                .subscribeOn(Schedulers.io());

        Observable<List<Meal>> areasObservable = getAreas()
                .map(areas -> ((Meals) areas).getMealsList())
                .subscribeOn(Schedulers.io());

        Observable<List<Meals>> dessertsObservable = getDesserts()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .take(8)
                .toList()
                .toObservable();

        Observable<List<FavoriteMeal>> favoriteMealsObservable = getStoredFavoriteMealsListForIcons()
                .subscribeOn(Schedulers.io())
                .toObservable();
        return Observable.zip(
                        dailyInspirationObservable,
                        moreYouMayLikeObservable,
                        categoriesObservable,
                        areasObservable,
                        dessertsObservable,
                        (dailyInspirations, moreYouMayLike, categories, areas, desserts) -> {
                            ContainerMealLists items = new ContainerMealLists();
                            List<Meal> allMeals = new ArrayList<>();
                            for (Meals meals : desserts) {
                                allMeals.addAll(meals.getMealsList());
                            }
                            items.setDailyInspirationsList(dailyInspirations);
                            items.setMoreYouMightLikeList(moreYouMayLike);
                            items.setCategoryList(categories);
                            items.setAreasList(areas);
                            items.setDessertsList(allMeals);
                            return items;
                        })
                .flatMap(items -> favoriteMealsObservable
                        .map(favoriteMeals -> {
                            items.setFavoriteMealList(favoriteMeals);
                            items.updateFavoriteStatus();
                            return items;
                        })
                )
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Meal>> searchByArea(String country) {
        return Single.zip(
                remoteDataSource.searchByCountry(country).toList(),
                getStoredFavoriteMealsListForIcons(),
                (meals, favoriteMeals) -> {
                    for (Meal meal : meals) {
                        for (FavoriteMeal favoriteMeal : favoriteMeals) {
                            if (meal.getIdMeal().equals(favoriteMeal.getIdMeal())) {
                                meal.setFavorite(true);
                            }
                        }
                    }
                    return meals;
                }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Meal>> searchByCategory(String category) {
        return Single.zip(
                remoteDataSource.searchByCategory(category).toList(),
                getStoredFavoriteMealsListForIcons(),
                (meals, favoriteMeals) -> {
                    for (Meal meal : meals) {
                        for (FavoriteMeal favoriteMeal : favoriteMeals) {
                            if (meal.getIdMeal().equals(favoriteMeal.getIdMeal())) {
                                meal.setFavorite(true);
                            }
                        }
                    }
                    return meals;
                }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Single<List<Meal>> searchByIngredients(String ingredient) {
        return Single.zip(
                remoteDataSource.searchByIngredient(ingredient).toList(),
                getStoredFavoriteMealsListForIcons(),
                (meals, favoriteMeals) -> {
                    for (Meal meal : meals) {
                        for (FavoriteMeal favoriteMeal : favoriteMeals) {
                            if (meal.getIdMeal().equals(favoriteMeal.getIdMeal())) {
                                meal.setFavorite(true);
                            }
                        }
                    }
                    return meals;
                }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Completable insertPlannerMeal(PlannerMeal meal) {
        return localDataSource.insertPlannerMeal(meal);
    }

    public Completable deleteByUserIdAndIdMealFromPlanner(String userId, String idMeal, int dayOfTheWeek) {
        return localDataSource.deleteByUserIdAndIdMealFromPlanner(userId, idMeal, dayOfTheWeek);
    }

    public Flowable<List<PlannerMeal>> getPlannerMealsList() {
        return localDataSource.getPlannerMealsList();
    }

}

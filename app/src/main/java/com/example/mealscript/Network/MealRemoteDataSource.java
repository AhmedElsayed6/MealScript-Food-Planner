package com.example.mealscript.Network;

import android.util.Log;

import com.example.mealscript.Model.Categories;
import com.example.mealscript.Model.Category;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Model.Meals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {

    private final static String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static final String TAG = "Network";
    RetroMealsInterface retroMealsInterface;
    static MealRemoteDataSource client = null;

    private MealRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL).build();
        retroMealsInterface = retrofit.create(RetroMealsInterface.class);
    }

    public static MealRemoteDataSource getInstance() {
        if (client == null)
            client = new MealRemoteDataSource();
        return client;
    }

    public void getRandomMeals() {
        Observable mealObservable = retroMealsInterface.getRandomMeal();
        Observer<Meals> observer = new Observer<Meals>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Meals meals) {
                for (int i = 0; i < meals.getMealsList().size(); i++) {

                    Log.i(TAG, "onNext: " + meals.getMealsList().get(i).getStrMeal());

                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        mealObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }

    public void getMealsByName(NetworkDelegate delegate, String mealName) {
        Observable mealObservable = retroMealsInterface.getMealsByName(mealName);
        Observer<Meals> observer = new Observer<Meals>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Meals meals) {
                Log.i("Network", "onNext: u succck");
                delegate.returnMeals(meals.getMealsList());


            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        mealObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }

    public void getCategories(NetworkDelegate delegate) {
        Observable categoriesObserver = retroMealsInterface.getCategories();
        categoriesObserver.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    List<Category> categoriesList = ((Categories) categories).getCategories();
                    delegate.returnCategories(categoriesList);
                });
    }

    public void getAreas(NetworkDelegate delegate) {
        Observable categoriesObserver = retroMealsInterface.getAreasList();
        categoriesObserver.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    List<Meal> areaList = ((Meals) meals).getMealsList();
                    delegate.returnAreas(areaList);
                });
    }

    public void searchByName(SearchCallback callback, String mealName) {
        Observable mealObservable = retroMealsInterface.searchMealByName(mealName);
        Observer<Meals> observer = new Observer<Meals>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Meals meals) {
                callback.returnMealsOnSuccess(meals.getMealsList());
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        mealObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void searchByCategory(SearchCallback callback, String categoryName) {
        List<Meal> returnedMeals = new ArrayList<>();
        retroMealsInterface.getCategoriesList()
                .map(mealResponse -> {
                    List<String> categoryNames = new ArrayList<>();
                    for (Meal meal : mealResponse.getMealsList()) {
                        categoryNames.add(meal.getStrCategory());
                    }
                    return categoryNames;
                })
                .flatMap(categoryNames -> {
                    List<String> foundCategoriesLists = categoryNames.stream()
                            .filter(name -> name.toLowerCase().contains(categoryName.toLowerCase()))
                            .collect(Collectors.toList());

                    if (foundCategoriesLists.isEmpty()) {
                        // If no categories are found, return an empty Observable
                        return Observable.empty();
                    }
                    return Observable.fromIterable(foundCategoriesLists)
                            .flatMap(category -> retroMealsInterface.filterByCategories(category))
                            .flatMap(categoryResponse -> Observable.fromIterable(categoryResponse.getMealsList()))
                            .flatMap(meal -> retroMealsInterface.getMealById(meal.getIdMeal()))
                            .map(mealsResponse -> mealsResponse.getMealsList().get(0));
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meal -> returnedMeals.add(meal),
                        throwable -> {
                            callback.returnMealsOnFailure(new ArrayList<>());
                        },
                        () -> {
                            if (!returnedMeals.isEmpty()) {
                                callback.returnMealsOnSuccess(returnedMeals);
                            } else {
                                callback.returnMealsOnFailure(new ArrayList<>());
                            }
                        }
                );
    }


    public void searchByIngredient(SearchCallback callback, String ingredientName) {
        List<Meal> returnedMeals = new ArrayList<>();
        retroMealsInterface.getIngredients()
                .map(mealResponse -> {
                    List<String> ingredientNames = new ArrayList<>();
                    for (Meal meal : mealResponse.getMealsList()) {
                        ingredientNames.add(meal.getStrIngredient());
                    }
                    return ingredientNames;
                })
                .flatMap(categoryNames -> {
                    List<String> foundCategoriesLists = categoryNames.stream()
                            .filter(name -> name.toLowerCase().contains(ingredientName.toLowerCase()))
                            .collect(Collectors.toList());

                    if (foundCategoriesLists.isEmpty()) {
                        // If no categories are found, return an empty Observable
                        return Observable.empty();
                    }
                    return Observable.fromIterable(foundCategoriesLists)
                            .flatMap(category -> retroMealsInterface.filterByCategories(category))
                            .flatMap(categoryResponse -> Observable.fromIterable(categoryResponse.getMealsList()))
                            .flatMap(meal -> retroMealsInterface.getMealById(meal.getIdMeal()))
                            .map(mealsResponse -> mealsResponse.getMealsList().get(0));
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meal -> returnedMeals.add(meal),
                        throwable -> {
                            callback.returnMealsOnFailure(new ArrayList<>());
                        },
                        () -> {
                            if (!returnedMeals.isEmpty()) {
                                callback.returnMealsOnSuccess(returnedMeals);
                            } else {
                                callback.returnMealsOnFailure(new ArrayList<>());
                            }
                        }
                );
    }


}




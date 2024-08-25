package com.example.mealscript.Network;

import android.util.Log;

import com.example.mealscript.Model.Categories;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Model.Meals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
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

    ObservableTransformer observableTransformer() {
        return new ObservableTransformer() {
            @Override
            public Observable apply(Observable observableToApllyStuffOn) {
                return observableToApllyStuffOn.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

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


    public Observable<Meals> getDailyInspirations(String letter) {
        return retroMealsInterface.getMealsByName(letter).compose(observableTransformer());
    }

    public Observable<Meals> getMealByID(String mealID) {
        return retroMealsInterface.getMealById(mealID);
    }

    public Observable<Meals> getMoreYouMayLike(String mealName) {
        return retroMealsInterface.getMealsByName(mealName).compose(observableTransformer());
    }
    public Observable<Categories> getCategories() {
        return retroMealsInterface.getCategories().compose(observableTransformer());
    }

    public Observable<Meals>  getAreas() {
       return retroMealsInterface.getAreasList().compose(observableTransformer());
    }
    public Observable<Meals> getDesserts() {
         return retroMealsInterface.filterByCategories("Dessert")
                 .flatMap(categoryResponse -> Observable.fromIterable(categoryResponse.getMealsList()))
                 .flatMap(meal -> retroMealsInterface.getMealById(meal.getIdMeal()));
    }
    public Observable<Meals> searchByName(String mealName) {
       return retroMealsInterface.searchMealByName(mealName).compose(observableTransformer());
    }

    public  Observable<Meal>  searchByCategory( String categoryName) {
     return   retroMealsInterface.getCategoriesList()
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
                        return Observable.empty();
                    }
                    return Observable.fromIterable(foundCategoriesLists)
                            .flatMap(category -> retroMealsInterface.filterByCategories(category))
                            .flatMap(categoryResponse -> Observable.fromIterable(categoryResponse.getMealsList()))
                            .flatMap(meal -> retroMealsInterface.getMealById(meal.getIdMeal()))
                            .map(mealsResponse -> mealsResponse.getMealsList().get(0));
                });

    }


    public  Observable<Meal>   searchByIngredient( String ingredientName) {
     return   retroMealsInterface.getIngredients()
                .map(mealResponse -> {
                    List<String> ingredientNames = new ArrayList<>();
                    for (Meal meal : mealResponse.getMealsList()) {
                        ingredientNames.add(meal.getStrIngredient());
                    }
                    return ingredientNames;
                })
                .flatMap(ingredientNames -> {
                    List<String> foundIngredientLists = ingredientNames.stream()
                            .filter(name -> name.toLowerCase().contains(ingredientName.toLowerCase()))
                            .collect(Collectors.toList());

                    if (foundIngredientLists.isEmpty()) {
                        // If no categories are found, return an empty Observable
                        return Observable.empty();
                    }
                    return Observable.fromIterable(foundIngredientLists)
                            .flatMap(ingredient -> retroMealsInterface.filterByIngredients(ingredient))
                            .flatMap(ingredientResponse -> Observable.fromIterable(ingredientResponse.getMealsList()))
                            .flatMap(meal -> retroMealsInterface.getMealById(meal.getIdMeal()))
                            .map(mealsResponse -> mealsResponse.getMealsList().get(0));
                });
    }

    public Observable<Meal> searchByCountry( String countryName) {
      return  retroMealsInterface.getAreasList()
                .map(mealResponse -> {
                    List<String> countryNames = new ArrayList<>();
                    for (Meal meal : mealResponse.getMealsList()) {
                        countryNames.add(meal.getStrArea());
                    }
                    return countryNames;
                })
                .flatMap(countriesNames -> {
                    List<String> foundCountriesLists = countriesNames.stream()
                            .filter(name -> name.toLowerCase().contains(countryName.toLowerCase()))
                            .collect(Collectors.toList());

                    if (foundCountriesLists.isEmpty()) {
                        // If no categories are found, return an empty Observable
                        return Observable.empty();
                    }
                    return Observable.fromIterable(foundCountriesLists)
                            .flatMap(country -> retroMealsInterface.filterByCountry(country))
                            .flatMap(countryResponse -> Observable.fromIterable(countryResponse.getMealsList()))
                            .flatMap(meal -> retroMealsInterface.getMealById(meal.getIdMeal()))
                            .map(mealsResponse -> mealsResponse.getMealsList().get(0));
                });

    }


}




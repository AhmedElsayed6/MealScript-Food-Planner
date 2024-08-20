package com.example.mealscript.Network;

import com.example.mealscript.Model.Categories;
import com.example.mealscript.Model.Meals;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroMealsInterface {
    @GET("random.php")
    Observable<Meals> getRandomMeal();

    @GET("search.php")
    Observable<Meals> getMealsByName(@Query("s") String mealName);
    @GET("search.php")
    Observable<Meals> searchMealByName(@Query("s") String mealName);
    @GET("filter.php")
    Observable<Meals> filterByCategories(@Query("c") String categoryName);


    @GET("search.php")
    Observable<Meals> getMealsByFirstLetter(@Query("f") String firstLetter);

    @GET("lookup.php")
    Observable<Meals> getMealById(@Query("i") String mealId);
    @GET("categories.php")
    Observable<Categories> getCategories();

    @GET("list.php?a=list")
    Observable<Meals> getAreasList();
    @GET("list.php?i=list")
    Observable<Meals> getIngredients();
    @GET("list.php?c=list")
    Observable<Meals> getCategoriesList();











}

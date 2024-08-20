package com.example.mealscript.Repo;

import com.example.mealscript.Network.MealRemoteDataSource;
import com.example.mealscript.Network.NetworkDelegate;
import com.example.mealscript.Network.SearchCallback;

public class Repo {
    MealRemoteDataSource remoteDataSource;

    private static Repo instance = null;


    private Repo() {
        remoteDataSource = MealRemoteDataSource.getInstance();
    }

    public static Repo getInstance() {
        if (instance == null)
            instance = new Repo();
        return instance;
    }


    public void getDailyInspirations(NetworkDelegate delegate, String randomLetter) {
        remoteDataSource.getMealsByName(delegate, randomLetter);
    }
    public void getCategories(NetworkDelegate delegate) {
        remoteDataSource.getCategories(delegate);
    }
    public void getArea(NetworkDelegate delegate) {
        remoteDataSource.getAreas(delegate);
    }

    public void searchByName(SearchCallback callback, String mealName) {
        remoteDataSource.searchByName(callback, mealName);
    }

    public void searchByCategory(SearchCallback callback, String category) {
        remoteDataSource.searchByCategory(callback,category);
    }
    public void searchByIngredients(SearchCallback callback, String category) {

    }
    public void searchByArea(SearchCallback callback, String category) {

    }



}

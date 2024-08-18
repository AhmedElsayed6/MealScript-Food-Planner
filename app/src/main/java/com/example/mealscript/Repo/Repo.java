package com.example.mealscript.Repo;

import com.example.mealscript.Network.NetworkDelegate;
import com.example.mealscript.Network.MealRemoteDataSource;

public class Repo {
    MealRemoteDataSource remoteDataSource ;

    private static Repo instance = null;


    private Repo(){
        remoteDataSource = MealRemoteDataSource.getInstance();
    }

    public static Repo getInstance(){
        if(instance == null)
            instance = new Repo();
        return instance;
    }


    public void getDailyInspirations(NetworkDelegate delegate , String randomLetter ){
        remoteDataSource.getMealsByName(delegate , randomLetter);
    }
    public void getCategories(NetworkDelegate delegate ){
        remoteDataSource.getCategories(delegate);
    }
    public void getArea(NetworkDelegate delegate ){
        remoteDataSource.getAreas(delegate);

    }





}

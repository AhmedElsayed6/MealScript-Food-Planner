package com.example.mealscript.Repo;

import com.example.mealscript.Home.Presenters.NetworkDelegate;
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


    public void getRandomMeals(NetworkDelegate delegate){
        remoteDataSource.getMealsByName(delegate , "s");
    }





}

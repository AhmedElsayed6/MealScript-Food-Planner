package com.example.mealscript.Local;

import android.content.Context;

import com.example.mealscript.Model.FavoriteMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealLocalDataSource {
    private FavMealDao favMealDao;
    private Flowable<List<FavoriteMeal>> favMealList;
    private static MealLocalDataSource instance =null;

        private MealLocalDataSource(Context context){
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        favMealDao =db.getFavMealDao();
        favMealList = favMealDao.getAllFavoriteMeals();

    }
    public  static MealLocalDataSource getInstance(Context context){
        if(instance ==null ){
            instance = new MealLocalDataSource(context);
        }
        return instance;
    }

    public Flowable<List<FavoriteMeal>> getFavoriteMealsList(){
        return  favMealList;
    }
    public Single<List<FavoriteMeal>> getFavoriteMealsListForIcons(){
        return  favMealDao.getAllFavoriteMealsToSetIcons();
    }


    public Completable insertFavoriteMeal(FavoriteMeal meal){
        return favMealDao.insertFavoriteMeal(meal);

    }

    public  Completable deleteByUserIdAndIdMeal(String userId, String idMeal){
        return favMealDao.deleteByUserIdAndIdMeal(userId,idMeal);
    }


}


package com.example.mealscript.Local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealscript.Model.FavoriteMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface FavMealDao {

    @Query("SELECT * FROM fav_table Where userId = userid")
    Flowable<List<FavoriteMeal>> getAllFavoriteMeals(String userid);

    @Query("SELECT * FROM fav_table Where userId = userid")
    Single<List<FavoriteMeal>> getAllFavoriteMealsToSetIcons(String userid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavoriteMeal(FavoriteMeal meal);

    @Query("DELETE FROM Fav_Table WHERE userId = :userId AND idMeal = :idMeal")
    Completable deleteByUserIdAndIdMealFromFav(String userId, String idMeal);


}
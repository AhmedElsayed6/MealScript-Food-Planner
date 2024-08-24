package com.example.mealscript.DB.Local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mealscript.Model.FavoriteMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface FavMealDao {

    @Query("SELECT * FROM fav_table WHERE userId = :userId")
    Flowable<List<FavoriteMeal>> getAllFavoriteMeals(String userId);

    @Query("SELECT * FROM fav_table WHERE userId = :userId")
    Single<List<FavoriteMeal>> getAllFavoriteMealsToSetIcons(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavoriteMeal(FavoriteMeal meal);

    @Query("DELETE FROM Fav_Table WHERE userId = :userId AND idMeal = :idMeal")
    Completable deleteByUserIdAndIdMealFromFav(String userId, String idMeal);

    @Query("DELETE FROM Fav_Table Where userId = :userId")
    Completable deleteAllFromFav(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavoriteMealList(List<FavoriteMeal> favoriteMealList);


}
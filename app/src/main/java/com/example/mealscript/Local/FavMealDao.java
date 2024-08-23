package com.example.mealscript.Local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealscript.Model.FavoriteMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface FavMealDao {

    @Query("SELECT * FROM fav_table")
    Flowable<List<FavoriteMeal>> getAllFavoriteMeals();

    @Query("SELECT * FROM fav_table")
    Single<List<FavoriteMeal>> getAllFavoriteMealsToSetIcons();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavoriteMeal(FavoriteMeal meal);

    @Query("DELETE FROM Fav_Table WHERE userId = :userId AND idMeal = :idMeal")
    Completable deleteByUserIdAndIdMeal(String userId, String idMeal);


}
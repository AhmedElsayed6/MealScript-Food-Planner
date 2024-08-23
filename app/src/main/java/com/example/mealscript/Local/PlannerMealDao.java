package com.example.mealscript.Local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PlannerMealDao {

    @Query("SELECT * FROM planner_table WHERE userId = userid")
    Flowable<List<PlannerMeal>> getAllPlannerMeals(String userid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlannerMeal(PlannerMeal meal);

    @Query("DELETE FROM planner_table WHERE userId = :userId AND idMeal = :idMeal AND dayOfTheWeek = :dayOfTheWeek")
    Completable deleteByUserIdAndIdMealFromPlanner(String userId, String idMeal, int dayOfTheWeek);


}
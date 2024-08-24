package com.example.mealscript.DB.Local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mealscript.Model.PlannerMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PlannerMealDao {

    @Query("SELECT * FROM planner_table WHERE userId = :userId")
    Flowable<List<PlannerMeal>> getAllPlannerMeals(String userId);

    @Query("SELECT * FROM planner_table WHERE userId = :userId")
    Single<List<PlannerMeal>> getAllPlannerMealsSingle(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlannerMeal(PlannerMeal meal);

    @Query("DELETE FROM planner_table WHERE userId = :userId AND idMeal = :idMeal AND dayOfTheWeek = :dayOfTheWeek")
    Completable deleteByUserIdAndIdMealFromPlanner(String userId, String idMeal, int dayOfTheWeek);

    @Query("DELETE FROM planner_table WHERE userId = :userId")
    Completable deleteAllFromPlanner(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlannerMealList(List<PlannerMeal> plannerMealList);




}
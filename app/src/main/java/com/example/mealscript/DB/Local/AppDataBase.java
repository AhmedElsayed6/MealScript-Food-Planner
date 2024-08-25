package com.example.mealscript.DB.Local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;


@Database(entities = {FavoriteMeal.class, PlannerMeal.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;

    public abstract FavMealDao getFavMealDao();

    public abstract PlannerMealDao getPlannerMealDao();


    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "Fav_Meal_DB")
                    .fallbackToDestructiveMigration().build();
        return instance;
    }


}

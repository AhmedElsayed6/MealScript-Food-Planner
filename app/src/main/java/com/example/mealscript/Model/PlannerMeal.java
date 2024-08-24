package com.example.mealscript.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Planner_Table", primaryKeys = {"userId", "idMeal", "dayOfTheWeek"})public class PlannerMeal {


    private String strMeal;
    @NonNull
    private String userId;
    @NonNull
    private String idMeal;
    @NonNull
    private int dayOfTheWeek;
    private String strMealThumb;

    public PlannerMeal(){}

    public PlannerMeal(String userId, String strMeal, String idMeal, String strMealThumb, int dayOfTheWeek) {
        this.userId = userId;
        this.strMeal = strMeal;
        this.idMeal = idMeal;
        this.strMealThumb = strMealThumb;
        this.dayOfTheWeek = dayOfTheWeek;
    }


    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
}

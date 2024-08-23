package com.example.mealscript.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Fav_Table")
public class FavoriteMeal {

    @PrimaryKey(autoGenerate = true)
    private int id;


    private String strMeal;
    private String userId;
    private String idMeal;
    private String strMealThumb;

    public FavoriteMeal(String userId, String strMeal, String idMeal, String strMealThumb) {
        this.userId = userId;
        this.strMeal = strMeal;
        this.idMeal = idMeal;
        this.strMealThumb = strMealThumb;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

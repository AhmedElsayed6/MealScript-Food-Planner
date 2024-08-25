package com.example.mealscript.Model;

import java.util.List;

public class User {
    String userId;

    public User(String userId, String displayName, String email, List<PlannerMeal> plannerMealList, List<FavoriteMeal> favoriteMealList) {
        this.userId = userId;
        this.displayName = displayName;
        this.email = email;
        this.plannerMealList = plannerMealList;
        this.favoriteMealList = favoriteMealList;
    }

    String displayName;
    String email;
    List<PlannerMeal> plannerMealList;
    List<FavoriteMeal> favoriteMealList;

    public User(){

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PlannerMeal> getPlannerMealList() {
        return plannerMealList;
    }

    public void setPlannerMealList(List<PlannerMeal> plannerMealList) {
        this.plannerMealList = plannerMealList;
    }

    public List<FavoriteMeal> getFavoriteMealList() {
        return favoriteMealList;
    }

    public void setFavoriteMealList(List<FavoriteMeal> favoriteMealList) {
        this.favoriteMealList = favoriteMealList;
    }



}

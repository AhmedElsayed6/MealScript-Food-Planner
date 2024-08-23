package com.example.mealscript.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContainerMealLists {

    private List<Meal> dailyInspirationsList;
    private List<Meal> moreYouMightLikeList;
    private List<Meal> dessertsList;
    private List<FavoriteMeal> favoriteMealList;
    private List<Meal> areasList;
    private List<Category> categoryList;

    public void updateFavoriteStatus() {
        Set<String> favoriteMealIds = new HashSet<>();
        for (FavoriteMeal favMeal : favoriteMealList) {
            favoriteMealIds.add(favMeal.getIdMeal());
        }
        updateListWithFavorites(dailyInspirationsList, favoriteMealIds);
        updateListWithFavorites(moreYouMightLikeList, favoriteMealIds);
        updateListWithFavorites(dessertsList, favoriteMealIds);
    }

    private void updateListWithFavorites(List<Meal> mealList, Set<String> favoriteMealIds) {
        if (mealList != null) {
            for (Meal meal : mealList) {
                if (favoriteMealIds.contains(meal.getIdMeal())) {
                    meal.setFavorite(true);
                } else {
                    meal.setFavorite(false);
                }
            }
        }
    }


    public ContainerMealLists() {
        dailyInspirationsList = new ArrayList<>();
        categoryList = new ArrayList<>();
        moreYouMightLikeList = new ArrayList<>();
        areasList = new ArrayList<>();
        dessertsList = new ArrayList<>();
        favoriteMealList = new ArrayList<>();
    }


    public List<FavoriteMeal> getFavoriteMealList() {
        return favoriteMealList;
    }

    public void setFavoriteMealList(List<FavoriteMeal> favoriteMealList) {
        this.favoriteMealList = favoriteMealList;
    }

    public List<Meal> getDessertsList() {
        return dessertsList;
    }

    public void setDessertsList(List<Meal> dessertsList) {
        this.dessertsList = dessertsList;
    }

    public List<Meal> getMoreYouMightLikeList() {
        return moreYouMightLikeList;
    }

    public void setMoreYouMightLikeList(List<Meal> moreYouMightLikeList) {
        this.moreYouMightLikeList = moreYouMightLikeList;
    }

    public List<Meal> getAreasList() {
        return areasList;
    }

    public void setAreasList(List<Meal> areasList) {
        this.areasList = areasList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Meal> getDailyInspirationsList() {
        return dailyInspirationsList;
    }

    public void setDailyInspirationsList(List<Meal> dailyInspirationsList) {
        this.dailyInspirationsList = dailyInspirationsList;
    }


}
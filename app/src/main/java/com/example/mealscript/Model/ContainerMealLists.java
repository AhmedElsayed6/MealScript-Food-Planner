package com.example.mealscript.Model;

import java.util.ArrayList;
import java.util.List;

public class ContainerMealLists {
    private List<Meal> dailyInspirationsList;
    private List<Meal> areasList;
    private List<Category> categoryList;

    public ContainerMealLists() {
        dailyInspirationsList = new ArrayList<>();
        categoryList = new ArrayList<>();
        areasList = new ArrayList<>();
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
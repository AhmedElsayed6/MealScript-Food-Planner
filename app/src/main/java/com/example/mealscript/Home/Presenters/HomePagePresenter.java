package com.example.mealscript.Home.Presenters;

import com.example.mealscript.Home.Views.HomePageInterface;
import com.example.mealscript.Model.Category;
import com.example.mealscript.Model.ContainerMealLists;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Network.NetworkDelegate;
import com.example.mealscript.Repo.Repo;

import java.util.List;
import java.util.Random;

public class HomePagePresenter implements NetworkDelegate {
    private static HomePagePresenter instance = null;
    private HomePageInterface view;
    private Repo repo ;
    private ContainerMealLists items;

    private HomePagePresenter(HomePageInterface view) {
        this.view = view;
        this.repo = Repo.getInstance();
        this.items = new ContainerMealLists();
    }


    public static HomePagePresenter getInstance(HomePageInterface view) {
        if (instance == null)
            instance = new HomePagePresenter(view);
        return instance;
    }

    public void getData() {
        repo.getDailyInspirations(this,"s");
        repo.getCategories(this);
        repo.getArea(this);
    }
    @Override

    public void returnMeals(List<Meal> meals) {
        items.setDailyInspirationsList(meals);
        view.setData(items);
    }

    @Override
    public void returnCategories(List<Category> categories) {
        items.setCategoryList(categories);
        view.setData(items);

    }

    @Override
    public void returnAreas(List<Meal> areas) {
        items.setAreasList(areas);
        view.setData(items);
    }


    public static String getRandomLetterExcluding(String excludedLetter) {
        Random random = new Random();
        char randomLetter;
        do {
            randomLetter = (char) (random.nextInt(26) + 'a');
        } while (String.valueOf(randomLetter).equals(excludedLetter.toLowerCase()));
        return String.valueOf(randomLetter);
    }



}

package com.example.mealscript.Search.Presenter;

import com.example.mealscript.Model.Meal;
import com.example.mealscript.Network.SearchCallback;
import com.example.mealscript.Repo.Repo;
import com.example.mealscript.Search.Views.SearchPageInterface;

import java.util.List;

public class SearchPagePresenter implements SearchCallback {

    private SearchPageInterface view;
    private Repo repo;

    public SearchPagePresenter(SearchPageInterface view) {
        this.view = view;
        this.repo = Repo.getInstance();
    }


    public void search(String query, String category) {
        switch (category) {
            case "Meal":
                repo.searchByName(this, query);
                break;
            case "Country":
                repo.searchByArea(this, query);
                break;
            case "Ingredient":
                repo.searchByIngredients(this, query);
                break;
            case "Category":
                repo.searchByCategory(this, query);
                break;
        }
    }

    @Override
    public void returnMealsOnSuccess(List<Meal> meals) {
        view.viewData(meals);
    }

    @Override
    public void returnMealsOnFailure(List<Meal> meals) {

    }
}
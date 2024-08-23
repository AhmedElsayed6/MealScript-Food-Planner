package com.example.mealscript.Planner.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealscript.MealDetails.Views.MealDetailsActivity;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Planner.Presenter.CalenderPagePresenter;
import com.example.mealscript.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CalenderPage extends Fragment implements CalenderPageInterface {
    CalenderPagePresenter presenter;
    RecyclerView satRecyclerView, sunRecyclerView, monRecyclerView, tuesRecyclerView, wedRecyclerView, thurRecyclerView, friRecyclerView;
    CalenderRecyclerViewAdapter satAdapter, sunAdapter, monAdapter, tuesAdapter, wedAdapter, thurAdapter, friAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calender_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CalenderPagePresenter(this, getContext());
        presenter.getPlannerMeals();
        satRecyclerView = view.findViewById(R.id.satRecyclerView);
        sunRecyclerView = view.findViewById(R.id.sunRecyclerView);
        monRecyclerView = view.findViewById(R.id.monRecyclerView);
        tuesRecyclerView = view.findViewById(R.id.tuesRecyclerView);
        wedRecyclerView = view.findViewById(R.id.wedRecyclerView);
        thurRecyclerView = view.findViewById(R.id.thurRecyclerView);
        friRecyclerView = view.findViewById(R.id.friRecyclerView);
        satAdapter = new CalenderRecyclerViewAdapter(this, new ArrayList<>());
        sunAdapter = new CalenderRecyclerViewAdapter(this, new ArrayList<>());
        monAdapter = new CalenderRecyclerViewAdapter(this, new ArrayList<>());
        tuesAdapter = new CalenderRecyclerViewAdapter(this, new ArrayList<>());
        wedAdapter = new CalenderRecyclerViewAdapter(this, new ArrayList<>());
        thurAdapter = new CalenderRecyclerViewAdapter(this, new ArrayList<>());
        friAdapter = new CalenderRecyclerViewAdapter(this, new ArrayList<>());
        satRecyclerView.setAdapter(satAdapter);
        sunRecyclerView.setAdapter(sunAdapter);
        monRecyclerView.setAdapter(monAdapter);
        tuesRecyclerView.setAdapter(tuesAdapter);
        wedRecyclerView.setAdapter(wedAdapter);
        thurRecyclerView.setAdapter(thurAdapter);
        friRecyclerView.setAdapter(friAdapter);
    }

    @Override
    public void showPlannerMeals(List<PlannerMeal> plannerMealList) {
        satAdapter.setItems(plannerMealList.stream().filter(item -> item.getDayOfTheWeek() == (1)).collect(Collectors.toList()));
        sunAdapter.setItems(plannerMealList.stream().filter(item -> item.getDayOfTheWeek() == (2)).collect(Collectors.toList()));
        monAdapter.setItems(plannerMealList.stream().filter(item -> item.getDayOfTheWeek() == (3)).collect(Collectors.toList()));
        tuesAdapter.setItems(plannerMealList.stream().filter(item -> item.getDayOfTheWeek() == (4)).collect(Collectors.toList()));
        wedAdapter.setItems(plannerMealList.stream().filter(item -> item.getDayOfTheWeek() == (5)).collect(Collectors.toList()));
        thurAdapter.setItems(plannerMealList.stream().filter(item -> item.getDayOfTheWeek() == (6)).collect(Collectors.toList()));
        friAdapter.setItems(plannerMealList.stream().filter(item -> item.getDayOfTheWeek() == (7)).collect(Collectors.toList()));
    }

    @Override
    public void deleteMeal(PlannerMeal meal) {
        presenter.deleteMeal(meal);
    }

    @Override
    public void requestToGoToDetailsPage(String mealID) {
        presenter.goToDetailsPage(mealID);

    }

    @Override
    public void goToDetailsPage(Meal meal) {
        Intent goToMealDetails = new Intent(this.getContext(), MealDetailsActivity.class);
        meal.setFavorite(true);
        goToMealDetails.putExtra("meal",meal);
        startActivity(goToMealDetails);
    }


}
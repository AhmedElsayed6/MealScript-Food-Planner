package com.example.mealscript.Home.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealscript.Filter.View.FilterActivity;
import com.example.mealscript.Home.Presenters.HomePagePresenter;
import com.example.mealscript.Home.Presenters.HomePagePresenterImpl;
import com.example.mealscript.MealDetails.Views.MealDetailsActivity;
import com.example.mealscript.Model.ContainerMealLists;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;


public class HomePage extends Fragment implements  HomePageInterface {
    RecyclerView parentRecyclerView;
    HomePagePresenter presenter;
    ContainerMealLists containerMealLists;
    HomeRecyclerViewAdapter parentRecyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = HomePagePresenterImpl.getInstance(this, this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parentRecyclerView = view.findViewById(R.id.parentRecyclerView);
        parentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData();
        containerMealLists = new ContainerMealLists();
        parentRecyclerAdapter = new HomeRecyclerViewAdapter(containerMealLists,this);
        parentRecyclerView.setAdapter(parentRecyclerAdapter);
    }


    @Override
    public void setData(ContainerMealLists container) {
        parentRecyclerAdapter.setItems(container);
    }

    @Override
    public void navigateToFilterActivity(String location, String type ) {
            Intent goToMealDetails = new Intent(getContext(), FilterActivity.class );
            goToMealDetails.putExtra("location", location );
            goToMealDetails.putExtra("type", type );
            startActivity(goToMealDetails);
    }

    @Override
    public void navigateToDetailsActivity(Meal meal) {
        Intent goToMealDetails = new Intent(getContext(), MealDetailsActivity.class);
        goToMealDetails.putExtra("meal", meal);
        startActivity(goToMealDetails);
    }


    @Override
    public void addToFavorite(Meal meal) {
        presenter.insertMeal(meal);
    }

    @Override
    public void removeFromFavorite(Meal meal) {
        presenter.deleteMeal(meal);
    }

    @Override
    public void showGuestModeMessage(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDataError(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
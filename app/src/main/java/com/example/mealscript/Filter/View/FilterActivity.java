package com.example.mealscript.Filter.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mealscript.Filter.Presenter.FilterPagePresenter;
import com.example.mealscript.Filter.Presenter.FilterPagePresenterImpl;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;
import com.example.mealscript.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity implements FilterPageInterface {
    RecyclerView filterRecyclerView;
    FilterPagePresenter presenter;
    FilterRecyclerViewAdapter filterRecyclerViewAdapter;
    Toolbar toolBarFilterMeals;
    TextView textViewFilterScreenType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        filterRecyclerView = findViewById(R.id.filterRecyclerView);
        toolBarFilterMeals = findViewById(R.id.toolBarFilterMeals);
        textViewFilterScreenType = findViewById(R.id.textViewFilterScreenType);
        filterRecyclerViewAdapter = new FilterRecyclerViewAdapter(this, new ArrayList<Meal>());
        filterRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        filterRecyclerView.setAdapter(filterRecyclerViewAdapter);
        presenter = new FilterPagePresenterImpl(this, Repository.getInstance(this));
        Intent from = getIntent();
        String location = from.getStringExtra("location");
        String type = from.getStringExtra("type");
        textViewFilterScreenType.setText(location);
        presenter.getMeals(location, type);
        toolBarFilterMeals.setTitle(type);
        setSupportActionBar(toolBarFilterMeals);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolBarFilterMeals.setNavigationOnClickListener(v -> finish());

    }


    @Override
    public void viewData(List<Meal> meals) {
        filterRecyclerViewAdapter.setData(meals);
    }

    @Override
    public void showErrorSnackBar(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addToFavorite(Meal meal) {

        presenter.insertMeal(meal);
    }

    @Override
    public void removeFromFavorite(Meal meal) {
        presenter.deleteMeal(meal);
    }



}
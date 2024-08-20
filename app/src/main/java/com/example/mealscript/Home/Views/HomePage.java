package com.example.mealscript.Home.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealscript.Home.Presenters.HomePagePresenter;
import com.example.mealscript.Model.ContainerMealLists;
import com.example.mealscript.R;


public class HomePage extends Fragment implements  HomePageInterface {
    RecyclerView parentRecyclerView;
    HomePagePresenter presenter;
    ContainerMealLists containerMealLists;
    ParentRecyclerViewAdapter parentRecyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = HomePagePresenter.getInstance(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getData();
        parentRecyclerView = view.findViewById(R.id.parentRecyclerView);
        parentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData();
        containerMealLists = new ContainerMealLists();
        parentRecyclerAdapter = new ParentRecyclerViewAdapter(containerMealLists,this);
        parentRecyclerView.setAdapter(parentRecyclerAdapter);
    }


    @Override
    public void setData(ContainerMealLists container) {
        parentRecyclerAdapter.setItems(container);
    }
}
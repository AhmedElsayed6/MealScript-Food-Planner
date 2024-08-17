package com.example.mealscript.Home.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealscript.Network.MealRemoteDataSource;
import com.example.mealscript.R;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends Fragment {
    CardStackView cardStackView;
    CardStackAdapter adapter;
    CardStackLayoutManager manager;
    Button rewind;
    RecyclerView parentRecyclerView;

    List<CardItem> items;
    private String TAG = "CardViewSucccks";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MealRemoteDataSource mrds = MealRemoteDataSource.getInstance();
        mrds.getRandomMeals();
        parentRecyclerView = view.findViewById(R.id.parentRecyclerView);
        parentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        items = new ArrayList<>();
        items.add(new CardItem("Chocoloate cakeeeeee cakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeee", R.drawable.cake1, "Action 1", R.drawable.fillheart));
        items.add(new CardItem("Card 2", R.drawable.cake2, "Action 2", R.drawable.fillheart));
        items.add(new CardItem("Card 2", R.drawable.cake2, "Action 2", R.drawable.fillheart));
        items.add(new CardItem("Card 2", R.drawable.cake2, "Action 2", R.drawable.fillheart));
        items.add(new CardItem("Card 2", R.drawable.cake2, "Action 2", R.drawable.fillheart));
        items.add(new CardItem("Cupcakeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeee", R.drawable.cake3, "Action 3", R.drawable.fillheart));
    }

    @Override
    public void onResume() {
        super.onResume();
        ParentRecyclerViewAdapter adapter1 = new ParentRecyclerViewAdapter(items,this);
        parentRecyclerView.setAdapter(adapter1);
    }
}
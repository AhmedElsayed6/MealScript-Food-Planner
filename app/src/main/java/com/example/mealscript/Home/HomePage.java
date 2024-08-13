package com.example.mealscript.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealscript.R;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.RewindAnimationSetting;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends Fragment {
    CardStackView cardStackView;
    CardStackAdapter adapter;
    CardStackLayoutManager manager;
    Button rewind;
    RecyclerView rvv;

    List<CardItem> items;
    private String TAG = "CardViewSucccks";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvv = view.findViewById(R.id.rvv);
        rvv.setLayoutManager(new LinearLayoutManager(getContext()));

         items = new ArrayList<>();
        items.add(new CardItem("Chocoloate cakeeeeee cakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeee", R.drawable.cake1, "Action 1", R.drawable.fillheart));
        items.add(new CardItem("Card 2", R.drawable.cake2, "Action 2", R.drawable.fillheart));
        items.add(new CardItem("Card 2", R.drawable.cake2, "Action 2", R.drawable.fillheart));
        items.add(new CardItem("Card 2", R.drawable.cake2, "Action 2", R.drawable.fillheart));
        items.add(new CardItem("Card 2", R.drawable.cake2, "Action 2", R.drawable.fillheart));
        items.add(new CardItem("Cupcakeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeeecakeeeeee", R.drawable.cake3, "Action 3", R.drawable.fillheart));




//        cardStackView = view.findViewById(R.id.card_stack_view);
//        manager = new CardStackLayoutManager(getContext(), new CardStackListener() {
//            @Override
//            public void onCardDragging(Direction direction, float ratio) {
//
//            }
//
//
//            @Override
//            public void onCardSwiped(Direction direction) {
//
//            }
//
//
//            @Override
//            public void onCardRewound() {
//                int position = manager.getTopPosition();
//                if(items.size()-1 != position)
//                    manager.setCanScrollHorizontal(true);
//            }
//
//            @Override
//            public void onCardCanceled() {
//
//            }
//
//            @Override
//            public void onCardAppeared(View view, int position) {
//            if(items.size()-1 == position)
//                manager.setCanScrollHorizontal(false);
//            }
//
//            @Override
//            public void onCardDisappeared(View view, int position) {
//
//            }
//        });
//        manager.setStackFrom(StackFrom.Left);
//        manager.setTranslationInterval(16.0f);
//        manager.setScaleInterval(0.90f);
//        manager.setCanScrollVertical(false);
//        List<Direction> directions = new ArrayList<>();
//        directions.add(Direction.Right);
//        manager.setDirections(directions);
//        cardStackView.setLayoutManager(manager);
//        RewindAnimationSetting setting = new RewindAnimationSetting.Builder().setDirection(Direction.Right).setDuration(Duration.Normal.duration).setInterpolator(new DecelerateInterpolator()).build();
//        manager.setRewindAnimationSetting(setting);
//        manager.setVisibleCount(4);
//        adapter = new CardStackAdapter(items , this);
//
//
//
//
//
//        cardStackView.setAdapter(adapter);

//        ConcatAdapter concatAdapter = new ConcatAdapter(adapter,adapter,adapter
//
//        );

        // Set the ConcatAdapter to the RecyclerView
     //   rvv.setAdapter(concatAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerViewAdapter adapter1 = new RecyclerViewAdapter(items,this);
        rvv.setAdapter(adapter1);
    }
}
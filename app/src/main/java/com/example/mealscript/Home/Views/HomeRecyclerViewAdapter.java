package com.example.mealscript.Home.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mealscript.Model.ContainerMealLists;
import com.example.mealscript.Model.Meal;
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
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private CardStackLayoutManager lmForCardStackView = null;
    private HomePageInterface view;
    private CardStackAdapter cardStackAdapter;
    private ContainerMealLists containerMealLists;

    public HomeRecyclerViewAdapter(ContainerMealLists containerMealLists, HomePageInterface view) {
        this.containerMealLists = containerMealLists;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_home_recyclerview_item, parent, false);
        return new HomeRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<Meal> dailyInspirationsList = containerMealLists.getDailyInspirationsList();
        List<Meal> moreYouMightLikeList = containerMealLists.getMoreYouMightLikeList();


        lmForCardStackView = new CardStackLayoutManager(holder.cardStackViewHomeScreen.getContext(), new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {

            }


            @Override
            public void onCardSwiped(Direction direction) {

            }


            @Override
            public void onCardRewound() {
                int position = lmForCardStackView.getTopPosition();
                if (dailyInspirationsList.size() - 1 != position)
                    lmForCardStackView.setCanScrollHorizontal(true);
            }

            @Override
            public void onCardCanceled() {

            }

            @Override
            public void onCardAppeared(View view, int position) {
                if (dailyInspirationsList.size() - 1 == position)
                    lmForCardStackView.setCanScrollHorizontal(false);
            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        });
        lmForCardStackView.setStackFrom(StackFrom.Left);
        lmForCardStackView.setTranslationInterval(16.0f);
        lmForCardStackView.setScaleInterval(0.90f);
        lmForCardStackView.setCanScrollVertical(false);
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.Right);
        lmForCardStackView.setDirections(directions);
        RewindAnimationSetting setting = new RewindAnimationSetting.Builder().setDirection(Direction.Right).setDuration(Duration.Normal.duration).setInterpolator(new DecelerateInterpolator()).build();
        lmForCardStackView.setRewindAnimationSetting(setting);
        lmForCardStackView.setVisibleCount(4);
        cardStackAdapter = new CardStackAdapter(view, dailyInspirationsList, holder.cardStackViewHomeScreen);
        holder.cardStackViewHomeScreen.setLayoutManager(lmForCardStackView);
        holder.cardStackViewHomeScreen.setAdapter(cardStackAdapter);

        //Categories
        LinearLayoutManager llmForCategories = new LinearLayoutManager(holder.recyclerViewHomeScreenCategories.getContext());
        llmForCategories.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerViewHomeScreenCategories.setLayoutManager(llmForCategories);
        // Countries
        LinearLayoutManager llmForCountries = new LinearLayoutManager(holder.recyclerViewHomeScreenCountries.getContext());
        llmForCountries.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerViewHomeScreenCountries.setLayoutManager(llmForCountries);
        //Desserts
        LinearLayoutManager llmForDesserts = new LinearLayoutManager(holder.recyclerViewHomeScreenSavedDesserts.getContext());
        llmForDesserts.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerViewHomeScreenSavedDesserts.setLayoutManager(llmForDesserts);
        // More you might like
        StaggeredGridLayoutManager staggeredGridLayoutManagerForMoreYouMightLike = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        holder.recyclerViewHomeScreenMYML.setLayoutManager(staggeredGridLayoutManagerForMoreYouMightLike);


        CategoriesHorizontalAdapter horizontalAdapterForCategories = new CategoriesHorizontalAdapter(view, containerMealLists.getCategoryList());
        CountriesHorizontalAdapter horizontalAdapterForCountries = new CountriesHorizontalAdapter(view, containerMealLists.getAreasList());
        ChildHorizontalRecyclerViewAdapter horizontalAdapterForDesserts = new ChildHorizontalRecyclerViewAdapter(view, containerMealLists.getDessertsList());
        ChildVerticalReyclerViewAdapter horizontalRecyclerForMYML = new ChildVerticalReyclerViewAdapter(view, moreYouMightLikeList);

        holder.recyclerViewHomeScreenCategories.setAdapter(horizontalAdapterForCategories);
        holder.recyclerViewHomeScreenSavedDesserts.setAdapter(horizontalAdapterForDesserts);
        holder.recyclerViewHomeScreenMYML.setAdapter(horizontalRecyclerForMYML);
        holder.recyclerViewHomeScreenCountries.setAdapter(horizontalAdapterForCountries);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDailyInspHomeScreen;
        CardStackView cardStackViewHomeScreen;
        RecyclerView recyclerViewHomeScreenCategories, recyclerViewHomeScreenSavedDesserts, recyclerViewHomeScreenMYML,
                recyclerViewHomeScreenCountries;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDailyInspHomeScreen = itemView.findViewById(R.id.txtDailyInspHomeScreen);
            cardStackViewHomeScreen = itemView.findViewById(R.id.cardStackViewHomeScreen);
            recyclerViewHomeScreenCategories = itemView.findViewById(R.id.recyclerViewHomeScreenCategories);
            recyclerViewHomeScreenSavedDesserts = itemView.findViewById(R.id.recyclerViewHomeScreenSavedDesserts);
            recyclerViewHomeScreenMYML = itemView.findViewById(R.id.recyclerViewHomeScreenMYML);
            recyclerViewHomeScreenCountries = itemView.findViewById(R.id.recyclerViewHomeScreenCountries);
        }
    }


    public void setItems(ContainerMealLists container) {
        containerMealLists = container;
        notifyDataSetChanged();
    }
}

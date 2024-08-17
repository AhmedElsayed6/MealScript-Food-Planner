package com.example.mealscript.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ParentRecyclerViewAdapter extends RecyclerView.Adapter<ParentRecyclerViewAdapter.ViewHolder>  {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    CardStackLayoutManager  manager = null ;
    RecyclerView rv ;
    private CompositeDisposable disposable = new CompositeDisposable();

    public ParentRecyclerViewAdapter(List<CardItem> items , HomePage page ) {
        this.items = items;
        this.page = page;
    }
    HomePage page ;
    private List<CardItem> items;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_item, parent, false);
        return new ParentRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    CardItem item  = items.get(position);

        manager = new CardStackLayoutManager(holder.cardStackViewHomeScreen.getContext(), new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {

            }


            @Override
            public void onCardSwiped(Direction direction) {

            }


            @Override
            public void onCardRewound() {
                int position = manager.getTopPosition();
                if(items.size()-1 != position)
                    manager.setCanScrollHorizontal(true);
            }

            @Override
            public void onCardCanceled() {

            }

            @Override
            public void onCardAppeared(View view, int position) {
                if(items.size()-1 == position)
                    manager.setCanScrollHorizontal(false);
            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        });
        manager.setStackFrom(StackFrom.Left);
        manager.setTranslationInterval(16.0f);
        manager.setScaleInterval(0.90f);
        manager.setCanScrollVertical(false);
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.Right);
        manager.setDirections(directions);
        RewindAnimationSetting setting = new RewindAnimationSetting.Builder().setDirection(Direction.Right).setDuration(Duration.Normal.duration).setInterpolator(new DecelerateInterpolator()).build();
        manager.setRewindAnimationSetting(setting);
        manager.setVisibleCount(4);
        CardStackAdapter adapter = new CardStackAdapter(items , holder.cardStackViewHomeScreen);
        holder.cardStackViewHomeScreen.setLayoutManager(manager);
        holder.cardStackViewHomeScreen.setAdapter(adapter);


        LinearLayoutManager llmForInspirationMeals = new LinearLayoutManager(holder.recyclerViewHomeScreenSavedMeals.getContext());
        llmForInspirationMeals.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerViewHomeScreenSavedMeals.setLayoutManager(llmForInspirationMeals);

        LinearLayoutManager llmForDesserts = new LinearLayoutManager(holder.recyclerViewHomeScreenSavedDesserts.getContext());
        llmForDesserts.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerViewHomeScreenSavedDesserts.setLayoutManager(llmForDesserts);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        holder.recyclerViewHomeScreenMYML.setLayoutManager(staggeredGridLayoutManager);

        ChildHorizontalRecyclerViewAdapter horizontalAdapterForSavedMeals = new ChildHorizontalRecyclerViewAdapter(items);
        ChildHorizontalRecyclerViewAdapter horizontalAdapterForDesserts = new ChildHorizontalRecyclerViewAdapter(items);
        ChildVerticalReyclerViewAdapter horizontalRecyclerForMYML = new ChildVerticalReyclerViewAdapter(items);

        holder.recyclerViewHomeScreenSavedMeals.setAdapter(horizontalAdapterForSavedMeals);
        holder.recyclerViewHomeScreenSavedDesserts.setAdapter(horizontalAdapterForDesserts);
        holder.recyclerViewHomeScreenMYML.setAdapter(horizontalRecyclerForMYML);
        autoScroll(llmForDesserts,holder.recyclerViewHomeScreenSavedMeals);
        autoScroll(llmForDesserts,holder.recyclerViewHomeScreenSavedDesserts);


    }

    private void autoScroll(LinearLayoutManager llm , RecyclerView rv ){
        int randomInt = new Random().nextInt(4) + 1;
        Observable.interval(randomInt, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    int currentPosition = llm.findFirstVisibleItemPosition();
                    int totalItemCount = rv.getAdapter().getItemCount();

                    if (currentPosition < totalItemCount - 1) {
                        rv.smoothScrollToPosition(currentPosition + 1);
                    } else {
                        rv.smoothScrollToPosition(0); // Loop back to the start
                    }
                });
    }


    @Override
    public int getItemCount() {
        return  1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDailyInspHomeScreen;
        CardStackView cardStackViewHomeScreen;
        RecyclerView recyclerViewHomeScreenSavedMeals , recyclerViewHomeScreenSavedDesserts , recyclerViewHomeScreenMYML ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             txtDailyInspHomeScreen = itemView.findViewById(R.id.txtDailyInspHomeScreen);
             cardStackViewHomeScreen = itemView.findViewById(R.id.cardStackViewHomeScreen);
             recyclerViewHomeScreenSavedMeals = itemView.findViewById(R.id.recyclerViewHomeScreenSavedMeals);
             recyclerViewHomeScreenSavedDesserts = itemView.findViewById(R.id.recyclerViewHomeScreenSavedDesserts);
            recyclerViewHomeScreenMYML= itemView.findViewById(R.id.recyclerViewHomeScreenMYML);
        }
    }
}

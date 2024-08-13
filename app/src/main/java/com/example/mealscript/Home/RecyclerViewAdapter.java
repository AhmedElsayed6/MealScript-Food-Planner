package com.example.mealscript.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    CardStackLayoutManager  manager = null ;
    public RecyclerViewAdapter(List<CardItem> items ,     HomePage page ) {
        this.items = items;
        this.page = page;
    }
    HomePage page ;
    private List<CardItem> items;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_item, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    CardItem item  = items.get(position);

        manager = new CardStackLayoutManager(holder.csv.getContext(), new CardStackListener() {
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
        CardStackAdapter adapter = new CardStackAdapter(items , holder.csv );
        holder.csv.setLayoutManager(manager);
        holder.csv.setAdapter(adapter);
        holder.csv.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return  1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardStackView csv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             textView = itemView.findViewById(R.id.textView111);
             csv = itemView.findViewById(R.id.card1);

        }
    }
}

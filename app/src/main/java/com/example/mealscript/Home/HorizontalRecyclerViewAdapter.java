package com.example.mealscript.Home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealscript.R;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.List;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.ViewHolder> {


    private List<CardItem> items;

    public HorizontalRecyclerViewAdapter(List<CardItem> items ) {
        this.items = items;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View row = inflater.inflate(R.layout.meal_cards_for_normalrvs, recycler, false);
        return new ViewHolder(row);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    // 0
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardItem item = items.get(position);
        holder.textView.setText(item.getText());
        holder.imageView.setImageResource(item.getImageResId());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        Button button;
        ImageButton addtofav , undo;
        ConstraintLayout cardHolder ;


        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textViewCardMealName1);
            imageView = view.findViewById(R.id.imageViewCardMeal1);
            button = view.findViewById(R.id.btnCardViewAddToPlanner1);
            cardHolder = view.findViewById(R.id.cardHolderCadView1);
        }

    }


}

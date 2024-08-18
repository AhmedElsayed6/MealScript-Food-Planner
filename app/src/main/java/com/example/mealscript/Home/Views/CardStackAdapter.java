package com.example.mealscript.Home.Views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealscript.MealDetails.Views.MealDetailsActivity;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<Meal> items;
    CardStackView page;
    private Context context;

    public CardStackAdapter(List<Meal> items, CardStackView page) {
        this.items = items;
        this.page = page;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_cards_for_stackview, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal item = items.get(position);
        holder.getTextViewCardMealName().setText(item.getStrMeal());
        Glide.with(context).load(item.getStrMealThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)).into(holder.getImageViewCardMeal());

        holder.btnCardViewUndo.setOnClickListener((e) -> {
            page.rewind();
        });
        holder.cardHolderCadView.setOnClickListener((e) -> {
            Intent goToMealDetails = new Intent(context, MealDetailsActivity.class);
            goToMealDetails.putExtra("meal", item);
            context.startActivity(goToMealDetails);
        });
        // You can set click listeners here
        holder.getBtnCardViewAddToPlanner().setOnClickListener(v -> {
            // Handle button click
        });

        holder.getBtnCardViewAddToFav().setOnClickListener(v -> {
            // Handle image button click
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCardMealName;
        private ImageView imageViewCardMeal;
        private Button btnCardViewAddToPlanner;
        private ImageButton btnCardViewAddToFav, btnCardViewUndo;
        private ConstraintLayout cardHolderCadView;


        ViewHolder(View view) {
            super(view);
            textViewCardMealName = view.findViewById(R.id.textViewCardMealName);
            imageViewCardMeal = view.findViewById(R.id.imageViewCardMeal);
            btnCardViewAddToPlanner = view.findViewById(R.id.btnCardViewAddToPlanner);
            btnCardViewAddToFav = view.findViewById(R.id.btnCardViewAddToFav);
            btnCardViewUndo = view.findViewById(R.id.btnCardViewUndo);
            cardHolderCadView = view.findViewById(R.id.cardHolderCadView);
        }


        public TextView getTextViewCardMealName() {
            return textViewCardMealName;
        }

        public ImageView getImageViewCardMeal() {
            return imageViewCardMeal;
        }

        public Button getBtnCardViewAddToPlanner() {
            return btnCardViewAddToPlanner;
        }

        public ImageButton getBtnCardViewAddToFav() {
            return btnCardViewAddToFav;
        }

        public ImageButton getBtnCardViewUndo() {
            return btnCardViewUndo;
        }

        public ConstraintLayout getCardHolderCadView() {
            return cardHolderCadView;
        }

    }

    public void setItems(List<Meal> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
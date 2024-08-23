package com.example.mealscript.Search.Views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.mealscript.Planner.View.PlannerDialog;
import com.example.mealscript.R;

import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {

    private List<Meal> items;
    private Context context;
    private SearchPageInterface view;
    public SearchRecyclerViewAdapter( SearchPageInterface view , List<Meal> items) {
        this.items = items;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        context = recycler.getContext();
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View row = inflater.inflate(R.layout.meal_cards_for_verticalrv, recycler, false);
        return new ViewHolder(row);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal item = items.get(position);
        if(item.isFavorite())
            holder.getBtnCardViewAddToFavVZ().setImageResource(R.drawable.fillheart);
        else
            holder.getBtnCardViewAddToFavVZ().setImageResource(R.drawable.outlineheart);
        holder.getBtnCardViewAddToFavVZ().setOnClickListener(v -> {
            if(item.isFavorite()){
                holder.getBtnCardViewAddToFavVZ().setImageResource(R.drawable.outlineheart);
                view.removeFromFavorite(item);
                item.setFavorite(false);
            }
            else{
                view.addToFavorite(items.get(position));
                items.get(position).setFavorite(true);
                holder.getBtnCardViewAddToFavVZ().setImageResource(R.drawable.fillheart);
            }

        });
        holder.getBtnCardViewAddToPlannerVZ().setOnClickListener(v -> {
            new PlannerDialog(context,item.getIdMeal(),item.getStrMeal(),item.getStrMealThumb()).showDialog();
        });
        holder.getCardHolderCadViewVZ().setOnClickListener((e)->{
            Intent goToMealDetails = new Intent(context, MealDetailsActivity.class);
            goToMealDetails.putExtra("meal", item);
            context.startActivity(goToMealDetails);
        });
        holder.getTextViewCardMealNameVZ().setText(item.getStrMeal());
        Glide.with(context).load(item.getStrMealThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.png_food_placeholder)
                .error(R.drawable.png_food_error)).into(holder.getImageViewCardMealVZ());


    }
    public void setData( List<Meal> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView getTextViewCardMealNameVZ() {
            return textViewCardMealNameVZ;
        }

        public ImageView getImageViewCardMealVZ() {
            return imageViewCardMealVZ;
        }

        public Button getBtnCardViewAddToPlannerVZ() {
            return btnCardViewAddToPlannerVZ;
        }

        public ImageButton getBtnCardViewAddToFavVZ() {
            return btnCardViewAddToFavVZ;
        }

        public ConstraintLayout getCardHolderCadViewVZ() {
            return cardHolderCadViewVZ;
        }

        private TextView textViewCardMealNameVZ;
        private ImageView imageViewCardMealVZ;
        private Button btnCardViewAddToPlannerVZ;
        private ImageButton btnCardViewAddToFavVZ;
        private ConstraintLayout cardHolderCadViewVZ;

        ViewHolder(View view) {
            super(view);
            textViewCardMealNameVZ = view.findViewById(R.id.textViewCardMealNameVZ);
            imageViewCardMealVZ = view.findViewById(R.id.imageViewCardMealVZ);
            btnCardViewAddToFavVZ = view.findViewById(R.id.btnCardViewAddToFavVZ);
            btnCardViewAddToPlannerVZ = view.findViewById(R.id.btnCardViewAddToPlannerVZ);
            cardHolderCadViewVZ = view.findViewById(R.id.cardHolderCadViewVZ);
        }

    }


}

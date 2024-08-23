package com.example.mealscript.Favorite.View;

import android.content.Context;
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
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Planner.View.PlannerDialog;
import com.example.mealscript.R;

import java.util.List;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ViewHolder> {

    private FavoriteActivityInterface view;
    private List<FavoriteMeal> items;
    private Context context;

    public FavoritesRecyclerViewAdapter(FavoriteActivityInterface view, List<FavoriteMeal> items) {
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
        FavoriteMeal meal = items.get(position);
        holder.getBtnCardViewAddToFavVZ().setImageResource(R.drawable.fillheart);
        holder.getBtnCardViewAddToFavVZ().setOnClickListener(v -> {
                view.deleteMeal(meal);
                items.remove(position);
                notifyDataSetChanged();
        });
        holder.getCardHolderCadViewVZ().setOnClickListener((e) -> {
            view.requestToGoToDetailsPage(meal.getIdMeal());
        });
        holder.getBtnCardViewAddToPlannerVZ().setOnClickListener(v -> {
            new PlannerDialog(context,meal.getIdMeal(),meal.getStrMeal(),meal.getStrMealThumb()).showDialog();
        });
        holder.getTextViewCardMealNameVZ().setText(items.get(position).getStrMeal());
        Glide.with(context).load(items.get(position).getStrMealThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.png_food_placeholder)
                .error(R.drawable.png_food_error)).into(holder.getImageViewCardMealVZ());
    }
    public void setData( List<FavoriteMeal> items){
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
            btnCardViewAddToPlannerVZ = view.findViewById(R.id.btnCardViewAddToPlannerVZ);
            cardHolderCadViewVZ = view.findViewById(R.id.cardHolderCadViewVZ);
            btnCardViewAddToFavVZ = view.findViewById(R.id.btnCardViewAddToFavVZ);
        }

    }


}

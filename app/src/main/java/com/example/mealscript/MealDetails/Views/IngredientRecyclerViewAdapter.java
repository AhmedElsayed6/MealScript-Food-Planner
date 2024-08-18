package com.example.mealscript.MealDetails.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;

import java.util.List;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {
    private List<String> ingredientsNames;
    private List<String> measures;
    private Context context;
    private static final String ImageUrl ="https://www.themealdb.com/images/ingredients/";
    private static final String ImageUrlEnd = "-small.png";

    public IngredientRecyclerViewAdapter(Meal meal) {
        ingredientsNames = meal.getIngredients();
        measures = meal.getMeasures();
    }

    @NonNull
    @Override
    public IngredientRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        context = recycler.getContext();
        View row = inflater.inflate(R.layout.ingredient_item_view, recycler, false);
        return new IngredientRecyclerViewAdapter.ViewHolder(row);
    }

    @Override
    public int getItemCount() {
        return ingredientsNames.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextViewMealDetailsIngredientTitle().setText(ingredientsNames.get(position));
        holder.getTextViewMealDetailsIngredientMeasure().setText(measures.get(position));
        Glide.with(context).load(ImageUrl+ingredientsNames.get(position)+ImageUrlEnd).apply(new RequestOptions()
                .placeholder(R.drawable.ingradient)
                .override(80, 80)
                .error(R.drawable.ic_launcher_foreground)).into(holder.getImageViewMealDetailsIngredientImage());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewMealDetailsIngredientTitle, textViewMealDetailsIngredientMeasure;
        private ImageView imageViewMealDetailsIngredientImage;

        public TextView getTextViewMealDetailsIngredientTitle() {
            return textViewMealDetailsIngredientTitle;
        }

        public TextView getTextViewMealDetailsIngredientMeasure() {
            return textViewMealDetailsIngredientMeasure;
        }

        public ImageView getImageViewMealDetailsIngredientImage() {
            return imageViewMealDetailsIngredientImage;
        }

        ViewHolder(View view) {
            super(view);
            textViewMealDetailsIngredientTitle = view.findViewById(R.id.textViewMealDetailsIngredientTitle);
            textViewMealDetailsIngredientMeasure = view.findViewById(R.id.textViewMealDetailsIngredientMeasure);
            imageViewMealDetailsIngredientImage = view.findViewById(R.id.imageViewMealDetailsIngredientImage);

        }

    }

}
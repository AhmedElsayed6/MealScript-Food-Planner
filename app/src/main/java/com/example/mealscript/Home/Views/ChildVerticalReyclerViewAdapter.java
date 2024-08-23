package com.example.mealscript.Home.Views;

import android.content.Context;
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
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Planner.View.PlannerDialog;
import com.example.mealscript.R;

import java.util.List;

import retrofit2.http.POST;

public class ChildVerticalReyclerViewAdapter extends RecyclerView.Adapter<ChildVerticalReyclerViewAdapter.ViewHolder> {

    private HomePageInterface view;
    private List<Meal> items;
    private Context context;

    public ChildVerticalReyclerViewAdapter(HomePageInterface view, List<Meal> items) {
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
             holder.getCardHolderCadViewVZ().setOnClickListener((e) -> {
                    view.navigateToDetailsActivity(item);
                });
        holder.getTextViewCardMealNameVZ().setText(item.getStrMeal());
        Glide.with(context).load(item.getStrMealThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)).into(holder.getImageViewCardMealVZ());


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCardMealNameVZ;
        private ImageView imageViewCardMealVZ;
        private Button btnCardViewAddToPlannerVZ;
        private ImageButton btnCardViewAddToFavVZ;
        private ConstraintLayout cardHolderCadViewVZ;
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




        ViewHolder(View view) {
            super(view);
            textViewCardMealNameVZ = view.findViewById(R.id.textViewCardMealNameVZ);
            btnCardViewAddToFavVZ = view.findViewById(R.id.btnCardViewAddToFavVZ);
            imageViewCardMealVZ = view.findViewById(R.id.imageViewCardMealVZ);
            btnCardViewAddToPlannerVZ = view.findViewById(R.id.btnCardViewAddToPlannerVZ);
            cardHolderCadViewVZ = view.findViewById(R.id.cardHolderCadViewVZ);
        }

    }


}

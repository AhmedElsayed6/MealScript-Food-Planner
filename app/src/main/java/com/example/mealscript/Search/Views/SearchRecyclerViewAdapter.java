package com.example.mealscript.Search.Views;

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
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;

import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {


    private List<Meal> items;
    private Context context;

    public SearchRecyclerViewAdapter(List<Meal> items) {
        this.items = items;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        context = recycler.getContext();
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View row = inflater.inflate(R.layout.meal_cards_for_horizontalrvs, recycler, false);
        return new ViewHolder(row);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal item = items.get(position);
        holder.getTextViewCardMealNameHZ().setText(item.getStrMeal());
        Glide.with(context).load(item.getStrMealThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)).into(holder.getImageViewCardMealHZ());


    }
    public void setData( List<Meal> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCardMealNameHZ;
        private ImageView imageViewCardMealHZ;
        private Button btnCardViewAddToPlannerHZ;
        private ImageButton btnCardViewAddToFavHZ;
        private ConstraintLayout cardHolderCadViewHZ;


        public TextView getTextViewCardMealNameHZ() {
            return textViewCardMealNameHZ;
        }

        public ImageView getImageViewCardMealHZ() {
            return imageViewCardMealHZ;
        }

        public Button getBtnCardViewAddToPlannerHZ() {
            return btnCardViewAddToPlannerHZ;
        }

        public ImageButton getBtnCardViewAddToFavHZ() {
            return btnCardViewAddToFavHZ;
        }

        public ConstraintLayout getCardHolderCadViewHZ() {
            return cardHolderCadViewHZ;
        }


        ViewHolder(View view) {
            super(view);
            textViewCardMealNameHZ = view.findViewById(R.id.textViewCardMealNameHZ);
            imageViewCardMealHZ = view.findViewById(R.id.imageViewCardMealHZ);
            btnCardViewAddToFavHZ = view.findViewById(R.id.btnCardViewAddToFavHZ);
            btnCardViewAddToPlannerHZ = view.findViewById(R.id.btnCardViewAddToPlannerHZ);
            cardHolderCadViewHZ = view.findViewById(R.id.cardHolderCadViewHZ);
        }

    }


}

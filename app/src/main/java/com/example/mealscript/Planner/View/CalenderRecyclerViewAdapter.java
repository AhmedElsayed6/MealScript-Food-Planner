package com.example.mealscript.Planner.View;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.R;
import java.util.List;

public class CalenderRecyclerViewAdapter extends RecyclerView.Adapter<CalenderRecyclerViewAdapter.ViewHolder> {

    private CalenderPageInterface view;
    private List<PlannerMeal> plannerMealList;
    private Context context;

    public CalenderRecyclerViewAdapter(CalenderPageInterface view, List<PlannerMeal> plannerMealList) {
        this.plannerMealList = plannerMealList;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        context = recycler.getContext();
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View row = inflater.inflate(R.layout.meal_cards_for_planner, recycler, false);
        return new ViewHolder(row);
    }


    @Override
    public int getItemCount() {
        return plannerMealList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlannerMeal meal = plannerMealList.get(position);
        holder.getCardHolderCadViewPlanner().setOnClickListener((e) -> {
             view.requestToGoToDetailsPage(meal.getIdMeal());
        });
        holder.getBtnCardRemoveFromPlanner().setOnClickListener((e) -> {
            view.deleteMeal(meal);
        });
        holder.getTextViewCardMealNamePlanner().setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.png_food_placeholder)
                .error(R.drawable.png_food_error)).into(holder.getImageViewCardMealPlanner());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCardMealNamePlanner;
        private ImageView imageViewCardMealPlanner;
        private ConstraintLayout cardHolderCadViewPlanner;
        private ImageButton btnCardRemoveFromPlanner;


        public TextView getTextViewCardMealNamePlanner() {
            return textViewCardMealNamePlanner;
        }

        public void setTextViewCardMealNamePlanner(TextView textViewCardMealNamePlanner) {
            this.textViewCardMealNamePlanner = textViewCardMealNamePlanner;
        }

        public ImageView getImageViewCardMealPlanner() {
            return imageViewCardMealPlanner;
        }

        public void setImageViewCardMealPlanner(ImageView imageViewCardMealPlanner) {
            this.imageViewCardMealPlanner = imageViewCardMealPlanner;
        }

        public ConstraintLayout getCardHolderCadViewPlanner() {
            return cardHolderCadViewPlanner;
        }

        public void setCardHolderCadViewPlanner(ConstraintLayout cardHolderCadViewPlanner) {
            this.cardHolderCadViewPlanner = cardHolderCadViewPlanner;
        }

        public ImageButton getBtnCardRemoveFromPlanner() {
            return btnCardRemoveFromPlanner;
        }

        public void setBtnCardRemoveFromPlanner(ImageButton btnCardRemoveFromPlanner) {
            this.btnCardRemoveFromPlanner = btnCardRemoveFromPlanner;
        }

        ViewHolder(View view) {
            super(view);
            cardHolderCadViewPlanner = view.findViewById(R.id.cardHolderCadViewPlanner);
            imageViewCardMealPlanner = view.findViewById(R.id.imageViewCardMealPlanner);
            btnCardRemoveFromPlanner = view.findViewById(R.id.btnCardRemoveFromPlanner);
            textViewCardMealNamePlanner = view.findViewById(R.id.textViewCardMealNamePlanner);
        }

    }
    public void setItems(List<PlannerMeal> items){
        this.plannerMealList = items;
        notifyDataSetChanged();
    }

}

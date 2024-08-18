package com.example.mealscript.Home.Views;

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
import com.example.mealscript.Model.ContainerMealLists;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;

import java.util.List;

public class ChildVerticalReyclerViewAdapter extends RecyclerView.Adapter<ChildVerticalReyclerViewAdapter.ViewHolder> {


    private List<Meal> items;
    private Context context;
    public ChildVerticalReyclerViewAdapter(List<Meal> items ) {
        this.items = items;

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
        holder.textView.setText(item.getStrMeal());
        Glide.with(context).load(item.getStrMealThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)).into(holder.imageView);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        Button button;
        ImageButton addtofav , undo;
        ConstraintLayout cardHolder ;


        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textViewCardMealNameVZ);
            imageView = view.findViewById(R.id.imageViewCardMealVZ);
            button = view.findViewById(R.id.btnCardViewAddToPlannerVZ);
            cardHolder = view.findViewById(R.id.cardHolderCadViewVZ);
        }

    }


}

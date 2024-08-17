package com.example.mealscript.Home;

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

import com.example.mealscript.R;

import java.util.List;

public class ChildVerticalReyclerViewAdapter extends RecyclerView.Adapter<ChildVerticalReyclerViewAdapter.ViewHolder> {


    private List<CardItem> items;

    public ChildVerticalReyclerViewAdapter(List<CardItem> items ) {
        this.items = items;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {

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
            textView = view.findViewById(R.id.textViewCardMealNameVZ);
            imageView = view.findViewById(R.id.imageViewCardMealVZ);
            button = view.findViewById(R.id.btnCardViewAddToPlannerVZ);
            cardHolder = view.findViewById(R.id.cardHolderCadViewVZ);
        }

    }


}

package com.example.mealscript.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealscript.R;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<CardItem> items;
    CardStackView page ;

    public CardStackAdapter(List<CardItem> items , CardStackView page ) {
        this.items = items;
        this.page = page;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_cards_for_stackview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardItem item = items.get(position);
        holder.textView.setText(item.getText());
        holder.imageView.setImageResource(item.getImageResId());
        holder.undo.setOnClickListener((e)->{page.rewind();});
        holder.cardHolder.setOnClickListener((e)->{
            Toast.makeText(page.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
        });
        // You can set click listeners here
        holder.button.setOnClickListener(v -> {
            // Handle button click
        });

        holder.addtofav.setOnClickListener(v -> {
            // Handle image button click
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        Button button;
        ImageButton addtofav , undo;
        ConstraintLayout cardHolder ;
        

        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textViewCardMealName);
            imageView = view.findViewById(R.id.imageViewCardMeal);
            button = view.findViewById(R.id.btnCardViewAddToPlanner);
            addtofav = view.findViewById(R.id.btnCardViewAddToFav);
            undo = view.findViewById(R.id.btnCardViewUndo);
            cardHolder = view.findViewById(R.id.cardHolderCadView);
        }
    }
}
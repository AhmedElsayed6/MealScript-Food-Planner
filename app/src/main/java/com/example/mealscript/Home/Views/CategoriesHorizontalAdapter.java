package com.example.mealscript.Home.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealscript.Model.Category;
import com.example.mealscript.R;

import java.util.List;

public class CategoriesHorizontalAdapter extends RecyclerView.Adapter<CategoriesHorizontalAdapter.ViewHolder> {


    List<Category> categoryList;
    private Context context;

    public CategoriesHorizontalAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        context = recycler.getContext();
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View row = inflater.inflate(R.layout.meal_cards_for_candc, recycler, false);
        return new ViewHolder(row);
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getTextViewCardCCname().setText(categoryList.get(position).getStrCategory());
        Glide.with(context).load(categoryList.get(position).getStrCategoryThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)).into(holder.getImageViewCardCC());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCardCCname;
        private ImageView imageViewCardCC;
        private LinearLayout cardHolderCadViewCC;

        public LinearLayout getCardHolderCadViewCC() {
            return cardHolderCadViewCC;
        }

        public ImageView getImageViewCardCC() {
            return imageViewCardCC;
        }

        public TextView getTextViewCardCCname() {
            return textViewCardCCname;
        }


        ViewHolder(View view) {
            super(view);
            cardHolderCadViewCC = view.findViewById(R.id.cardHolderCadViewCC);
            imageViewCardCC = view.findViewById(R.id.imageViewCardCC);
            textViewCardCCname = view.findViewById(R.id.textViewCardCCname);
        }

    }


}

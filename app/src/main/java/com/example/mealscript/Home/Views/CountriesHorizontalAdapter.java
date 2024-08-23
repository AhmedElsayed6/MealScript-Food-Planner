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
import com.example.mealscript.Model.CuisineAreaEnum;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;

import java.util.List;


public class CountriesHorizontalAdapter extends RecyclerView.Adapter<CountriesHorizontalAdapter.ViewHolder> {

    private HomePageInterface view;
    List<Meal> areasList;
    private Context context;
    private static final String ImageUrl = "https://www.themealdb.com/images/ingredients/";
    private static final String ImageUrlEnd = "-small.png";

    public CountriesHorizontalAdapter(HomePageInterface view, List<Meal> areasList) {
        this.areasList = areasList;
        this.view = view;
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
        return areasList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String country = areasList.get(position).getStrArea();
        holder.getCardHolderCadViewCC().setOnClickListener((e) -> {
            view.navigateToFilterActivity(areasList.get(position).getStrArea(), "Country");
        });
        if (country.compareToIgnoreCase("unknown") != 0) {
            holder.getTextViewCardCCname().setText(areasList.get(position).getStrArea());
            Glide.with(context).load("https://flagsapi.com/" + CuisineAreaEnum.getCountryCodeByArea(areasList.get(position).getStrArea()) + "/shiny/64.png").apply(new RequestOptions()
                    .placeholder(R.drawable.ingradient)
                    .error(R.drawable.png_food_error)).into(holder.getImageViewCardCC());
        }


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

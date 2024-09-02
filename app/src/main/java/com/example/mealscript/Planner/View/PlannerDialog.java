package com.example.mealscript.Planner.View;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealscript.Planner.Presenter.PlannerDialogPresenterImpl;
import com.example.mealscript.R;
import com.example.mealscript.Repository.Repository;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class PlannerDialog implements PlannerDialogInterface {
    private String mealId, mealName, mealImage;
    private Context context;
    private PlannerDialogPresenterImpl presenter;


    public PlannerDialog(Context context, String mealId, String mealName, String mealImage) {
        this.context = context;
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealImage = mealImage;
        this.presenter = new PlannerDialogPresenterImpl(this, Repository.getInstance(context));
    }

    public void showDialog() {

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.planner_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        ImageView imageViewMealPlannerCard = dialogView.findViewById(R.id.imageViewMealPlannerCard);
        TextView textViewMealNamePlannerCard = dialogView.findViewById(R.id.textViewMealNamePlannerCard);
        Button btnAddToPlanner = dialogView.findViewById(R.id.btnAddToPlanner);
        Button btnCancelPlannerCard = dialogView.findViewById(R.id.btnCancelPlannerCard);
        ChipGroup chipGroundForPlannerDialog = dialogView.findViewById(R.id.chipGroundForPlannerDialog);
        textViewMealNamePlannerCard.setText(mealName);
        Glide.with(context).load(mealImage).apply(new RequestOptions()
                .placeholder(R.drawable.png_food_placeholder)
                .error(R.drawable.png_food_error)).into(imageViewMealPlannerCard);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btnCancelPlannerCard.setOnClickListener((e) -> alertDialog.dismiss());

        btnAddToPlanner.setOnClickListener((e) -> {
            Chip selectedChip = dialogView.findViewById(chipGroundForPlannerDialog.getCheckedChipId());
            String selectedText = selectedChip != null ? selectedChip.getText().toString() : "";
            presenter.insertMeal(mealId, mealName, mealImage, selectedText );
            alertDialog.dismiss();
        });



    }
    public void showError(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
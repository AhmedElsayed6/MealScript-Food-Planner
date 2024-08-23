package com.example.mealscript.Planner.View;



import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;

public class PlannerDialog {
    private String mealId, mealName, mealImage;
    private Context context;
    public PlannerDialog(Context context , String mealId, String mealName, String mealImage ) {
        this.context = context;
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealImage = mealImage;
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

        textViewMealNamePlannerCard.setText(mealName);
        Glide.with(context).load(mealImage).apply(new RequestOptions()
                .placeholder(R.drawable.png_food_placeholder)
                .error(R.drawable.png_food_error)).into(imageViewMealPlannerCard);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
//
//        dialogButton.setOnClickListener(view -> {
//            String input = dialogInput.getText().toString().trim();
//            if (!input.isEmpty()) {
//                callback.onSubmit(input);
//                alertDialog.dismiss();
//            } else {
//                dialogInput.setError("This field cannot be empty");
//            }
//        });


    }

    public interface DialogCallback {
        void onSubmit(String input);
    }


}
package com.example.mealscript.MealDetails.Views;

import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealscript.MealDetails.Presenter.MealDetailsActivityPresenter;
import com.example.mealscript.MealDetails.Presenter.MealDetailsActivityPresenterImpl;
import com.example.mealscript.Model.CuisineAreaEnum;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Network.InternetStatesBroadcastReceiver;
import com.example.mealscript.Network.NetworkObserver;
import com.example.mealscript.Planner.View.PlannerDialog;
import com.example.mealscript.R;
import com.example.mealscript.Repository.Repository;
import com.google.android.material.snackbar.Snackbar;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsActivityInterface , NetworkObserver {
    LinearLayoutManager llmForIngredients;
    RecyclerView recyclerViewMealDetailsIngredients;
    ImageView imageViewMealDetailsMeal, imageViewMealsDetailsCountriesFlags, btnCardViewMealDetailsAddToFav;
    TextView textViewMealDetailsTitle, textViewMealsDeatilsCountryName, textViewMealDetailsInstructions;
    MealDetailsActivityPresenter presenter;
    WebView webViewYoutubeVideo;
    CardView cardToolbarMealDetails;
    Button btnAddToPlannerMealDetails;
    ScrollView scrollViewMeals;
    Toolbar toolBarMealDetails;
    Meal meal;
    InternetStatesBroadcastReceiver internetReceiver;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MealDetailsActivityPresenterImpl(this, Repository.getInstance(this));
        setContentView(R.layout.activity_meal_details);
        meal = (Meal) getIntent().getSerializableExtra("meal");
        imageViewMealDetailsMeal = findViewById(R.id.imageViewMealDetailsMeal);
        btnAddToPlannerMealDetails = findViewById(R.id.btnAddToPlannerMealDetails);
        cardToolbarMealDetails = findViewById(R.id.cardToolbarMealDetails);
        btnCardViewMealDetailsAddToFav = findViewById(R.id.btnCardViewMealDetailsAddToFav);
        textViewMealDetailsTitle = findViewById(R.id.textViewMealDetailsTitle);
        scrollViewMeals = findViewById(R.id.scrollViewMeals);
        imageViewMealsDetailsCountriesFlags = findViewById(R.id.imageViewMealsDetailsCountriesFlags);
        textViewMealsDeatilsCountryName = findViewById(R.id.textViewMealsDeatilsCountryName);
        recyclerViewMealDetailsIngredients = findViewById(R.id.recyclerViewMealDetailsIngredient);
        textViewMealDetailsInstructions = findViewById(R.id.textViewMealDetailsInstructions);
        toolBarMealDetails = findViewById(R.id.toolBarMealDetails);
        internetReceiver = new InternetStatesBroadcastReceiver(this);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internetReceiver, filter);


        setSupportActionBar(toolBarMealDetails);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolBarMealDetails.setNavigationOnClickListener(v -> finish());
        btnAddToPlannerMealDetails.setOnClickListener((e) -> {
            new PlannerDialog(this, meal.getIdMeal(), meal.getStrMeal(), meal.getStrMealThumb()).showDialog();
        });
        webViewYoutubeVideo = findViewById(R.id.webViewYoutubeVideo);
        WebSettings webSettings = webViewYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewYoutubeVideo.setWebViewClient(new WebViewClient());
        IngredientRecyclerViewAdapter adapter = new IngredientRecyclerViewAdapter(meal);
        llmForIngredients = new LinearLayoutManager(this);
        llmForIngredients.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMealDetailsIngredients.setLayoutManager(llmForIngredients);
        recyclerViewMealDetailsIngredients.setAdapter(adapter);
        imageViewMealDetailsMeal.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollViewMeals.getScrollY();
                adjustCardViewAlpha(scrollY);
            }
        });
        setUpFavButton(meal);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onMealDataReceived(meal);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void adjustCardViewAlpha(int scrollY) {
        int maxScroll = imageViewMealDetailsMeal.getMeasuredHeight();
        float alphaRatio = (float) scrollY / maxScroll;
        int alphaValue = (int) (255 * alphaRatio);
        int backgroundColor = Color.argb(alphaValue, 255, 255, 255);
        cardToolbarMealDetails.setCardBackgroundColor(backgroundColor);
    }

    private void setUpFavButton(Meal meal) {
        if (meal.isFavorite())
            btnCardViewMealDetailsAddToFav.setImageResource(R.drawable.fillheart);
        else
            btnCardViewMealDetailsAddToFav.setImageResource(R.drawable.outlineheartwhite);
        btnCardViewMealDetailsAddToFav.setOnClickListener((e) -> {
            if (meal.isFavorite()) {
                btnCardViewMealDetailsAddToFav.setImageResource(R.drawable.outlineheartwhite);
                presenter.deleteMeal(meal);
                meal.setFavorite(false);
            } else {
                presenter.insertMeal(meal);
                meal.setFavorite(true);
                btnCardViewMealDetailsAddToFav.setImageResource(R.drawable.fillheart);
            }

        });

    }

    public void showMealDetails(Meal meal) {

        Glide.with(this).load(meal.getStrMealThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.png_food_placeholder)
                .error(R.drawable.png_food_error)).into(imageViewMealDetailsMeal);

        Glide.with(this).load("https://flagsapi.com/" + CuisineAreaEnum.getCountryCodeByArea(meal.getStrArea()) + "/shiny/64.png").apply(new RequestOptions()
                .placeholder(R.drawable.png_food_placeholder)
                .error(R.drawable.png_food_error)).into(imageViewMealsDetailsCountriesFlags);

        textViewMealsDeatilsCountryName.setText(meal.getStrArea());
        textViewMealDetailsTitle.setText(meal.getStrMeal());
        textViewMealDetailsInstructions.setText(meal.getStrInstructions());
        webViewYoutubeVideo.loadUrl(meal.getStrYoutube());


    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetowrkErrorSnackBar(String message) {
        snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE).setAction("Retry", view -> {
            snackbar.dismiss();
            this.recreate();
        });
        snackbar.show();
    }
}
package com.example.mealscript.MealDetails.Views;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealscript.MealDetails.Presenter.MealDetailsActivityPresenter;
import com.example.mealscript.Model.CuisineArea;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsActivityInterface {
    LinearLayoutManager llmForIngredients;
    RecyclerView recyclerViewMealDetailsIngredients;
    ImageView imageViewMealDetailsMeal, imageViewMealsDetailsCountriesFlags;
    TextView textViewMealDetailsTitle, textViewMealsDeatilsCountryName ,textViewMealDetailsInstructions;
    MealDetailsActivityPresenter presenter;
    WebView webViewYoutubeVideo;
    CardView cardToolbarMealDetails;
    ScrollView scrollViewMeals;

    private static final String TAG = "ALY";
    Meal meal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG , "onCreate: ALY AWAD");
        presenter = new MealDetailsActivityPresenter(this);
        setContentView(R.layout.activity_meal_details);
        meal = (Meal) getIntent().getSerializableExtra("meal");
        imageViewMealDetailsMeal = findViewById(R.id.imageViewMealDetailsMeal);
     cardToolbarMealDetails = findViewById(R.id.cardToolbarMealDetails);
        textViewMealDetailsTitle = findViewById(R.id.textViewMealDetailsTitle);
        scrollViewMeals = findViewById(R.id.scrollViewMeals);
        imageViewMealsDetailsCountriesFlags = findViewById(R.id.imageViewMealsDetailsCountriesFlags);
        textViewMealsDeatilsCountryName = findViewById(R.id.textViewMealsDeatilsCountryName);
        recyclerViewMealDetailsIngredients = findViewById(R.id.recyclerViewMealDetailsIngredient);
        textViewMealDetailsInstructions = findViewById(R.id.textViewMealDetailsInstructions);
        webViewYoutubeVideo = findViewById(R.id.webViewYoutubeVideo);
        WebSettings webSettings = webViewYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewYoutubeVideo.setWebViewClient(new WebViewClient());
        IngredientRecyclerViewAdapter adapter = new IngredientRecyclerViewAdapter(meal);
        llmForIngredients = new LinearLayoutManager(this);
        llmForIngredients.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMealDetailsIngredients.setLayoutManager(llmForIngredients);
        recyclerViewMealDetailsIngredients.setAdapter(adapter);
        scrollViewMeals.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollViewMeals.getScrollY();
                adjustCardViewAlpha(scrollY);
            }
        });

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
        int alphaValue = (int) (255 * alphaRatio);  // 255 is the maximum value for alpha
        int backgroundColor = Color.argb(alphaValue, 255, 255, 255);  // Adjusts the alpha from 0 to 255
        cardToolbarMealDetails.setCardBackgroundColor(backgroundColor);
    }

    public void showMealDetails(Meal meal) {
     
        Glide.with(this).load(meal.getStrMealThumb()).apply(new RequestOptions()
                .placeholder(R.drawable.ingradient)
                .error(R.drawable.ic_launcher_foreground)).into(imageViewMealDetailsMeal);

        Glide.with(this).load("https://flagsapi.com/"+ CuisineArea.getCountryCodeByArea(meal.getStrArea())+"/shiny/64.png").apply(new RequestOptions()
                .placeholder(R.drawable.ingradient)
                .error(R.drawable.ic_launcher_foreground)).into(imageViewMealsDetailsCountriesFlags);

        textViewMealsDeatilsCountryName.setText(meal.getStrArea());
        textViewMealDetailsTitle.setText(meal.getStrMeal());
        textViewMealDetailsInstructions.setText(meal.getStrInstructions());
        webViewYoutubeVideo.loadUrl(meal.getStrYoutube());


    }
}
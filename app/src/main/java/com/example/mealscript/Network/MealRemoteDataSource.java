package com.example.mealscript.Network;

import android.util.Log;

import com.example.mealscript.Model.Categories;
import com.example.mealscript.Model.Category;
import com.example.mealscript.Model.Meal;
import com.example.mealscript.Model.Meals;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {

    private final static String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static final String TAG = "Network";
    RetroMealsInterface rpi;
    static MealRemoteDataSource client = null;

    private MealRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL).build();
        rpi = retrofit.create(RetroMealsInterface.class);
    }

    public static MealRemoteDataSource getInstance() {
        if (client == null)
            client = new MealRemoteDataSource();
        return client;
    }

    public void getRandomMeals() {
        Observable mealObservable = rpi.getRandomMeal();
        Observer<Meals> observer = new Observer<Meals>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Meals meals) {
                for (int i = 0; i < meals.getMealsList().size(); i++) {

                    Log.i(TAG, "onNext: " + meals.getMealsList().get(i).getStrMeal());

                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        mealObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }

    public void getMealsByName(NetworkDelegate delegate, String mealName) {
        Observable mealObservable = rpi.getMealsByName(mealName);
        Observer<Meals> observer = new Observer<Meals>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Meals meals) {
                Log.i("Network", "onNext: u succck");
                delegate.returnMeals(meals.getMealsList());


            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        mealObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }

    public void getCategories(NetworkDelegate delegate) {
        Observable categoriesObserver = rpi.getCategories();
        categoriesObserver.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    List<Category> categoriesList = ((Categories) categories).getCategories();
                    delegate.returnCategories(categoriesList);
                });
    }

    public void getAreas(NetworkDelegate delegate) {
        Observable categoriesObserver = rpi.getAreas();
        categoriesObserver.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    List<Meal> areaList = ((Meals) meals).getMealsList();
                    delegate.returnAreas(areaList);
                });
    }

}




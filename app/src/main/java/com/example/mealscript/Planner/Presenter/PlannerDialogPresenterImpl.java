package com.example.mealscript.Planner.Presenter;

import android.content.Context;

import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.DayOfTheWeekEnum;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Planner.View.PlannerDialogInterface;
import com.example.mealscript.Repo.Repo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PlannerDialogPresenterImpl implements PlannerDialogPresenter {
    private PlannerDialogInterface view;
    private Repo repo;

    public PlannerDialogPresenterImpl(PlannerDialogInterface view, Context context) {
        this.view = view;
        this.repo = Repo.getInstance(context);
    }

    public void insertMeal(String mealId, String mealName, String mealImage, String dayOfTheWeek) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            PlannerMeal plannerMeal = new PlannerMeal(authManager.getCurrentUserId(), mealName, mealId, mealImage, DayOfTheWeekEnum.getDayNumberByName(dayOfTheWeek));
            repo.insertPlannerMeal(plannerMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe( );
        } else {
            view.showError("You can't add or remove favorite items guest mode!");
        }

    }

}

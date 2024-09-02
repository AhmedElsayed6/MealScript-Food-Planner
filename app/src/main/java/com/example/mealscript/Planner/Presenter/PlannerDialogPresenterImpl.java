package com.example.mealscript.Planner.Presenter;

import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.DayOfTheWeekEnum;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Planner.View.PlannerDialogInterface;
import com.example.mealscript.Repository.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PlannerDialogPresenterImpl implements PlannerDialogPresenter {
    private PlannerDialogInterface view;
    private Repository repository;

    public PlannerDialogPresenterImpl(PlannerDialogInterface view, Repository repo) {
        this.view = view;
        this.repository = repo;
    }

    public void insertMeal(String mealId, String mealName, String mealImage, String dayOfTheWeek) {
        AuthManager authManager = new AuthManager();
        if (!authManager.isGuestMode()) {
            PlannerMeal plannerMeal = new PlannerMeal(authManager.getCurrentUserId(), mealName, mealId, mealImage, DayOfTheWeekEnum.getDayNumberByName(dayOfTheWeek));
            repository.insertPlannerMeal(plannerMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } else {
            view.showError("You can't add or remove favorite items guest mode!");
        }

    }

}

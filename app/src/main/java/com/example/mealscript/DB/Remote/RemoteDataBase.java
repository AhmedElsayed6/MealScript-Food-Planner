package com.example.mealscript.DB.Remote;

import android.util.Log;

import com.example.mealscript.Model.AuthManager;
import com.example.mealscript.Model.User;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;
import com.example.mealscript.Profile.Presenter.CallbackForProfile;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RemoteDataBase {
    private FirebaseFirestore remoteFireStore;


   public RemoteDataBase(){
       remoteFireStore = FirebaseFirestore.getInstance();
    }

    public void insertUser(User user){
        remoteFireStore.collection("users").document(user.getUserId())
                .set(user);
    }

    public void insertFavoriteMealList(List<FavoriteMeal> favoriteMealList){
        AuthManager authManager = new AuthManager();
        DocumentReference userDocRef = remoteFireStore.collection("users").document(authManager.getCurrentUserId());
        userDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                userDocRef.update("favoriteMealList", favoriteMealList)
                        .addOnSuccessListener(aVoid -> Log.d("Firestore", "Favorite meal successfully added!"))
                        .addOnFailureListener(e -> Log.w("Firestore", "Error adding favorite meal", e));
            }
        }).addOnFailureListener(e -> Log.w("Firestore", "Error fetching document", e));
    }
    public void insertPlannerMealList(List<PlannerMeal> plannerMealList){
        AuthManager authManager = new AuthManager();
        DocumentReference userDocRef = remoteFireStore.collection("users").document(authManager.getCurrentUserId());
        userDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                userDocRef.update("plannerMealList", plannerMealList)
                        .addOnSuccessListener(aVoid -> Log.d("Firestore", "Favorite meal successfully added!"))
                        .addOnFailureListener(e -> Log.w("Firestore", "Error adding favorite meal", e));
            }
        }).addOnFailureListener(e -> Log.w("Firestore", "Error fetching document", e));
    }

        public void insertIntoFavoritesTable(CallbackForProfile callback){
        AuthManager authManager = new AuthManager();
        DocumentReference userDocRef = remoteFireStore.collection("users").document(authManager.getCurrentUserId());
        userDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                List<FavoriteMeal> favoriteMealList = documentSnapshot.toObject(User.class).getFavoriteMealList();
                callback.updateFavoriteLocalDataBase(favoriteMealList);
            }
        }).addOnFailureListener(e -> callback.displayErrorMessage(e.getMessage()));

    }

    public void insertIntoPlannerTable(CallbackForProfile callback){
        AuthManager authManager = new AuthManager();
        DocumentReference userDocRef = remoteFireStore.collection("users").document(authManager.getCurrentUserId());
        userDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                List<PlannerMeal> plannerMealList = documentSnapshot.toObject(User.class).getPlannerMealList();
                callback.updatePlannerLocalDataBase(plannerMealList);
            }
        }).addOnFailureListener(e -> callback.displayErrorMessage(e.getMessage()));

    }
    public void getUserDetails(CallbackForProfile callback){
        AuthManager authManager = new AuthManager();
        DocumentReference userDocRef = remoteFireStore.collection("users").document(authManager.getCurrentUserId());
        userDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String name = documentSnapshot.toObject(User.class).getDisplayName();
                String email = documentSnapshot.toObject(User.class).getEmail();
                callback.getUserDetails(name,email);
            }
        }).addOnFailureListener(e -> callback.displayErrorMessage(e.getMessage()));

    }


}

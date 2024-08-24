package com.example.mealscript.Auth.Model;

import android.content.Intent;
import android.util.Log;

import com.example.mealscript.Auth.Presenters.AuthPresenter;
import com.example.mealscript.DB.Remote.RemoteDataBase;
import com.example.mealscript.Model.FavoriteMeal;
import com.example.mealscript.Model.PlannerMeal;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

public class AuthManager {
    private static String TAG = "AuthManager";
    private  RemoteDataBase remoteDataBase;
    public static boolean isIsGuest() {
        return isGuest;
    }

    private static boolean isGuest = false;
    private static FirebaseAuth mAuth;
    private String currentUserId = null;


    public AuthManager() {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null)
            currentUserId = mAuth.getCurrentUser().getUid();
    }
    public void setUpUserId(){
        currentUserId = mAuth.getCurrentUser().getUid();
    }

    public void Login(String email, String password, AuthPresenter presenter) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "onComplete Login: success ");
                    presenter.onSuccess();
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                user.getDisplayName();
                    setGuestMode(false);
                } else {
                    Log.i(TAG, "onComplete Login : fail");
                    presenter.onFail();
                }
            }
        });

    }

    public void Signup(String email, String password,String name, AuthPresenter presenter) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            remoteDataBase = new RemoteDataBase();
                            User user = new User();
                            user.setDisplayName(name);
                            user.setEmail(email);
                            user.setUserId(task.getResult().getUser().getUid());
                            user.setFavoriteMealList(new ArrayList<FavoriteMeal>());
                            user.setPlannerMealList(new ArrayList<PlannerMeal>());
                            remoteDataBase.insertUser(user);
                            Log.i("TAGHOMEPAGE", "onComplete Registeration: success ");
                            setGuestMode(false);
                            presenter.onSuccess();
                        } else {
                            Log.i(TAG, "onComplete Registeration : fail");
                        }
                    }
                });
    }

    public void SignInUpWithGoogle(Intent data, AuthPresenter presenter) {
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        GoogleSignInAccount account = task.getResult();
                        if (account != null) {
                            String idToken = account.getIdToken();
                            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                    .addOnCompleteListener(authTask -> {
                                        if (authTask.isSuccessful()) {
                                            remoteDataBase = new RemoteDataBase();
                                            User user = new User();
                                            user.setDisplayName(account.getDisplayName());
                                            user.setEmail(account.getEmail());
                                            user.setUserId(task.getResult().getId());
                                            user.setFavoriteMealList(new ArrayList<FavoriteMeal>());
                                            user.setPlannerMealList(new ArrayList<PlannerMeal>());
                                            remoteDataBase.insertUser(user);
                                            Log.i(TAG, "handleSignInResult: Firebase sign-in successful");
                                            setGuestMode(false);
                                            presenter.onSuccess();
                                        } else {
                                            Log.e(TAG, "handleSignInResult: Firebase sign-in failed", authTask.getException());
                                        }
                                    });
                        } else {
                            Log.e(TAG, "handleSignInResult: GoogleSignInAccount is null");
                        }
                    } else {
                        Log.e(TAG, "handleSignInResult: Google sign-in failed", task.getException());
                    }
                });
    }

    public void SignOut() {
        mAuth.signOut();
    }

    public boolean isUserLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }

    public static boolean isGuestMode() {
        return isGuest;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setGuestMode(boolean isGuest) {
        this.isGuest = isGuest;
    }
}

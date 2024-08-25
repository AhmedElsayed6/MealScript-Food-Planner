package com.example.mealscript.Model;

import android.content.Intent;
import android.util.Log;

import com.example.mealscript.Auth.Presenters.AuthPresenter;
import com.example.mealscript.DB.Remote.RemoteDataBase;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

public class AuthManager {
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
                    presenter.onSuccess();
                    setGuestMode(false);
                } else {
                    presenter.onFail(task.getException().getMessage());
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
                            setGuestMode(false);
                            presenter.onSuccess();
                        } else {
                            presenter.onFail("Email Already used by another account");
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
                                            boolean isNewUser = authTask.getResult().getAdditionalUserInfo().isNewUser();
                                            if(isNewUser){
                                                remoteDataBase = new RemoteDataBase();
                                                User user = new User();
                                                user.setDisplayName(account.getDisplayName());
                                                user.setEmail(account.getEmail());
                                                user.setUserId(mAuth.getCurrentUser().getUid());
                                                user.setFavoriteMealList(new ArrayList<FavoriteMeal>());
                                                user.setPlannerMealList(new ArrayList<PlannerMeal>());
                                                remoteDataBase.insertUser(user);
                                            }
                                            setGuestMode(false);
                                            presenter.onSuccess();
                                        } else {
                                            presenter.onFail("Failed To Signup");
                                        }
                                    });
                        } else {
                            presenter.onFail("Failed To Signup");
                        }
                    } else {
                        presenter.onFail("Failed To Signup");
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

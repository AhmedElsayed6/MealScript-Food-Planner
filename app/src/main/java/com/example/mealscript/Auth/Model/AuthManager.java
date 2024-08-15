package com.example.mealscript.Auth.Model;

import android.util.Log;

import com.example.mealscript.Auth.Presenters.AuthPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.rxjava3.annotations.NonNull;

public class AuthManager {
    private static String TAG ="AuthManager";
    private static AuthManager instance = null;
    private static FirebaseAuth mAuth;
    private AuthPresenter presenter;
    private AuthManager(AuthPresenter presenter) {
        this.presenter = presenter;
    }

    public static AuthManager getInstance(AuthPresenter presenter) {
        mAuth = FirebaseAuth.getInstance();
        if (instance == null)
            instance = new AuthManager(presenter);
        return instance;
    }

    public void Login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "onComplete Login: success ");
                    presenter.onSuccess();
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                user.getDisplayName();

                } else {
                    Log.i(TAG, "onComplete Login : fail");
                    presenter.onFail();
                }
            }
        });

    }
    public void Signup(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.i(TAG, "onComplete Registeration: success ");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                user.getDisplayName();

                        } else {
                            Log.i(TAG, "onComplete Registeration : fail");
                        }
                    }
                });
    }
    public void SignInUpWithGoogle(){
        Log.i(TAG, "SignInUpWithGoogle: ");
    }
    public void SignOut(){
        mAuth.signOut();
    }
    public boolean isUserLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }
}

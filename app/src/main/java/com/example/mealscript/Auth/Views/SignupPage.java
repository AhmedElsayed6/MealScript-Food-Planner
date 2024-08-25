package com.example.mealscript.Auth.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mealscript.Auth.Presenters.SignupPresenter;
import com.example.mealscript.Auth.Presenters.SignupPresenterImpl;
import com.example.mealscript.Home.Views.HomeActivity;
import com.example.mealscript.R;
import com.google.android.material.textfield.TextInputEditText;


public class SignupPage extends Fragment implements SignupPageInterface {
    TextInputEditText textInputSignupPasswordConfirm, textInputSignupDisplayName, textInputSignupEmail, textInputSignupPassword;
    Button btnSignupSignup;
    Toolbar toolbarSignup;
    NavController navController;
    SignupPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = SignupPresenterImpl.getInstance(this);
        textInputSignupPasswordConfirm = view.findViewById(R.id.textInputSignupPasswordConfirm);
        textInputSignupDisplayName = view.findViewById(R.id.textInputSignupDisplayName);
        textInputSignupEmail = view.findViewById(R.id.textInputSignupEmail);
        textInputSignupPassword = view.findViewById(R.id.textInputSignupPassword);
        btnSignupSignup = view.findViewById(R.id.btnSignupSignup);
        toolbarSignup = view.findViewById(R.id.toolbarSignup);
        ConfigAppBar(view);

        btnSignupSignup.setOnClickListener((e) -> {
            String email = textInputSignupEmail.getText().toString();
            String displayName = textInputSignupDisplayName.getText().toString();
            String password = textInputSignupPassword.getText().toString();
            String confirmPassword = textInputSignupPasswordConfirm.getText().toString();
            presenter.validateUser(displayName, email, password, confirmPassword);
        });
    }

    private void ConfigAppBar(View view) {
        navController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.welcomePage).build();
        NavigationUI.setupWithNavController(toolbarSignup, navController, appBarConfiguration);
        toolbarSignup.setTitle(" ");
    }

    @Override
    public void showEmailTextError() {
        textInputSignupEmail.setError("Email can't be empty, And must have a valid domain");
    }

    @Override
    public void showNameTextError() {
        textInputSignupDisplayName.setError("Name can't be empty, And must contain only letters and whitespace");
    }

    @Override
    public void showPasswordTextError() {
        textInputSignupPassword.setError("Password can't be empty and must be at least 6 characters long");
    }

    @Override
    public void showConfirmPasswordTextError() {
        textInputSignupPasswordConfirm.setError("Passwords don't match");
    }

    @Override
    public void onSignupSuccess() {
        Intent toHome = new Intent(getActivity(), HomeActivity.class);
        startActivity(toHome);
    }

    @Override
    public void onSignupFail(String message) {
        Toast.makeText(this.getContext(), message , Toast.LENGTH_SHORT).show();
    }


}
package com.example.mealscript.Auth.Views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealscript.Auth.Presenters.LoginPresenter;
import com.example.mealscript.R;
import com.google.android.material.textfield.TextInputEditText;


public class LoginPage extends Fragment implements LoginPageInterface {
    TextInputEditText textInputLoginEmail, textInputLoginPassword;
    Button btnLoginLogin;
    TextView textLoginForgotPassword;
    Toolbar toolbarLogin;
    NavController navController;
    LoginPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = LoginPresenter.getInstance(this);
        textInputLoginEmail = view.findViewById(R.id.textInputLoginEmail);
        textInputLoginPassword = view.findViewById(R.id.textInputLoginPassword);
        btnLoginLogin = view.findViewById(R.id.btnLoginLogin);
        textLoginForgotPassword = view.findViewById(R.id.textLoginForgotPassword);
        toolbarLogin = view.findViewById(R.id.toolbarLogin);
        ConfigAppBar(view);



        btnLoginLogin.setOnClickListener((e)->{
            String email = textInputLoginEmail.getText().toString();
            String password = textInputLoginPassword.getText().toString();
            presenter.validateUser( email, password);
        });
    }





    private  void ConfigAppBar(View view){
        navController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.welcomePage).build();
        NavigationUI.setupWithNavController(toolbarLogin, navController, appBarConfiguration);
        toolbarLogin.setTitle(" ");
    }

    @Override
    public void showEmailTextError() {
        textInputLoginEmail.setError("Email can't be empty, And must have a valid domain");
    }

    @Override
    public void showPasswordTextError() {
        textInputLoginPassword.setError("Password can't be empty or less than 6 characters");
    }
}
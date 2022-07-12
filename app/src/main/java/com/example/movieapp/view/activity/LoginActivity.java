package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieapp.R;
import com.example.movieapp.control.local.SharedPreference;
import com.example.movieapp.databinding.ActivityLoginBinding;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.NetworkChangeListener;
import com.example.movieapp.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private SharedPreference sharedPreference;
    private NetworkChangeListener networkChangeListener;

    private ActivityLoginBinding activityLoginBinding;
    private LoginViewModel loginViewModel;

    public static void starter(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreference = new SharedPreference(this);
        networkChangeListener = new NetworkChangeListener();

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        activityLoginBinding.setUserViewModel(loginViewModel);

        activityLoginBinding.loginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAdminActivity.starter(LoginActivity.this);
            }
        });

        activityLoginBinding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPasswordActivity.starter(LoginActivity.this);
            }
        });

        Users users = new Users();
        sharedPreference.getUser(users);

        String name = users.getName();
        String password = users.getPassword();

        activityLoginBinding.inputName.setText(name);
        activityLoginBinding.inputPassword.setText(password);

        activityLoginBinding.createAccount.setOnClickListener(view -> {
            SignUpActivity.starter(LoginActivity.this);
        });
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}
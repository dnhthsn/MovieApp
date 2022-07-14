package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieapp.R;
import com.example.movieapp.control.local.SharedPreference;
import com.example.movieapp.databinding.ActivityLoginBinding;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.Const;
import com.example.movieapp.util.NetworkChangeListener;
import com.example.movieapp.util.Utility;
import com.example.movieapp.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    private SharedPreference sharedPreference;
    private NetworkChangeListener networkChangeListener;

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        userViewModel = new UserViewModel(this);

        binding.loginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAdminActivity.starter(LoginActivity.this);
            }
        });

        binding.clickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.inputName.getText().toString();
                String password = binding.inputPassword.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Utility.Notice.snack(view, Const.Error.name);
                } else if (TextUtils.isEmpty(password)) {
                    Utility.Notice.snack(view, Const.Error.password);
                } else {
                    if (binding.rememberUser.isChecked()) {
                        sharedPreference.saveUser(name, password);
                    } else {
                        sharedPreference.removeUser();
                    }

                    userViewModel.checkUser(name, password, LoginActivity.this);
                    binding.wrongInfo.setText(userViewModel.getMessage());
                }
            }
        });

        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPasswordActivity.starter(LoginActivity.this);
            }
        });

        Users users = new Users();
        sharedPreference.getUser(users);

        String name = users.getName();
        String password = users.getPassword();

        binding.inputName.setText(name);
        binding.inputPassword.setText(password);

        binding.createAccount.setOnClickListener(view -> {
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
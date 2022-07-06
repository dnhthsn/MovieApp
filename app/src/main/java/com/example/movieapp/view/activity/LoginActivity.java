package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.control.Repository;
import com.example.movieapp.control.local.SharedPreference;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Users;
import com.example.movieapp.presenter.LoginPresenter;
import com.example.movieapp.util.Const;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.LoginUser {
    private EditText inputName;
    private TextInputEditText inputPassword;
    private Button clickLogin;
    private TextView createAccount, loginAdmin, forgetPassword, wrongInfo;
    private CheckBox rememberUser;

    private Repository repository;
    private SharedPreference sharedPreference;
    private LoginPresenter loginPresenter;

    public static void starter(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        clickLogin = findViewById(R.id.click_login);
        createAccount = findViewById(R.id.create_account);
        rememberUser = findViewById(R.id.remember_user);
        loginAdmin = findViewById(R.id.login_admin);
        forgetPassword = findViewById(R.id.forget_password);
        wrongInfo = findViewById(R.id.wrong_info);

        sharedPreference = new SharedPreference(this);
        loginPresenter = new LoginPresenter(this);

        loginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAdminActivity.starter(LoginActivity.this);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPasswordActivity.starter(LoginActivity.this);
            }
        });

        clickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository = new Repository();
                repository.getUser(new Callback() {
                    @Override
                    public void getUser(List<Users> list) {
                        super.getUser(list);

                        String name = inputName.getText().toString();
                        String password = inputPassword.getText().toString();
                        if (TextUtils.isEmpty(name)) {
                            Toast.makeText(LoginActivity.this, Const.Error.name, Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(password)) {
                            Toast.makeText(LoginActivity.this, Const.Error.password, Toast.LENGTH_SHORT).show();
                        } else {
                            if (rememberUser.isChecked()) {
                                sharedPreference.saveUser(name, password);
                            } else {
                                sharedPreference.removeUser();
                            }
                            loginPresenter.login(name, password);
                        }
                    }
                });
            }
        });

        Users users = new Users();
        sharedPreference.getUser(users);

        String name = users.getName();
        String password = users.getPassword();

        inputName.setText(name);
        inputPassword.setText(password);

        createAccount.setOnClickListener(view -> {
            SignUpActivity.starter(LoginActivity.this);
        });
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void loginSuccess(Users users) {
        sharedPreference.saveCurrentUser(users);
        MainActivity.starter(LoginActivity.this);
        wrongInfo.setText("");
        finish();
    }

    @Override
    public void loginError(Users users) {
        wrongInfo.setText(Const.Error.information);
    }
}
package com.example.movieapp.activity;

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
import com.example.movieapp.model.Users;
import com.example.movieapp.rest.Callback;
import com.example.movieapp.rest.Repository;
import com.example.movieapp.sharedpreferences.SharedPreference;
import com.example.movieapp.util.Const;
import com.example.movieapp.util.Utility;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText inputName;
    private TextInputEditText inputPassword;
    private Button clickLogin;
    private TextView createAccount, loginAdmin, forgetPassword, wrongInfo;
    private CheckBox rememberUser;

    private Repository repository;

    public static void starter(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    //Lỗi starter: tự động nhảy đến main activity

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
                                SharedPreference.saveUser(name, password);
                            } else {
                                SharedPreference.removeUser();
                            }
                            for (Users users : list) {
                                if (users.getName().equals(name) && users.getPassword().equals(password)) {
                                    Utility.currentOnlineUser = users;
                                    MainActivity.starter(LoginActivity.this);
                                    wrongInfo.setText("");
                                    finish();
                                    break;
                                } else {
                                    wrongInfo.setText(Const.Error.information);
                                }
                            }
                        }
                    }
                });
            }
        });

        SharedPreference.sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String name = SharedPreference.sharedPreferences.getString(Const.Sender.name, "");
        String pass = SharedPreference.sharedPreferences.getString(Const.Sender.password, "");

        inputName.setText(name);
        inputPassword.setText(pass);

        createAccount.setOnClickListener(view -> {
            SignUpActivity.starter(LoginActivity.this);
        });
    }

    @Override
    public void onBackPressed() {
    }
}
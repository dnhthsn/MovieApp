package com.example.movieapp.activity;

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

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText inputName, inputPassword;
    private Button clickLogin;
    private TextView createAccount, loginAdmin, forgetPassword, wrongInfo;
    private CheckBox rememberUser;

    private Repository repository;

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
                Intent intent = new Intent(LoginActivity.this, LoginAdminActivity.class);
                startActivity(intent);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
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
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    Utility.currentOnlineUser = users;
                                    startActivity(intent);
                                    wrongInfo.setText("");
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
            Intent intent1 = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent1);
        });
    }

    @Override
    public void onBackPressed() {
    }
}
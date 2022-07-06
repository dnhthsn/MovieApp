package com.example.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.control.Repository;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Admins;
import com.example.movieapp.presenter.LoginAdminPresenter;
import com.example.movieapp.util.Const;

import java.util.List;

public class LoginAdminActivity extends AppCompatActivity implements LoginAdminPresenter.LoginAdmin {
    private EditText inputName, inputPassword;
    private Button clickLogin;
    private ImageView clickBack;

    private Repository repository;
    private LoginAdminPresenter loginAdminPresenter;

    public static void starter(Context context) {
        Intent intent = new Intent(context, LoginAdminActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        clickLogin = findViewById(R.id.click_login);
        clickBack = findViewById(R.id.click_back);

        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loginAdminPresenter = new LoginAdminPresenter(this);

        repository = new Repository();
        repository.getAdmin(new Callback() {
            @Override
            public void getAdmin(List<Admins> list) {
                super.getAdmin(list);
                clickLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = inputName.getText().toString();
                        String password = inputPassword.getText().toString();
                        if (TextUtils.isEmpty(name)) {
                            Toast.makeText(LoginAdminActivity.this, Const.Error.name, Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(password)) {
                            Toast.makeText(LoginAdminActivity.this, Const.Error.password, Toast.LENGTH_SHORT).show();
                        } else {
                            loginAdminPresenter.login(name, password);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void loginSuccess(Admins admins) {
        Toast.makeText(LoginAdminActivity.this, Const.Success.login, Toast.LENGTH_SHORT).show();
        AddMovieActivity.starter(LoginAdminActivity.this);
    }

    @Override
    public void loginError(Admins admins) {
        Toast.makeText(LoginAdminActivity.this, Const.Error.information, Toast.LENGTH_SHORT).show();
    }
}
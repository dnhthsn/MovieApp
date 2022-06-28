package com.example.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.model.Admins;
import com.example.movieapp.model.Users;
import com.example.movieapp.prevalent.Prevalent;
import com.example.movieapp.rest.Callback;
import com.example.movieapp.rest.Repository;
import com.example.movieapp.sharedpreferences.SharedPreference;
import com.example.movieapp.util.Const;

import java.util.List;

public class LoginAdminActivity extends AppCompatActivity {
    private EditText inputName, inputPassword;
    private Button clickLogin;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        clickLogin = findViewById(R.id.click_login);

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
                            for (Admins admins : list) {
                                if (admins.getName().equals(name) && admins.getPassword().equals(password)) {
                                    Toast.makeText(LoginAdminActivity.this, Const.Success.login, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginAdminActivity.this, AddMovieActivity.class);
                                    startActivity(intent);
                                    break;
                                } else {
                                    Toast.makeText(LoginAdminActivity.this, Const.Error.information, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
            }
        });
    }
}
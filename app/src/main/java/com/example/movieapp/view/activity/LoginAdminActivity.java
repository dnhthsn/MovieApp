package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityLoginAdminBinding;
import com.example.movieapp.util.Const;
import com.example.movieapp.viewmodel.AdminViewModel;

public class LoginAdminActivity extends AppCompatActivity {
    private ActivityLoginAdminBinding binding;
    private AdminViewModel adminViewModel;

    public static void starter(Context context) {
        Intent intent = new Intent(context, LoginAdminActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_admin);
        adminViewModel = new AdminViewModel(this);

        binding.clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.clickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.inputName.getText().toString();
                String password = binding.inputPassword.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(LoginAdminActivity.this, Const.Error.name, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginAdminActivity.this, Const.Error.password, Toast.LENGTH_SHORT).show();
                } else {
                    adminViewModel.checkAdmin(name, password, LoginAdminActivity.this);
                }
            }
        });
    }
}
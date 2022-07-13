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
import com.example.movieapp.databinding.ActivityForgetPasswordBinding;
import com.example.movieapp.util.Const;
import com.example.movieapp.viewmodel.UserViewModel;

public class ForgetPasswordActivity extends AppCompatActivity{
    private ActivityForgetPasswordBinding binding;
    private UserViewModel userViewModel;

    public static void starter(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);
        userViewModel = new UserViewModel(this);

        binding.clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.clickConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.inputName.getText().toString();
                String password = binding.inputPassword.getText().toString();
                String repass = binding.inputRepassword.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(ForgetPasswordActivity.this, Const.Error.name, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(ForgetPasswordActivity.this, Const.Error.password, Toast.LENGTH_SHORT).show();
                } else if (!repass.equals(password)) {
                    Toast.makeText(ForgetPasswordActivity.this, Const.Error.notmatch, Toast.LENGTH_SHORT).show();
                } else {
                    userViewModel.updatePassword(name, password, ForgetPasswordActivity.this);
                }
            }
        });
    }
}
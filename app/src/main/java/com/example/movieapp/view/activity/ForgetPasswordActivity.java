package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityForgetPasswordBinding;
import com.example.movieapp.util.Const;
import com.example.movieapp.util.Utility;
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

                if (!repass.equals(password)) {
                    Utility.Notice.snack(view, Const.Error.notmatch);
                } else {
                    userViewModel.updatePassword(name, password, view, ForgetPasswordActivity.this);
                }
            }
        });
    }
}
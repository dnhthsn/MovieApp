package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivitySignUpBinding;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.Const;
import com.example.movieapp.util.Utility;
import com.example.movieapp.viewmodel.UserViewModel;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private UserViewModel userViewModel;

    public static void starter(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        userViewModel = new UserViewModel(this);

        binding.clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.clickSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.inputName.getText().toString();
                String phone = binding.inputPhone.getText().toString();
                String password = binding.inputPassword.getText().toString();
                String address = binding.inputAddress.getText().toString();

                int genderGrID = binding.genderGroup.getCheckedRadioButtonId();
                RadioButton genderRad = findViewById(genderGrID);
                String gender = genderRad.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Utility.Notice.snack(view, Const.Error.name);
                } else if (TextUtils.isEmpty(phone)) {
                    Utility.Notice.snack(view, Const.Error.phone);
                } else if (TextUtils.isEmpty(password)) {
                    Utility.Notice.snack(view, Const.Error.password);
                } else if (TextUtils.isEmpty(address)) {
                    Utility.Notice.snack(view, Const.Error.address);
                } else {
                    Users users = new Users(name, phone, password, address, gender);
                    userViewModel.addUser(users, view);
                    LoginActivity.starter(SignUpActivity.this);
                }
            }
        });
    }
}
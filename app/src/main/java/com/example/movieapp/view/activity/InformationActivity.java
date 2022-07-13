package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieapp.R;
import com.example.movieapp.control.local.SharedPreference;
import com.example.movieapp.databinding.ActivityInformationBinding;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.Const;

public class InformationActivity extends AppCompatActivity {
    private SharedPreference sharedPreference;
    private ActivityInformationBinding binding;

    public static void starter(Context context) {
        Intent intent = new Intent(context, InformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_information);

        sharedPreference = new SharedPreference(this);

        Users users = sharedPreference.getCurrentUser();

        String uName = users.getName();
        String uPass = users.getPassword();
        String uPhone = users.getPhone();
        String uAddress = users.getAddress();
        String uGender = users.getGender();

        binding.userName.setText("Name: " + uName);
        binding.password.setText("Password: " + uPass);
        binding.phoneNumber.setText("Phone number: " + uPhone);
        binding.address.setText("Address: " + uAddress);
        binding.gender.setText("Gender: " + uGender);

        binding.clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.editInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(Const.Sender.name, uName);
                bundle.putString(Const.Sender.password, uPass);
                bundle.putString(Const.Sender.phone, uPhone);
                bundle.putString(Const.Sender.address, uAddress);
                bundle.putString(Const.Sender.gender, uGender);
                EditInformationActivity.starter(InformationActivity.this, bundle);
            }
        });

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.starter(InformationActivity.this);
                finish();
            }
        });
    }
}
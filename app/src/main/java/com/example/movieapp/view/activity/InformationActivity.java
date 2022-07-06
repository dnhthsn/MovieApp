package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.control.local.SharedPreference;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.Const;

public class InformationActivity extends AppCompatActivity {
    private ImageView clickBack;
    private TextView editInfo, name, password, phone, address, gender;
    private Button logOut;

    private SharedPreference sharedPreference;

    public static void starter(Context context) {
        Intent intent = new Intent(context, InformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        clickBack = findViewById(R.id.click_back);
        editInfo = findViewById(R.id.edit_infor);
        name = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone_number);
        address = findViewById(R.id.address);
        gender = findViewById(R.id.gender);
        logOut = findViewById(R.id.log_out);

        sharedPreference = new SharedPreference(this);

        Users users = sharedPreference.getCurrentUser();

        String uName = users.getName();
        String uPass = users.getPassword();
        String uPhone = users.getPhone();
        String uAddress = users.getAddress();
        String uGender = users.getGender();

        name.setText("Name: " + uName);
        password.setText("Password: " + uPass);
        phone.setText("Phone number: " + uPhone);
        address.setText("Address: " + uAddress);
        gender.setText("Gender: " + uGender);

        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editInfo.setOnClickListener(new View.OnClickListener() {
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

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.starter(InformationActivity.this);
                finish();
            }
        });
    }
}
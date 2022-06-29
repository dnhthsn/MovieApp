package com.example.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp.R;
import com.example.movieapp.util.Prevalent;

public class InformationActivity extends AppCompatActivity {
    private ImageView clickBack;
    private TextView editInfo, name, password, phone, address, gender;

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

        String uName = Prevalent.currentOnlineUser.getName();
        String uPass = Prevalent.currentOnlineUser.getPassword();
        String uPhone = Prevalent.currentOnlineUser.getPhone();
        String uAddress = Prevalent.currentOnlineUser.getAddress();
        String uGender = Prevalent.currentOnlineUser.getGender();

        name.setText(uName);
        password.setText(uPass);
        phone.setText(uPhone);
        address.setText(uAddress);
        gender.setText(uGender);

        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, EditInformationActivity.class);
                startActivity(intent);
            }
        });
    }
}
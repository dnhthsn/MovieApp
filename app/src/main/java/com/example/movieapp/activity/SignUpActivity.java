package com.example.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.model.Users;
import com.example.movieapp.control.rest.Repository;
import com.example.movieapp.util.Const;

public class SignUpActivity extends AppCompatActivity {
    private EditText inputName, inputPassword, inputPhone, inputAddress;
    private RadioGroup genderGroup;
    private Button clickSignup;
    private ImageView clickBack;

    private Repository repository;

    public static void starter(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        inputPhone = findViewById(R.id.input_phone);
        inputAddress = findViewById(R.id.input_address);
        clickSignup = findViewById(R.id.click_sign_up);
        genderGroup = findViewById(R.id.gender_group);
        clickBack = findViewById(R.id.click_back);

        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        repository = new Repository();
        clickSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String phone = inputPhone.getText().toString();
                String password = inputPassword.getText().toString();
                String address = inputAddress.getText().toString();

                int genderGrID = genderGroup.getCheckedRadioButtonId();
                RadioButton genderRad = findViewById(genderGrID);
                String gender = genderRad.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(SignUpActivity.this, Const.Error.name, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(SignUpActivity.this, Const.Error.phone, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this, Const.Error.password, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(SignUpActivity.this, Const.Error.address, Toast.LENGTH_SHORT).show();
                } else {
                    Users users = new Users(name, phone, password, address, gender);
                    repository.addUser(users, SignUpActivity.this);
                    LoginActivity.starter(SignUpActivity.this);
                }
            }
        });
    }
}
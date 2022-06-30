package com.example.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.model.Users;
import com.example.movieapp.rest.Repository;
import com.example.movieapp.util.Const;
import com.example.movieapp.util.Utility;

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText inputName, inputPassword, inputRepass;
    private ImageView clickBack;
    private Button clickConfirm;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        inputRepass = findViewById(R.id.input_repassword);
        clickBack = findViewById(R.id.click_back);
        clickConfirm = findViewById(R.id.click_confirm);

        repository = new Repository();

        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        clickConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(ForgetPasswordActivity.this, Const.Error.name, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(ForgetPasswordActivity.this, Const.Error.password, Toast.LENGTH_SHORT).show();
                } else {
                    updatePass(name, password);
                }
            }
        });
    }

    public void updatePass(String name, String password){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(LayoutInflater.from(ForgetPasswordActivity.this).inflate(R.layout.dialog, null));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Users users = new Users();
                users.setName(name);
                users.setPassword(password);
                repository.updatePassword(users);
                Toast.makeText(ForgetPasswordActivity.this, Const.Success.update, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
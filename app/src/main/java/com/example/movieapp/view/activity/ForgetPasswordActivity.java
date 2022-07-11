package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.control.Repository;
import com.example.movieapp.presenter.ForgetPasswordPresenter;
import com.example.movieapp.util.Const;

public class ForgetPasswordActivity extends AppCompatActivity implements ForgetPasswordPresenter.ForgetPassword{
    private EditText inputName, inputPassword, inputRepass;
    private ImageView clickBack;
    private Button clickConfirm;

    private Repository repository;
    private ForgetPasswordPresenter forgetPasswordPresenter;

    public static void starter(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

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
        forgetPasswordPresenter = new ForgetPasswordPresenter(this);

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
                String repass = inputRepass.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(ForgetPasswordActivity.this, Const.Error.name, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(ForgetPasswordActivity.this, Const.Error.password, Toast.LENGTH_SHORT).show();
                } else if (!repass.equals(password)) {
                    Toast.makeText(ForgetPasswordActivity.this, Const.Error.notmatch, Toast.LENGTH_SHORT).show();
                } else {
                    forgetPasswordPresenter.changePassword(ForgetPasswordActivity.this, name, password);
                }
            }
        });
    }

//    public void updatePass(String name, String password) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(LayoutInflater.from(ForgetPasswordActivity.this).inflate(R.layout.dialog, null));
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Users users = new Users();
//                users.setName(name);
//                users.setPassword(password);
//
//                repository.updatePassword(users, ForgetPasswordActivity.this);
//
//                LoginActivity.starter(ForgetPasswordActivity.this);
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

    @Override
    public void changePassSuccess(Context context) {
        LoginActivity.starter(context);
    }
}
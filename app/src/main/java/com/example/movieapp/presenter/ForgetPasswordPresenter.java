package com.example.movieapp.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;

import com.example.movieapp.R;
import com.example.movieapp.control.Repository;
import com.example.movieapp.model.Users;

public class ForgetPasswordPresenter {
    private ForgetPassword forgetPassword;
    private Repository repository;

    public ForgetPasswordPresenter(ForgetPassword forgetPassword) {
        this.forgetPassword = forgetPassword;
        this.repository = new Repository();
    }

    public void changePassword(Context context, String name, String password){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(LayoutInflater.from(context).inflate(R.layout.dialog, null));
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

                repository.updatePassword(users, context);
                forgetPassword.changePassSuccess(context);
//
//                LoginActivity.starter(context);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public interface ForgetPassword{
        void changePassSuccess(Context context);
    }
}

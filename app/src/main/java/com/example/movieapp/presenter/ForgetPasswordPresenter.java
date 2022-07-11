package com.example.movieapp.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;

import com.example.movieapp.R;
import com.example.movieapp.control.Repository;
import com.example.movieapp.model.Users;

import java.io.IOException;

public class ForgetPasswordPresenter {
    private ForgetPassword forgetPassword;
    private Repository repository;

    public ForgetPasswordPresenter(ForgetPassword forgetPassword) {
        this.forgetPassword = forgetPassword;
        this.repository = new Repository();
    }

    public void changePassword(Context context, String name, String password){
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.timo);
        mediaPlayer.start();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(LayoutInflater.from(context).inflate(R.layout.dialog, null));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

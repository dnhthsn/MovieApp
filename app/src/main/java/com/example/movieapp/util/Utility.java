package com.example.movieapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;

import com.example.movieapp.R;
import com.example.movieapp.control.Repository;
import com.example.movieapp.model.Users;
import com.example.movieapp.view.activity.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class Utility {
    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo[] infos = manager.getAllNetworkInfo();
            if (infos != null) {
                for (int i = 0; i < infos.length; i++) {
                    if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static class Notice {
        public static void snack(View view, String data) {
            Snackbar snackbar = Snackbar.make(view, data, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        public static void dialog(Context context, String name, String password, Repository repository, View view){
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

                    repository.updatePassword(users, view);
                    LoginActivity.starter(context);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}

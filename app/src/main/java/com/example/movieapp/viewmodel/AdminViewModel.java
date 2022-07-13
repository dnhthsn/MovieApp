package com.example.movieapp.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.movieapp.control.Repository;
import com.example.movieapp.control.local.SharedPreference;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Admins;
import com.example.movieapp.util.Const;
import com.example.movieapp.view.activity.AddMovieActivity;

import java.util.List;

public class AdminViewModel extends ViewModel {
    private Repository repository;
    private SharedPreference sharedPreference;

    public AdminViewModel(Context context) {
        this.repository = new Repository();
        this.sharedPreference = new SharedPreference(context);
    }

    public void checkAdmin(String name, String password, Context context){
        repository.getAdmin(new Callback() {
            @Override
            public void getAdmin(List<Admins> list) {
                super.getAdmin(list);
                for (Admins admins : list){
                    if (admins.getName().equals(name) && admins.getPassword().equals(password)){
                        Toast.makeText(context, Const.Success.login, Toast.LENGTH_SHORT).show();
                        AddMovieActivity.starter(context);
                        break;
                    } else {
                        Toast.makeText(context, Const.Error.information, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

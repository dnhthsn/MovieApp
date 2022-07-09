package com.example.movieapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.movieapp.control.Repository;
import com.example.movieapp.control.local.SharedPreference;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.Const;
import com.example.movieapp.view.activity.MainActivity;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    private Repository repository;
    private SharedPreference sharedPreference;
    private String message;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public String getMessage() {
        return message;
    }

    public void login(Context context, String name, String password){
        sharedPreference = new SharedPreference(context);
        repository.getUser(new Callback() {
            @Override
            public void getUser(List<Users> list) {
                super.getUser(list);
                for (Users users : list){
                    if (users.getName().equals(name) && users.getPassword().equals(password)){
                        MainActivity.starter(context);
                        sharedPreference.saveCurrentUser(users);
                        message = "";
                    } else {
                        message = Const.Error.information;
                    }
                }
            }
        });
    }
}

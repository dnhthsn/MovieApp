package com.example.movieapp.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.movieapp.BR;
import com.example.movieapp.control.Repository;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Admins;
import com.example.movieapp.util.Const;
import com.example.movieapp.view.activity.AddMovieActivity;

import java.util.List;

public class LoginAdminViewModel extends BaseObservable {
    private String name;
    private String password;

    private Repository repository;
    private Context context;

    public LoginAdminViewModel(Context context) {
        this.repository = new Repository();
        this.context = context;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void onClickLogin() {
        repository.getAdmin(new Callback() {
            @Override
            public void getAdmin(List<Admins> list) {
                super.getAdmin(list);
                for (Admins admin : list) {
                    if (admin.getName().equals(getName()) && admin.getPassword().equals(getPassword())) {
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

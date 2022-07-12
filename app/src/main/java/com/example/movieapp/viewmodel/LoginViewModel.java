package com.example.movieapp.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.movieapp.BR;
import com.example.movieapp.control.Repository;
import com.example.movieapp.control.local.SharedPreference;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.Const;
import com.example.movieapp.view.activity.MainActivity;

import java.util.List;

public class LoginViewModel extends BaseObservable {
    private String name;
    private String password;
    public ObservableField<String> messageLogin = new ObservableField<>();
    
    private Repository repository;
    private Context context;
    private SharedPreference sharedPreference;

    public LoginViewModel(Context context) {
        this.repository = new Repository();
        this.sharedPreference = new SharedPreference(context);
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
        if (TextUtils.isEmpty(getName())){
            Toast.makeText(context, Const.Error.name, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(getPassword())){
            Toast.makeText(context, Const.Error.password, Toast.LENGTH_SHORT).show();
        } else {
            repository.getUser(new Callback() {
                @Override
                public void getUser(List<Users> list) {
                    super.getUser(list);
                    for (Users user : list) {
                        if (user.getName().equals(getName()) && user.getPassword().equals(getPassword())) {
                            sharedPreference.saveCurrentUser(user);
                            MainActivity.starter(context);
                            break;
                        } else {
                            messageLogin.set(Const.Error.information);
                        }
                    }
                }
            });
        }
    }
}

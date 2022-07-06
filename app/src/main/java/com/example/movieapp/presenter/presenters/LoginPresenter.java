package com.example.movieapp.presenter.presenters;

import com.example.movieapp.control.Repository;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Users;
import com.example.movieapp.presenter.interfaces.LoginInterface;

import java.util.List;

public class LoginPresenter {
    private LoginInterface loginInterface;
    private Repository repository;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
        this.repository = new Repository();
    }

    public void login(String name, String password){
        repository.getUser(new Callback() {
            @Override
            public void getUser(List<Users> list) {
                super.getUser(list);
                for (Users users : list){
                    if (users.getName().equals(name) && users.getPassword().equals(password)) {
                        loginInterface.loginSuccess(users);
                        break;
                    } else {
                        loginInterface.loginError(users);
                    }
                }
            }
        });
    }
}

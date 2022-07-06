package com.example.movieapp.presenter;

import com.example.movieapp.control.Repository;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Users;

import java.util.List;

public class LoginPresenter {
    private LoginUser loginUser;
    private Repository repository;

    public LoginPresenter(LoginUser loginUser) {
        this.loginUser = loginUser;
        this.repository = new Repository();
    }

    public void login(String name, String password){
        repository.getUser(new Callback() {
            @Override
            public void getUser(List<Users> list) {
                super.getUser(list);
                for (Users users : list){
                    if (users.getName().equals(name) && users.getPassword().equals(password)) {
                        loginUser.loginSuccess(users);
                        break;
                    } else {
                        loginUser.loginError(users);
                    }
                }
            }
        });
    }

    public interface LoginUser {
        void loginSuccess(Users users);
        void loginError(Users users);
    }
}

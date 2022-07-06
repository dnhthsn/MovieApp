package com.example.movieapp.presenter.interfaces;

import com.example.movieapp.model.Users;

public interface LoginInterface {
    void loginSuccess(Users users);
    void loginError(Users users);
}

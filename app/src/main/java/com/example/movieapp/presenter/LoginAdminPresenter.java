package com.example.movieapp.presenter;

import com.example.movieapp.control.Repository;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Admins;

import java.util.List;

public class LoginAdminPresenter {
    private LoginAdmin loginAdmin;
    private Repository repository;

    public LoginAdminPresenter(LoginAdmin loginAdmin) {
        this.loginAdmin = loginAdmin;
        this.repository = new Repository();
    }

    public void login(String name, String password){
        repository.getAdmin(new Callback() {
            @Override
            public void getAdmin(List<Admins> list) {
                super.getAdmin(list);
                for (Admins admins : list){
                    if (admins.getName().equals(name) && admins.getPassword().equals(password)) {
                        loginAdmin.loginSuccess(admins);
                        break;
                    } else {
                        loginAdmin.loginError(admins);
                    }
                }
            }
        });
    }

    public interface LoginAdmin {
        void loginSuccess(Admins admins);
        void loginError(Admins admins);
    }
}

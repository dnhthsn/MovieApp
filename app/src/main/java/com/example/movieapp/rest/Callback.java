package com.example.movieapp.rest;

import com.example.movieapp.model.Admins;
import com.example.movieapp.model.Users;

import java.util.List;

public abstract class Callback {
    public void getUser(List<Users> list){}
    public void getAdmin(List<Admins> list){}
}

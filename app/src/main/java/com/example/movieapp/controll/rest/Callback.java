package com.example.movieapp.controll.rest;

import com.example.movieapp.model.Admins;
import com.example.movieapp.model.Movies;
import com.example.movieapp.model.Users;

import java.util.List;

public abstract class Callback {
    public void getUser(List<Users> list){}
    public void getAdmin(List<Admins> list){}
    public void getMovie(List<Movies> list){}
}

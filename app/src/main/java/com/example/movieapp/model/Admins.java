package com.example.movieapp.model;

import java.io.Serializable;

public class Admins implements Serializable {
    private String name, password;

    public Admins() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}

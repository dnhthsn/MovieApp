package com.example.movieapp.model;

import java.io.Serializable;

public class CategoryItem implements Serializable {
    private int id;
    private String movieName;
    private String imageUrl;
    private String fileUrl;

    public CategoryItem(int id, String movieName, String imageUrl, String fileUrl) {
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.fileUrl = fileUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

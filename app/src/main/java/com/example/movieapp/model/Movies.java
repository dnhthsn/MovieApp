package com.example.movieapp.model;

import java.io.Serializable;

public class Movies implements Serializable {
    private String movieName;
    private String imageUrl;
    private String fileUrl;

    public Movies() {
    }

    public Movies(String movieName, String imageUrl, String fileUrl) {
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.fileUrl = fileUrl;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }
}

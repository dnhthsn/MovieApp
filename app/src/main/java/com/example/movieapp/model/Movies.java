package com.example.movieapp.model;

import java.io.Serializable;

public class Movies implements Serializable {
    private String name;
    private String image;
    private String video;
    private String genre;

    public Movies() {
    }

    public Movies(String name, String image, String video, String genre) {
        this.name = name;
        this.image = image;
        this.video = video;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {
        return video;
    }

    public String getGenre() {
        return genre;
    }
}

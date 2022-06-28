package com.example.movieapp.model;

import java.io.Serializable;

public class Movies implements Serializable {
    private String name;
    private String image;
    private String video;

    public Movies() {
    }

    public Movies(String name, String imageUrl, String fileUrl) {
        this.name = name;
        this.image = imageUrl;
        this.video = fileUrl;
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
}

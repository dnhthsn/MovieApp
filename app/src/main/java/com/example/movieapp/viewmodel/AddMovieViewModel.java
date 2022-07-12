package com.example.movieapp.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.BR;
import com.example.movieapp.control.Repository;
import com.example.movieapp.model.Movies;

public class AddMovieViewModel extends BaseObservable {
    private String name;
    private String image;
    private String video;
    private String genre;
    public MutableLiveData<Movies> moviesMutableLiveData = new MutableLiveData<>();

    private Repository repository;
    private Context context;

    public AddMovieViewModel(Context context) {
        repository = new Repository();
        this.context = context;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
        notifyPropertyChanged(BR.video);
    }

    @Bindable
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    public void onClickAddMovie(){
        Movies movies = new Movies(getName(), getImage(), getVideo(), getGenre());
        repository.addMovie(movies, context);
    }
}

package com.example.movieapp.util;

public interface Const {
    interface Database {
        String movie = "Movies";
        String admin = "Admins";
        String user = "Users";
        String name = "name";
        String password = "password";
        String phone = "phone";
        String address = "address";
        String gender = "gender";
        String image = "image";
        String video = "video";
        String genre = "genre";
    }

    interface Sender {
        String name = "name";
        String password = "password";
        String phone = "phone";
        String gender = "gender";
        String address = "address";
        String movieName = "movieName";
        String movieImageUrl = "movieImageUrl";
        String movieFile = "movieFile";
        String url = "url";
        String SHARED_PREFERENCES = "dataLogin";
        String users = "users";
    }

    interface Error {
        String name = "Please write your name...";
        String password = "Please write your password...";
        String phone = "Please write your phone...";
        String address = "Please write your address...";
        String information = "Wrong information";
        String network = "Network Error: Please try again...";
        String existed = "existed";
        String notexisted = "Not existed";
        String notmatch = "Not match with password";
    }

    interface Success {
        String created = "Created success fully";
        String update = "Profile info update successfully";
        String login = "Logged in Successfully";
        String connected = "Internet connected";
    }
}

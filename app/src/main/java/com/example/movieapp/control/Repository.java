package com.example.movieapp.control;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Admins;
import com.example.movieapp.model.Movies;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.Const;
import com.example.movieapp.util.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Repository {
    private FirebaseDatabase db;
    private DatabaseReference databaseReference;

    public Repository() {
        this.db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference();
    }

    public void getAdmin(Callback callback) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Admins> list = new ArrayList<>();
                Admins admins;
                for (DataSnapshot data : snapshot.getChildren()) {
                    admins = data.getValue(Admins.class);
                    list.add(admins);
                }
                callback.getAdmin(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(Const.Database.admin).addValueEventListener(postListener);
    }

    public void getUser(Callback callback) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Users> list = new ArrayList<>();
                Users users;
                for (DataSnapshot data : snapshot.getChildren()) {
                    users = data.getValue(Users.class);
                    list.add(users);
                }
                callback.getUser(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(Const.Database.user).addValueEventListener(postListener);
    }

    public void getMovie(Callback callback) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Movies> list = new ArrayList<>();
                Movies movies;
                for (DataSnapshot data : snapshot.getChildren()) {
                    movies = data.getValue(Movies.class);
                    list.add(movies);
                }
                if (list != null){
                    callback.getMovie(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(Const.Database.movie).addValueEventListener(postListener);
    }

    public void addUser(Users users, View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.child(Const.Database.user).child(users.getName()).exists()) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put(Const.Database.name, users.getName());
                    userdataMap.put(Const.Database.password, users.getPassword());
                    userdataMap.put(Const.Database.phone, users.getPhone());
                    userdataMap.put(Const.Database.address, users.getAddress());
                    userdataMap.put(Const.Database.gender, users.getGender());

                    databaseReference.child(Const.Database.user).child(users.getName()).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Utility.Notice.snack(view, Const.Success.created);
                            } else {
                                Utility.Notice.snack(view, Const.Error.network);
                            }
                        }
                    });
                } else {
                    Utility.Notice.snack(view, Const.Error.existed);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addMovie(Movies movies, View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.child(Const.Database.movie).child(movies.getName()).exists()) {
                    HashMap<String, Object> movieDataMap = new HashMap<>();
                    movieDataMap.put(Const.Database.name, movies.getName());
                    movieDataMap.put(Const.Database.image, movies.getImage());
                    movieDataMap.put(Const.Database.video, movies.getVideo());
                    movieDataMap.put(Const.Database.genre, movies.getGenre());

                    databaseReference.child(Const.Database.movie).child(movies.getName()).updateChildren(movieDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Utility.Notice.snack(view, Const.Success.created);
                            } else {
                                Utility.Notice.snack(view, Const.Error.network);
                            }
                        }
                    });
                } else {
                    Utility.Notice.snack(view, Const.Error.existed);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateUser(Users users) {
        databaseReference.child(Const.Database.user).child(users.getName()).setValue(users);
    }

    public void updatePassword(Users users, View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(Const.Database.user).child(users.getName()).exists()) {
                    Utility.Notice.snack(view, Const.Success.update);
                    databaseReference.child(Const.Database.user).child(users.getName()).child(Const.Database.password).setValue(users.getPassword());
                } else {
                    Utility.Notice.snack(view, Const.Error.notexisted);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

package com.example.movieapp.rest;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.movieapp.model.Admins;
import com.example.movieapp.model.Movies;
import com.example.movieapp.model.Users;
import com.example.movieapp.util.Const;
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
    private long id = 0;

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

    public void addUser(Users users, Context context) {
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
                                Toast.makeText(context, Const.Success.created, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, Const.Error.network, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(context, Const.Error.existed, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addMovie(Movies movies, Context context) {
        databaseReference.child(Const.Database.movie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    id = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child(Const.Database.movie).child(String.valueOf(id + 1)).setValue(movies);
        Toast.makeText(context, Const.Success.created, Toast.LENGTH_SHORT).show();
    }
}

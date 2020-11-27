package com.cobanogluhasan.giproject.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.cobanogluhasan.giproject.models.UsersPictures;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/*
Singleton pattern that guarantees a class has one instance only
and a global point of access to it is provided by that class.
* */
public class UsersPicturesRepository {

    private static UsersPicturesRepository usersPicturesRepository;
    private ArrayList<UsersPictures> dataSet = new ArrayList<>();

    public static UsersPicturesRepository getInstance() {
        if(usersPicturesRepository==null) {
            usersPicturesRepository = new UsersPicturesRepository();
        }
        return usersPicturesRepository;
    }

    public MutableLiveData<List<UsersPictures>> getUserPictures() {
        setUserInfo();

        MutableLiveData<List<UsersPictures>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }


     private void setUserInfo() {
         DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("users").orderByKey();

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String email = (String) snapshot.child("email").getValue().toString();
                String imageUrl = (String) snapshot.child("url").getValue().toString();
                String location = (String) snapshot.child("location").getValue().toString();

                dataSet.add(new UsersPictures(location,email,imageUrl));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.keepSynced(true);

    }




}

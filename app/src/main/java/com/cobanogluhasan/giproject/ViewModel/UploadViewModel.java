package com.cobanogluhasan.giproject.ViewModel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cobanogluhasan.giproject.Repository.RegisterLoginRepository;
import com.cobanogluhasan.giproject.Repository.UploadPicturesRepository;
import com.cobanogluhasan.giproject.Repository.UsersPicturesRepository;
import com.cobanogluhasan.giproject.models.UsersPictures;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class UploadViewModel extends AndroidViewModel {

    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private UploadPicturesRepository uploadPicturesRepository;

    public UploadViewModel(@NonNull Application application) {
        super(application);

        uploadPicturesRepository = new UploadPicturesRepository(application);
        userMutableLiveData = uploadPicturesRepository.getUserMutableLiveData();
    }





    public void uploadImage(String imageName, byte[] data, final Context context, View view,String adress) {
        uploadPicturesRepository.uploadImage(imageName, data, context,view,adress);
    }


    public LiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}

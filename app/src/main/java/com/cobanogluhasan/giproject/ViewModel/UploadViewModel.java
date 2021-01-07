package com.cobanogluhasan.giproject.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.cobanogluhasan.giproject.Repository.UploadPicturesRepository;
import com.cobanogluhasan.giproject.Repository.UsersPicturesRepository;
import com.cobanogluhasan.giproject.models.UsersPictures;

import java.util.List;

public class UploadViewModel {

    private MutableLiveData<List<UsersPictures>> mUserPictures;
    private UploadPicturesRepository uploadPicturesRepository ;




    public void uploadImage(String imageName, byte[] data, final Context context) {
      //  UploadPicturesRepository.uplo
    }
}

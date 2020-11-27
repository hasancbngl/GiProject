package com.cobanogluhasan.giproject.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cobanogluhasan.giproject.Model.UsersPicturesRepository;
import com.cobanogluhasan.giproject.models.UsersPictures;

import java.util.List;

public class ViewSharePhotoViewModel extends ViewModel {
    private MutableLiveData<List<UsersPictures>> mUserPictures;
    private UsersPicturesRepository usersPicturesRepository;

    public void init() {
        //check if we retrieved the data
        if(mUserPictures!=null) {
            //it means we received it.
            return;
        }

        usersPicturesRepository = UsersPicturesRepository.getInstance();
        mUserPictures = usersPicturesRepository.getUserPictures();
    }

    public LiveData<List<UsersPictures>> getUserPictures() {
        return mUserPictures;
    }


}

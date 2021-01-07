package com.cobanogluhasan.giproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cobanogluhasan.giproject.Repository.RegisterLoginRepository;
import com.google.firebase.auth.FirebaseUser;


public class LoggedInViewModel extends AndroidViewModel {
    private RegisterLoginRepository registerLoginRepository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> loggedOutMutableLiveData;

    public LoggedInViewModel(@NonNull Application application) {
        super(application);

        registerLoginRepository = new RegisterLoginRepository(application);
        userMutableLiveData = registerLoginRepository.getUserMutableLiveData();
        loggedOutMutableLiveData = registerLoginRepository.getLoggedOutMutableLiveData();
    }

    public void signOut() {
        registerLoginRepository.signOut();
    }

    public LiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public LiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }

}

package com.cobanogluhasan.giproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cobanogluhasan.giproject.Repository.RegisterLoginRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegisterViewModel extends AndroidViewModel {

    private RegisterLoginRepository registerLoginRepository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;

    public LoginRegisterViewModel(@NonNull Application application) {
        super(application);

        registerLoginRepository = new RegisterLoginRepository(application);
        userMutableLiveData = registerLoginRepository.getUserMutableLiveData();
    }

    public void register(String email, String password) {
        //invoke the register method on apprepo class

        registerLoginRepository.register(email, password);
    }

    public void login(String email, String password) {
        registerLoginRepository.login(email, password);
    }

    public LiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}

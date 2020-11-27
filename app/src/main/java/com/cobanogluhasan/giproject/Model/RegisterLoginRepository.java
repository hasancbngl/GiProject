package com.cobanogluhasan.giproject.Model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterLoginRepository {
    private static final String TAG = "AppRepository";
    private Application application;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> loggedOutMutableLiveData;
    private FirebaseAuth mAuth;

    public RegisterLoginRepository(Application application) {
        this.application = application;

        userMutableLiveData = new MutableLiveData<>();
        mAuth = FirebaseAuth.getInstance();
        loggedOutMutableLiveData = new MutableLiveData<>();

        if(mAuth.getCurrentUser()!=null) {
            userMutableLiveData.postValue(mAuth.getCurrentUser());
            loggedOutMutableLiveData.postValue(false);
        }
    }

    public void register(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            userMutableLiveData.postValue(mAuth.getCurrentUser());
                        }
                        else {
                            Toast.makeText(application, "failed!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void login(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Log.i(TAG, "onComplete: Signed In");
                            userMutableLiveData.postValue(mAuth.getCurrentUser());
                        }
                        else {
                            Toast.makeText(application, "failed!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
        loggedOutMutableLiveData.postValue(true);
    }



    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }



}

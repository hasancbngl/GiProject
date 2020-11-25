package com.cobanogluhasan.giproject.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.cobanogluhasan.giproject.R;
import com.cobanogluhasan.giproject.ViewModel.LoginRegisterViewModel;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegisterFragment extends Fragment {

    private static final String TAG = "LoginRegisterFragment";
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signupButton;
    private Button loginButton;
    private String email,password;


    private LoginRegisterViewModel loginRegisterViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginRegisterViewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);

        loginRegisterViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser!=null) {
                    Navigation.findNavController(getView()).navigate(R.id.action_loginRegisterFragment_to_viewSharePhotoFragment2);
                }
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loginregister,container,false);

        emailEditText = view.findViewById(R.id.loginregisterEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        signupButton = view.findViewById(R.id.signUpButton);
        loginButton = view.findViewById(R.id.loginButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email = emailEditText.getText().toString();
                 password = passwordEditText.getText().toString();

                 if(!email.equals("") && password.length()>5) {
                 loginRegisterViewModel.register(email,password);
                 }
                 else {
                     Toast.makeText(getContext(), "email@gmail.com şeklinde ve password en az 6 haneli olmalıdır.", Toast.LENGTH_SHORT).show();
                 }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                if(!email.equals("") && password.length()>3) {
                    loginRegisterViewModel.login(email,password);
                    Log.i(TAG, "onClick: login successfull ");
                }
            }
        });
        
        return view;
    }

}

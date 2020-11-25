package com.cobanogluhasan.giproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.cobanogluhasan.giproject.R;
import com.cobanogluhasan.giproject.ViewModel.LoggedInViewModel;
import com.cobanogluhasan.giproject.ViewModel.LoginRegisterViewModel;
import com.google.firebase.auth.FirebaseUser;

public class ViewSharePhotoFragment extends Fragment {

    private static final String TAG = "ViewSharePhotoFragment";
    private TextView emailTextView;
    private TextView addnewTextview;
    private Button signOutButton;
    private ImageButton addnewImageButton;
    private LoggedInViewModel loggedInViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel.class);

        loggedInViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser!=null) {
                    emailTextView.setText(firebaseUser.getEmail());
                }
            }
        });

        loggedInViewModel.getLoggedOutMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loggedOut) {
                if(loggedOut) {
                    Navigation.findNavController(getView()).navigate(R.id.action_viewSharePhotoFragment_to_loginRegisterFragment);
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewsharephoto,container,false);
        emailTextView = view.findViewById(R.id.emailTextView);
        addnewTextview = view.findViewById(R.id.addnewTextView);
        signOutButton = view.findViewById(R.id.signOutButton);
        addnewImageButton = view.findViewById(R.id.addnewImageButton);

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggedInViewModel.signOut();
            }
        });

        addnewImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUploadFragment();
            }
        });

        addnewTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUploadFragment();
            }
        });





        return view;
    }


    public void gotoUploadFragment(){
        Navigation.findNavController(getView()).navigate(R.id.action_viewSharePhotoFragment_to_uploadFragment);
    }

}

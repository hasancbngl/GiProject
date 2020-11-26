package com.cobanogluhasan.giproject.views;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cobanogluhasan.giproject.Adapter.Adapter;
import com.cobanogluhasan.giproject.R;
import com.cobanogluhasan.giproject.ViewModel.LoggedInViewModel;
import com.cobanogluhasan.giproject.ViewModel.LoginRegisterViewModel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ViewSharePhotoFragment extends Fragment {

    private static final String TAG = "ViewSharePhotoFragment";
    private TextView emailTextView;
    private TextView addnewTextview;
    private Button signOutButton;
    private ImageButton addnewImageButton;
    private LoggedInViewModel loggedInViewModel;
    private ArrayList<String> mUserEmails = new ArrayList<>();
    private ArrayList<String> mUserLocations = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerView;
    private Application application;

    public ViewSharePhotoFragment(Application application) {
        this.application = application;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel.class);

        loggedInViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser!=null) {
                    emailTextView.setText("user:" + firebaseUser.getEmail());
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

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: prepering bitmap ");
        readDataFirebase();

        initRecyclerView();

    }

    private void initRecyclerView() {
        Adapter adapter = new Adapter(,mImageUrls,mUserEmails,mUserLocations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewsharephoto,container,false);
        emailTextView = view.findViewById(R.id.emailTextView);
        addnewTextview = view.findViewById(R.id.addnewTextView);
        signOutButton = view.findViewById(R.id.signOutButton);
        addnewImageButton = view.findViewById(R.id.addnewImageButton);
        recyclerView = view.findViewById(R.id.recyclerview);

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

        initImageBitmaps();

        return view;
    }


    public void gotoUploadFragment(){
        Navigation.findNavController(getView()).navigate(R.id.action_viewSharePhotoFragment_to_uploadFragment);
    }

    public void readDataFirebase() {
        Query query = FirebaseDatabase.getInstance().getReference().child("users").orderByKey();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String email = (String) snapshot.child("email").getValue().toString();
                String imageUrl = (String) snapshot.child("url").getValue().toString();
                String location = (String) snapshot.child("location").getValue().toString();

                mImageUrls.add(imageUrl.substring(1,imageUrl.length()-1));
                mUserEmails.add(email);
                mUserLocations.add(location);
                Log.i(TAG, "onChildAdded: " + imageUrl);
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

    }

}

package com.cobanogluhasan.giproject.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cobanogluhasan.giproject.Adapter.Adapter;
import com.cobanogluhasan.giproject.models.UsersPictures;
import com.cobanogluhasan.giproject.R;
import com.cobanogluhasan.giproject.ViewModel.LoggedInViewModel;
import com.cobanogluhasan.giproject.ViewModel.ViewSharePhotoViewModel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;
import java.util.List;

@SuppressWarnings("ALL")
public class ViewSharePhotoFragment extends Fragment {

    private static final String TAG = "ViewSharePhotoFragment";
    private TextView emailTextView;
    private TextView addnewTextview;
    private Button signOutButton;
    private ImageButton addnewImageButton;
    private LoggedInViewModel loggedInViewModel;
    private ViewSharePhotoViewModel viewSharePhotoViewModel;

    private Adapter mAdapter;
    private Context mContext;
    private RecyclerView recyclerView;
    private String imageName;
    private byte[] data;


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

        viewSharePhotoViewModel = ViewModelProviders.of(this).get(ViewSharePhotoViewModel.class);
        viewSharePhotoViewModel.init();

        viewSharePhotoViewModel.getUserPictures().observe(this, new Observer<List<UsersPictures>>() {
            @Override
            public void onChanged(List<UsersPictures> usersPictures) {
                mAdapter.notifyDataSetChanged();
            }
        });


    }


    private void initRecyclerView() {
        mAdapter = new Adapter(viewSharePhotoViewModel.getUserPictures().getValue(), getContext());
        recyclerView.setAdapter(mAdapter);
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
        recyclerView = view.findViewById(R.id.recycler_view);

        signOutButton.setText("Log Out");
        addnewTextview.setText("Add a pic");

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

        initRecyclerView();

        return view;
    }


    public void gotoUploadFragment(){
        Navigation.findNavController(getView()).navigate(R.id.action_viewSharePhotoFragment_to_uploadFragment);
    }


}

package com.cobanogluhasan.giproject.Adapter;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cobanogluhasan.giproject.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private static final String TAG = "Adapter";
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mUserEmails = new ArrayList<>();
    private ArrayList<String> mUserLocations = new ArrayList<>();

    private Context mContext;

    public Adapter(Context mContext,ArrayList<String> mImageUrls, ArrayList<String> mUserEmails, ArrayList<String> mUserLocations, Application application) {
        this.mImageUrls = mImageUrls;
        this.mUserEmails = mUserEmails;
        this.mUserLocations = mUserLocations;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        //get the images
        Glide.with(mContext)
                .load(mImageUrls)
                .into(holder.imageView);


        holder.location_user.setText(mUserLocations.get(position));
        holder.email_user.setText(mUserEmails.get(position));
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView location_user,email_user;
        RelativeLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            location_user = itemView.findViewById(R.id.location_user);
            email_user = itemView.findViewById(R.id.email_user);
            parent_layout = itemView.findViewById(R.id.parent_relativelayout);
        }
    }


}

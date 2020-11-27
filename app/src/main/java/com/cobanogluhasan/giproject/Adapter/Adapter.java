package com.cobanogluhasan.giproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cobanogluhasan.giproject.models.UsersPictures;
import com.cobanogluhasan.giproject.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Adapter";
    private List<UsersPictures> muserPictures;

    private Context mContext;

    public Adapter(List<UsersPictures> muserPictures, Context mContext) {
        this.muserPictures = muserPictures;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Set the email and location
        ((ViewHolder)holder).email_user.setText(muserPictures.get(position).getUserEmail());
        ((ViewHolder) holder).location_user.setText(muserPictures.get(position).getUserLocation());

        //set the image
        RequestOptions requestOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(muserPictures.get(position).getImageUrl())
                .into(((ViewHolder) holder).imageView);


    }


    @Override
    public int getItemCount() {
        return muserPictures.size();
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

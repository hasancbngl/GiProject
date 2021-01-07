package com.cobanogluhasan.giproject.Repository;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.cobanogluhasan.giproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class UploadPicturesRepository {
    public void uploadImage(String imageName, byte[] data, final Context context, final View view) {

        //private ImageView imageView;

        //imageView.setDrawingCacheEnabled(true);
        //imageView.buildDrawingCache();
        //Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // byte[] data = baos.toByteArray();

        final UploadTask uploadTask =  FirebaseStorage.getInstance().getReference().child("images").child(imageName).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(context, "Yükleme başarısız!!", Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Toast.makeText(context, "Yükleme Başarılı",Toast.LENGTH_SHORT).show();

                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while ((!uri.isComplete()));
                Uri url = uri.getResult();

                String fileLink = url.toString();

                 updataData(fileLink,view);

            }
        });
    }

    private void updataData(String url, View view,){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");

        String email = mAuth.getCurrentUser().getEmail();

        reference.child(mAuth.getCurrentUser().getUid()).child("email").setValue(email);
        reference.child(mAuth.getCurrentUser().getUid()).child("location").setValue(address);

        try {
            reference.child(mAuth.getCurrentUser().getUid()).child("url").setValue(url);
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Navigation.findNavController(view).navigate(R.id.action_uploadFragment_to_viewSharePhotoFragment);

    }




}

package com.polsri.ican.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.polsri.ican.R;

public class CameraView extends AppCompatActivity {
    ImageView Camera;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);

        Camera = (ImageView) findViewById(R.id.CameraIV);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayoutNode1);
        ShowImage("gs://icanpolsri-a0d38.appspot.com", "Kolam.jpeg");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {@Override
        public void onRefresh() {
            ShowImage("gs://icanpolsri-a0d38.appspot.com", "Kolam2.jpeg");
            swipeRefreshLayout.setRefreshing(false);
        }
        });


    }

    private void ShowImage(String url, String file) {
        final long ONE_MEGABYTE = 1024 * 1024;
        FirebaseStorage storage = FirebaseStorage.getInstance(url);
        StorageReference gsReference = storage.getReference(file);
        gsReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                Camera.setImageBitmap(bmp);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
            }
        });

        //setting Refreshing to false

    }
}
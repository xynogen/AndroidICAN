package com.polsri.ican.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.polsri.ican.R;

public class settings extends AppCompatActivity {
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url = "https://icanpolsri-a0d38-default-rtdb.asia-southeast1.firebasedatabase.app/";
        database = FirebaseDatabase.getInstance(url).getReference("/");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button setting = (Button) findViewById(R.id.restart);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.child("restart").setValue("1");
                Toast.makeText(getBaseContext(), "Device Restarted" , Toast.LENGTH_SHORT ).show();
            }
        });


    }
}
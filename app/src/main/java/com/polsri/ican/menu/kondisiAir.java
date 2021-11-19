package com.polsri.ican.menu;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polsri.ican.R;
import com.polsri.ican.dataClass.Sensor;
import com.polsri.ican.dataClass.Switch;


public class kondisiAir extends AppCompatActivity {
    private String derajat = "°C";
    private String kubik = "m³";
    private TextView PH, Turbidity, Temp, Volume;
        private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kondisi_air);

        PH = (TextView) findViewById(R.id.phAir);
        Turbidity = (TextView) findViewById(R.id.turbidity);
        Temp = (TextView) findViewById(R.id.temp);
        Volume = (TextView) findViewById(R.id.volume);
        String url = "https://icanpolsri-a0d38-default-rtdb.asia-southeast1.firebasedatabase.app/";
        database = FirebaseDatabase.getInstance(url).getReference("/");
        Button siramTanaman = (Button) findViewById(R.id.siramTanaman);

        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CameraView.class);
                startActivity(i);
                finish();
            }
        });


        ValueEventListener sensorListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Sensor sensor = snapshot.child("data").getValue(Sensor.class);
                PH.setText(sensor.getPh());
                Turbidity.setText(sensor.getTurbidity().concat(" NTU"));
                Temp.setText(sensor.getTemp().concat(derajat));
                Volume.setText(sensor.getDistance().concat(kubik));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Data Canceled on Sensor" , Toast.LENGTH_SHORT ).show();
            }
        };
        database.addValueEventListener(sensorListener);

        ValueEventListener switchListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Switch Switch = snapshot.child("switch").getValue(Switch.class);
                if (Switch.getPompa() == "1" || Switch.getBlower() == "1") {
                    siramTanaman.setEnabled(false);
                    siramTanaman.setClickable(false);
                    siramTanaman.setBackgroundColor(getResources().getColor(R.color.gray));
                } else {
                    siramTanaman.setEnabled(true);
                    siramTanaman.setClickable(true);
                    siramTanaman.setBackgroundColor(getResources().getColor(R.color.purple));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Data Canceled on Switch" , Toast.LENGTH_SHORT ).show();
            }
        };
        database.addValueEventListener(switchListener);

        siramTanaman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                database.child("switch").child("pompa").setValue("1");
                Toast.makeText(getBaseContext(), "Pompa Dinyalakan" , Toast.LENGTH_SHORT ).show();
            }
        });
    }
}
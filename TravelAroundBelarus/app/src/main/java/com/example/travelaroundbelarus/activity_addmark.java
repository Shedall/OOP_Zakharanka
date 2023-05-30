package com.example.travelaroundbelarus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.travelaroundbelarus.models.Attraction;
import com.example.travelaroundbelarus.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_addmark extends AppCompatActivity {

    FirebaseDatabase db;
    RelativeLayout root;
    DatabaseReference attraction_db;
    Button addmark,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmark);

        db = FirebaseDatabase.getInstance();
        attraction_db = db.getReference("attraction");

        addmark = findViewById(R.id.batton_add);
        back = findViewById(R.id.batton_back_aam);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

        addmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmark();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                onBackPressed();
            }
        });
    }

    private void addmark() {
        EditText latitude = findViewById(R.id.latitude);
        EditText longitude = findViewById(R.id.longitude);
        EditText title = findViewById(R.id.title);
        EditText adres = findViewById(R.id.adres);
        EditText disription = findViewById(R.id.description);

        double dabllatitude = Double.parseDouble(latitude.getText().toString());
        double dabllongitude = Double.parseDouble(longitude.getText().toString());

        Attraction attraction = new Attraction(dabllatitude,dabllongitude,title.getText().toString(),adres.getText().toString(),disription.getText().toString());
        String attractionKey = attraction.getTitle();
        attraction_db.child(attractionKey).setValue(attraction)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(activity_addmark.this, "Достопримечательность успешно добавлена", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity_addmark.this, "Ошибка добавления", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void onBackPressed(){
        Intent intent = new Intent(activity_addmark.this, Admin_account.class);
        User user = getIntent().getParcelableExtra("user");
        String userID = getIntent().getStringExtra("userid");
        intent.putExtra("user",user);
        intent.putExtra("userid",userID);
        startActivity(intent);
        finish();
    }
}
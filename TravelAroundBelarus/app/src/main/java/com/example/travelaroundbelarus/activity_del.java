package com.example.travelaroundbelarus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.travelaroundbelarus.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_del extends AppCompatActivity {
    FirebaseDatabase db;
    RelativeLayout root;
    DatabaseReference attraction_db;
    Button delmark,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);
        db = FirebaseDatabase.getInstance();
        attraction_db = db.getReference("attraction");

        back = findViewById(R.id.batton_back_del);
        delmark = findViewById(R.id.batton_del);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });
        delmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delmark();
            }
        });
    }

    private void delmark() {
        EditText del = findViewById(R.id.delob);

        String key = del.getText().toString();

        attraction_db.child(key).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(activity_del.this, "объект удален", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity_del.this, "ошибка", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void onBackPressed(){
        Intent intent = new Intent(activity_del.this, Admin_account.class);
        User user = getIntent().getParcelableExtra("user");
        String userID = getIntent().getStringExtra("userid");
        intent.putExtra("user",user);
        intent.putExtra("userid",userID);
        startActivity(intent);
        finish();
    }
}
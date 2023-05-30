package com.example.travelaroundbelarus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelaroundbelarus.models.User;

public class Admin_account extends AppCompatActivity {
    Button btnback,btnmap,btnshowinfo,btnaddmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);
        btnback = findViewById(R.id.batton_back);
        btnmap = findViewById(R.id.btnMap);
        btnaddmark = findViewById(R.id.btnaddMark);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmap();
            }
        });
        btnaddmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMark();
            }
        });
    }

    private void addMark() {
        Intent intent = new Intent(Admin_account.this,activity_addmark.class);
        startActivity(intent);
    }

    private void openmap() {
        //Intent intent = new Intent();
        //intent.setAction(Intent.ACTION_VIEW);
        Intent intent = new Intent(Admin_account.this,MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Admin_account.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

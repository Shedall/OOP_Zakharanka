package com.example.travelaroundbelarus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.travelaroundbelarus.models.User;

public class personal_account extends AppCompatActivity {

    Button btnback,btnmap,btnshowinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_account);
        btnback = findViewById(R.id.batton_back);
        btnshowinfo = findViewById(R.id.showinfo);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnshowinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showinfouser();
            }
        });
    }

    private void showinfouser() {
        User user = getIntent().getParcelableExtra("user");
        Intent intent = new Intent(personal_account.this, UserInfoactivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(personal_account.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
package com.example.travelaroundbelarus;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.travelaroundbelarus.models.User;

public class UserInfoactivity extends AppCompatActivity {
    Button btnback;
   // User user = getIntent().getParcelableExtra("user");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infoactivity);
        btnback = findViewById(R.id.batton_back);

        shouinfouser();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(UserInfoactivity.this, personal_account.class);
        startActivity(intent);
        finish();
    }
    private void shouinfouser()
    {
            User user = getIntent().getParcelableExtra("user");
            TextView name_view = findViewById(R.id.name_us);
            TextView email_view = findViewById(R.id.Email_us);
            TextView lastname_view = findViewById(R.id.lastname_us);
            TextView phone_view = findViewById(R.id.Phone_us);
            TextView password_view = findViewById(R.id.Password_us);

            name_view.setText(user.getName().toString());
            email_view.setText(user.getEmail().toString());
            lastname_view.setText(user.getLastname().toString());
            phone_view.setText(user.getPhone().toString());
            password_view.setText(user.getPassword().toString());
    }
    //СДЕЛАТЬ СКРЫВАЕМЫЙ ПАРОЛЬ!!!
}
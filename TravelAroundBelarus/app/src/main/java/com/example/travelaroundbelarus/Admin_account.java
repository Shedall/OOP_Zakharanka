package com.example.travelaroundbelarus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelaroundbelarus.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_account extends AppCompatActivity {
    Button btnback,btnmap,btnshowalluser,btnaddmark,btndelmark;
    DatabaseReference us = FirebaseDatabase.getInstance().getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);
        btnback = findViewById(R.id.batton_back);
        btnmap = findViewById(R.id.btnMap);
        btnaddmark = findViewById(R.id.btnaddMark);
        btndelmark = findViewById(R.id.btndelobgect);
        btnshowalluser = findViewById(R.id.btnshowalluser);

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
        btndelmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delmark();
            }
        });
        btnshowalluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showalluser();
            }
        });
    }

    private void showalluser() {
        us.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> userlist = new ArrayList<>();
                 for (DataSnapshot usersnap : snapshot.getChildren())
                 {
                     User user = usersnap.getValue(User.class);

                     userlist.add(user.getEmail());
                 }
                 showuserList(userlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showuserList(List<String> userlist) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Admin_account.this);
        builder.setTitle("Список пользователей");
        builder.setItems(userlist.toArray(new String[0]), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Обработка нажатия на элемент списка
                String usermail = userlist.get(which);
                // Ваш код для действий, связанных с выбранным пользователем
                AlertDialog.Builder dialog_2 = new AlertDialog.Builder(Admin_account.this);
                dialog_2.setTitle("Удаление:");
                dialog_2.setMessage("Вы уверены,что хотите удалить данного пользователя?");
                LayoutInflater inflater = LayoutInflater.from(getBaseContext());
                View empty_window = inflater.inflate(R.layout.empty, null);
                dialog_2.setView(empty_window);
                dialog_2.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog_2.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Выполнение запроса для поиска пользователя по email
                        Query query = us.orderByChild("email").equalTo(usermail);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    // Пользователь найден
                                    String userKey = snapshot.getKey(); // Получение ключа пользователя
                                    us.child(userKey).removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(Admin_account.this, "Ошибка удаления", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                dialog_2.show();

            }
        });
        builder.show();
    }

    private void delmark() {
        Intent intent = new Intent(Admin_account.this,activity_del.class);
        startActivity(intent);
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

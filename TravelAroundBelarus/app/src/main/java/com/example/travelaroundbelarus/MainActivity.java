package com.example.travelaroundbelarus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.travelaroundbelarus.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnRegistration,btnexit;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.button_sign_in);
        btnRegistration = findViewById(R.id.button_registration);
        btnexit = findViewById(R.id.button_exit);

        root = findViewById(R.id.root_element);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("users");

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistrationWindow();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showSign_in_Window();}
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }

    private void showSign_in_Window() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти:");
        dialog.setMessage("Введите данные для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window =inflater.inflate(R.layout.sign_in_window ,null);
        dialog.setView(sign_in_window);

        final TextView password = sign_in_window.findViewById(R.id.Password);
        final TextView email = sign_in_window.findViewById(R.id.Email);

        dialog.setNegativeButton("отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which){
                dialogInterface.dismiss();
            }
        });


        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root,"Введите вашу почту",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(password.getText().toString().length() < 5){
                    Snackbar.make(root,"Введите пароль(не менее 5-и символов",Snackbar.LENGTH_LONG).show();
                    return;
                }
                //авторизация пользователя

                auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                // Получение текущего пользователя Firebase
                                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (firebaseUser != null) {
                                    String userId = firebaseUser.getUid();
                                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

                                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            // Получение данных пользователя из базы данных
                                            User user = dataSnapshot.getValue(User.class);
                                            if (user != null) {
                                                // Передача данных пользователя на страницу personal_account
                                                Intent intent = new Intent(MainActivity.this, personal_account.class);
                                                intent.putExtra("user", user);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Snackbar.make(root, "Ошибка при получении данных пользователя", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(root, "Ошибка авторизации: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                            }
                        });

                {

                }

            }
        });

        dialog.show();
    }

    private void showRegistrationWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Регистрация:");
        dialog.setMessage("Введите свои данные");

        LayoutInflater inflater = LayoutInflater.from(this);
        View registration_window =inflater.inflate(R.layout.registration_window,null);
        dialog.setView(registration_window);

        final TextView password = registration_window.findViewById(R.id.Password);
        final TextView name = registration_window.findViewById(R.id.name);
        final TextView lastname = registration_window.findViewById(R.id.lastname);
        final TextView email = registration_window.findViewById(R.id.Email);
        final TextView phone = registration_window.findViewById(R.id.Phone);

        dialog.setNegativeButton("отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which){
                dialogInterface.dismiss();
            }
        });


        dialog.setPositiveButton("Зарегистрировать", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root,"Введите вашу почту",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(root,"Введите ваше имя",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(lastname.getText().toString())){
                    Snackbar.make(root,"Введите вашу фамилию",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(password.getText().toString().length() < 5){
                    Snackbar.make(root,"Введите пароль(не менее 5-и символов",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(root,"Введите ваш номер телефона",Snackbar.LENGTH_LONG).show();
                    return;
                }
                //регисьрация пользователя
                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User(email.getText().toString(), password.getText().toString(), name.getText().toString(),
                                        lastname.getText().toString(), phone.getText().toString());
                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                String userId = currentUser.getUid();

                                users.child(userId)
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Snackbar.make(root, "пользователь успешно добавлен!", Snackbar.LENGTH_LONG).show();
                                            }
                                        });
                            }
                                }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Snackbar.make(root,"ошибка регистрации" + e.getMessage(),Snackbar.LENGTH_LONG).show();
                                }
                        });


            }
        });

        dialog.show();
    }
}
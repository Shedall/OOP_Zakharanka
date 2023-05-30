package com.example.travelaroundbelarus;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.travelaroundbelarus.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInfoactivity extends AppCompatActivity {
    Button btnback,btnedit,btndel;

    ConstraintLayout root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infoactivity);
        btnback = findViewById(R.id.batton_back);
        btndel = findViewById(R.id.batton_del);
        btnedit = findViewById(R.id.batton_edit);
        root = findViewById(R.id.root_element);

        shouinfouser();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onuserdel();
            }
        });
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserEdit();
            }
        });
    }
    @Override
    public void onBackPressed(){
        User user = getIntent().getParcelableExtra("user");
        if(!user.isAdmin()) {
            Intent intent = new Intent(UserInfoactivity.this, personal_account.class);
            String userID = getIntent().getStringExtra("userid");
            intent.putExtra("user", user);
            intent.putExtra("userid", userID);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(UserInfoactivity.this, Admin_account.class);
            String userID = getIntent().getStringExtra("userid");
            intent.putExtra("user", user);
            intent.putExtra("userid", userID);
            startActivity(intent);
            finish();
        }
    }

    public void onuserdel(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Удаление:");
        dialog.setMessage("Вы уверены,что хотите удалить аккаунт?");
        LayoutInflater inflater = LayoutInflater.from(this);
        View empty_window = inflater.inflate(R.layout.empty, null);
        dialog.setView(empty_window);
        dialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userID = getIntent().getStringExtra("userid");
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userID);
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                firebaseUser.delete();
                userRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(root, "Пользователь успешно удален", Snackbar.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root, "Ошибка удаления пользователя", Snackbar.LENGTH_LONG).show();
                    }
                });

                Intent intent = new Intent(UserInfoactivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
    }
    public void onUserEdit() {
        User user = getIntent().getParcelableExtra("user");
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Редактирование:");
        dialog.setMessage("Измените данные");
        LayoutInflater inflater = LayoutInflater.from(this);
        View edit_window = inflater.inflate(R.layout.userinfoedit, null);
        dialog.setView(edit_window);
        EditText editEmail = edit_window.findViewById(R.id.Email_edit);
        editEmail.setHint("EMAIL");
        EditText editName = edit_window.findViewById(R.id.name_edit);
        editName.setHint("ИМЯ");
        EditText editLastname = edit_window.findViewById(R.id.lastname_edit);
        editLastname.setHint("ФАМИЛИЯ");
        EditText editPhone = edit_window.findViewById(R.id.Phone_edit);
        editPhone.setHint("ТЕЛЕФОН");

        EditText editEmailText = edit_window.findViewById(R.id.Email_edit);
        String TextEmail = user.getEmail();
        editEmailText.setText(TextEmail);
        EditText editnameText = edit_window.findViewById(R.id.name_edit);
        String TextName = user.getName();
        editnameText.setText(TextName);
        EditText editLastNameText = edit_window.findViewById(R.id.lastname_edit);
        String TextLastName = user.getLastname();
        editLastNameText.setText(TextLastName);
        EditText editPhoneText = edit_window.findViewById(R.id.Phone_edit);
        String TextPhone = user.getPhone();
        editPhoneText.setText(TextPhone);
        EditText editPasswordText = edit_window.findViewById(R.id.Password_edit);
        String TextPassword = user.getPassword();
        editPasswordText.setText(TextPassword);

        TextView Email_ed = edit_window.findViewById(R.id.Email_edit);
        TextView Name_ed = edit_window.findViewById(R.id.name_edit);
        TextView Lastname_ed = edit_window.findViewById(R.id.lastname_edit);
        TextView phone_ed = edit_window.findViewById(R.id.Phone_edit);
        TextView password_ed = edit_window.findViewById(R.id.Password_edit);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(Email_ed.getText().toString())){
                    Snackbar.make(root,"Введите вашу почту",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(Name_ed.getText().toString())){
                    Snackbar.make(root,"Введите ваше имя",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(Lastname_ed.getText().toString())){
                    Snackbar.make(root,"Введите вашу фамилию",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(password_ed.getText().toString().length() < 5){
                    Snackbar.make(root,"Введите пароль(не менее 5-и символов",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(phone_ed.getText().toString())){
                    Snackbar.make(root,"Введите ваш номер телефона",Snackbar.LENGTH_LONG).show();
                    return;
                }
                String userID = getIntent().getStringExtra("userid");
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (firebaseUser != null) {
                    // Редактирование полей в Firebase Authentication
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(Email_ed.getText().toString()) // Измените на новое имя
                            .build();
                    firebaseUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Snackbar.make(root, "пользователь успешно обновлен", Snackbar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(root, "Ошибка", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    });
                    firebaseUser.updateEmail(Email_ed.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Snackbar.make(root, "пользователь успешно обновлен", Snackbar.LENGTH_LONG).show();
                                    } else {
                                        Snackbar.make(root, "Ошибка", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                    firebaseUser.updatePassword(password_ed.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });

                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userID);
                    userRef.child("email").setValue(Email_ed.getText().toString());
                    userRef.child("name").setValue(Name_ed.getText().toString());
                    userRef.child("password").setValue(password_ed.getText().toString());
                    userRef.child("lastname").setValue(Lastname_ed.getText().toString());
                    userRef.child("phone").setValue(phone_ed.getText().toString());

                    user.setEmail(Email_ed.getText().toString());
                    user.setPassword(password_ed.getText().toString());
                    user.setPhone(phone_ed.getText().toString());
                    user.setName(Name_ed.getText().toString());
                    user.setLastname(Lastname_ed.getText().toString());

                    shouinfouser(user);
                }
            }

        });

        dialog.show();
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

    private void shouinfouser(User user)
    {
        //User user = getIntent().getParcelableExtra("user");
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
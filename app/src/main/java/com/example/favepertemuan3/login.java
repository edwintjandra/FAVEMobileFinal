package com.example.favepertemuan3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    Button btnlogin;
    Button back;
    EditText email;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("lastLogin",MODE_PRIVATE);
        btnlogin=findViewById(R.id.login);
        email=findViewById(R.id.email);
        builder = new AlertDialog.Builder(login.this);
        back=findViewById(R.id.back);

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
            if (mFirebaseUser != null){
                Toast.makeText(login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(login.this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(login.this, "Cannot logged in",Toast.LENGTH_SHORT).show();
            }
        };

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( login.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().length() != 0) {
                    String email = ((EditText) findViewById(R.id.email)).getText().toString();
                    String password = ((EditText) findViewById(R.id.password)).getText().toString();

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login.this, task ->
                    {
                        if (!task.isSuccessful()) {
                            Toast.makeText(login.this, "Login Error, Please Login again", Toast.LENGTH_SHORT).show();
                        } else {
                            editor = sharedPreferences.edit();
                            editor.putString("username", email.toString());
                            editor.apply();

                            builder.setTitle("Confirmation").setMessage("Are you sure?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(login.this, MainActivity.class);
                                    startActivity(intent);

                                    Toast.makeText(login.this, "successfully login", Toast.LENGTH_SHORT).show();
                                }
                            });

                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(login.this, "Action cancelled", Toast.LENGTH_SHORT).show();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                    });
                }
            }
        });
    }}

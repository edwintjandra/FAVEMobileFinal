package com.example.favepertemuan3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class register extends AppCompatActivity {
    Button regisbtn;
    Button back;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regisbtn=findViewById(R.id.register);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( register.this, MainActivity.class);
                startActivity(i);
            }
        });

        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                String name = ((EditText) findViewById(R.id.username)).getText().toString();
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
                String address = ((EditText) findViewById(R.id.address)).getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, task -> {
                    if (!task.isSuccessful()){
                        Toast.makeText(context, "Register Failed",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Register Success",Toast.LENGTH_SHORT).show();
                        Intent home = new Intent(register.this, MainActivity.class);
                        startActivity(home);
                    }
                });


            }
        });
    }
}
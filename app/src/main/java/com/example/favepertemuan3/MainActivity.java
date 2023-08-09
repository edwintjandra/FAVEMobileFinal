package com.example.favepertemuan3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.favepertemuan3.R;
import com.example.favepertemuan3.FavoriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navbar);
        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.favorites) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FavoriteFragment()).commit();
                return true;
            }else if(item.getItemId() == R.id.list) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ListFragment()).commit();
                 return true;
            }else if(item.getItemId() == R.id.home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
                return true;
            }
           return false;
        });
        bottomNavigationView.setSelectedItemId(R.id.home);
     }
}
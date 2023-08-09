package com.example.favepertemuan3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.favepertemuan3.login;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeFragment extends Fragment {
    Button loginbtn;
    Button regisbtn;
    Button logout;
    TextView username;
    SharedPreferences sharedPreferences;
    FirebaseUser user;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        loginbtn = view.findViewById(R.id.btnlogin);
        regisbtn = view.findViewById(R.id.btnregister);
        username = view.findViewById(R.id.username);
        sharedPreferences = getActivity().getSharedPreferences("lastLogin", Context.MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        logout=view.findViewById(R.id.logout);

        if(user==null) {
            logout.setVisibility(View.INVISIBLE);
            loginbtn.setVisibility(View.VISIBLE);
            regisbtn.setVisibility(View.VISIBLE);
            username.setText("username");
        }else {
            logout.setVisibility(View.VISIBLE);
            loginbtn.setVisibility(View.INVISIBLE);
            regisbtn.setVisibility(View.INVISIBLE);
            username.setText(user.getEmail());
        }

       // String usernameText = sharedPreferences.getString("username", null);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeFragment.this.getActivity(), login.class);
                startActivity(i);
            }
        });

        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                Intent i = new Intent( HomeFragment.this.getActivity(), register.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(HomeFragment.this.getActivity(), login.class);
                startActivity(i);

            }
        });



        return view;
    }
}
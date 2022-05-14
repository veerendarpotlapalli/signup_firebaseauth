package com.example.signup_firebaseauth;

import static com.example.signup_firebaseauth.R.color.purple_200;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContentProviderCompat;
import androidx.core.content.ContentResolverCompat;
import androidx.core.view.ContentInfoCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class logout extends AppCompatActivity {
    Button logout;
   // ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            getWindow().setNavigationBarColor(getResources().getColor(R.color.purple_200));
            getWindow().setStatusBarColor(getResources().getColor(R.color.purple_200));
            setContentView(R.layout.activity_logout);
           // actionBar = getSupportActionBar();
           // actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(purple_200)));

            logout = findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                }
            }); //logout onclick listener
        }catch (Exception e){

        } //catch
    } //oncreate
} // main
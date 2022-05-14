package com.example.signup_firebaseauth;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    EditText phone;
    Button sendotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            phone = findViewById(R.id.phone);
            sendotp = findViewById(R.id.sendotp);

            sendotp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        if (phone.getText().toString().isEmpty()) {
                        phone.setError("phone number required");
                        }
                        else if (phone.getText().toString().length() != 13) {
                        phone.setError("Enter Mobile Number");
                        }
                        else {
                            String n = phone.getText().toString();
                            Intent intent = new Intent(MainActivity.this, otp_verify.class);
                            intent.putExtra("mobile",n);
                            startActivity(intent);
                            phone.setText("+91");
                        } //else
                    }catch (Exception e){
                        e.getMessage();
                    } // catch
                } // onclick view
            }); //setotp onclick

    } //oncreate
} //main
package com.example.signup_firebaseauth;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp_verify extends AppCompatActivity {

    EditText enterotp;
    Button verify;
    String phoneNumber;
    FirebaseAuth mAuth;
    String otpid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_otp_verify);

            enterotp = findViewById(R.id.enterotp);
            verify = findViewById(R.id.verify);
            phoneNumber = getIntent().getStringExtra("mobile");
            mAuth = FirebaseAuth.getInstance();

            initiateotp();

            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (enterotp.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(), "Blank field cannot be processed", Toast.LENGTH_SHORT).show();

                    else if (enterotp.getText().toString().length() != 6)
                        Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();

                    else {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, enterotp.getText().toString());
                        signInWithPhoneAuthCredential(credential);
                    }
                } //verfy on
            });
        }catch (Exception e){

        } //catch
    } //oncreate

    private void initiateotp()
    {
        try {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,
                    60L,
                    TimeUnit.SECONDS,
                    this,
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    otpid = s;  // if sim is not in your phone
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    signInWithPhoneAuthCredential(phoneAuthCredential);

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }); //phoneauthprovider

        }catch (Exception e){

        } //catch
    } //initiateotp

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        try {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(otp_verify.this, logout.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "signup code error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }); //addOnCompleteListener
        }catch(Exception e){

        }
        } //signInWithPhoneAuthCredential

} //main
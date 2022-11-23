package com.example.pushfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigupApp extends AppCompatActivity {
    private EditText edttk,edtpass;
    private Button btnSigup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup_app);
        edttk = findViewById(R.id.edt_tdn);
        edtpass = findViewById(R.id.edt_mk);
        btnSigup = findViewById(R.id.bt_logupme);
        btnSigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSigup();
            }
        });
    }
    private  void onClickSigup(){
        String strEmail = edttk.getText().toString().trim();
        String strPass = edtpass.getText().toString().trim();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(strEmail, strPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(SigupApp.this,MainActivity.class);
                            startActivity(intent);
                            finishAffinity();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SigupApp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
}
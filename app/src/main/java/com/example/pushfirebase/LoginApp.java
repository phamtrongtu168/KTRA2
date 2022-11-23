package com.example.pushfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginApp extends AppCompatActivity {
    EditText edtGmail,edtPass;
    Button btnSigUp;
    TextView tvSigup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);
        tvSigup = findViewById(R.id.tv_sigup);
        edtGmail = findViewById(R.id.edt_tdn);
        edtPass = findViewById(R.id.edt_mk);
        btnSigUp = findViewById(R.id.bt_loginme);
        btnSigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSigIn();
            }
        });

        initListener();
    }

    private void onClickSigIn() {
        String strEmail = edtGmail.getText().toString().trim();
        String strPass = edtPass.getText().toString().trim();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(strEmail, strPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(LoginApp.this,MainActivity.class);
                            startActivity(intent);
                            finishAffinity();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LoginApp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void initUi(){

    }
    private  void initListener(){
        tvSigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginApp.this,SigupApp.class);
                startActivity(intent);
            }
        });
    }
}
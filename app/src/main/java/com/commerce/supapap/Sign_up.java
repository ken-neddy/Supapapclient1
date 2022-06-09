package com.commerce.supapap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_up extends AppCompatActivity {
    private TextView ediTextEmail;
    private TextView editTextTextPassword;
    private TextView editTextConfirm;
    private Button googlebtn;
    private Button facebookbtn;
    private Button createAccountbtn;
    private TextView gotAccounttxt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        googlebtn = (Button) findViewById(R.id.googlebtn);
        facebookbtn = (Button) findViewById(R.id.facebookbtn);
        createAccountbtn = (Button) findViewById(R.id.createAccountbtn);
        gotAccounttxt = findViewById(R.id.gotAccounttxt);
        ediTextEmail = findViewById(R.id.editTextEmail);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextConfirm = findViewById(R.id.editTextConfirm);
        mAuth = FirebaseAuth.getInstance();

        gotAccounttxt.setOnClickListener(view -> {
            startActivity(new Intent(Sign_up.this, Log_in.class));
        });

        createAccountbtn.setOnClickListener (view -> {
            createUser();
        });
    }

    private void createUser(){
        String email=ediTextEmail.getText().toString();
        String password = editTextTextPassword.getText().toString();


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ediTextEmail.setError("provide a valid email");
            ediTextEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(email)){
            ediTextEmail.setError("email required");
            ediTextEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            editTextTextPassword.setError("password required");
            editTextTextPassword.requestFocus();
        }else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        User user = new User(email);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .setValue(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                        }else {
                                            Toast.makeText(Sign_up.this, "Error!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                        startActivity(new Intent(Sign_up.this,Log_in.class));
                    }else {
                        Toast.makeText(Sign_up.this, "Error❌" + "-" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}


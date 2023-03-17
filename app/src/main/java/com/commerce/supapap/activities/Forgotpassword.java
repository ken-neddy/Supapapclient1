package com.commerce.supapap.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.commerce.supapap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpassword extends AppCompatActivity {
    private EditText enter_your_email_forgot;
    private Button resetpassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        enter_your_email_forgot = findViewById(R.id.enter_your_email_forgot);
        resetpassword=findViewById(R.id.resetpassword);
        auth = FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPasswordMethod();
            }
        });
    }

    private void resetPasswordMethod(){
        String email = enter_your_email_forgot.getText().toString().trim();
        if (email.isEmpty()){
            enter_your_email_forgot.setError("email required");
            enter_your_email_forgot.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            enter_your_email_forgot.setError("provide a valid email");
            enter_your_email_forgot.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Forgotpassword.this, "Check your email to reset password", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Forgotpassword.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
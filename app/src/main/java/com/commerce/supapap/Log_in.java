package com.commerce.supapap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class Log_in extends AppCompatActivity {
    private TextView editTextTextEmail;
    private TextView editTextTextPassword;
    private Button googlebtn;
    private Button facebookbtn;
    private Button log_inbtn;
    private TextView createaccount,retailerLogin;
    private TextView forgotpasswordtextView;
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 007;
    private SpinKitView spinKitView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        googlebtn = (Button) findViewById(R.id.googlebtn);
        facebookbtn = (Button) findViewById(R.id.facebookbtn);
        log_inbtn = (Button) findViewById(R.id.log_inbtn);
        editTextTextEmail = findViewById(R.id.editTextTextEmail);
        createaccount = findViewById(R.id.createaccount);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        retailerLogin = findViewById(R.id.retailerLogin);
        forgotpasswordtextView = findViewById(R.id.forgotpasswordtextView);
        mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();
        spinKitView = findViewById(R.id.spin_kit);
        spinKitView.setVisibility(View.VISIBLE);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_in.this, Sign_up.class);
                startActivity(intent);
            }
        });

        retailerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_in.this, Retailerlogin.class);
                startActivity(intent);
            }
        });

        forgotpasswordtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Log_in.this,Forgotpassword.class));
            }
        });


        log_inbtn.setOnClickListener(view -> {
            loginUser();
            spinKitView.setVisibility(View.VISIBLE);
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(Log_in.this, "success✔", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Log_in.this, Dashboard.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(Log_in.this, "failed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull FacebookException e) {
                        Toast.makeText(Log_in.this, "error", Toast.LENGTH_LONG).show();
                    }
                });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinKitView.setVisibility(View.VISIBLE);
                switch (v.getId()) {
                    case R.id.googlebtn:
                        signIn();
                        break;
                }
            }

        });
        facebookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(Log_in.this, Arrays.asList("public_profile"));
            }
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Log_in.this, Dashboard.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode());

        }
    }
    private void loginUser(){
        String email=editTextTextEmail.getText().toString();
        String password = editTextTextPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            editTextTextEmail.setError("email required");
            editTextTextPassword.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            editTextTextEmail.setError("password required");
            editTextTextPassword.requestFocus();
        }else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Log_in.this, "success✔", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Log_in.this, Dashboard.class));
                        spinKitView.setVisibility(View.VISIBLE);
                    }else {
                        Toast.makeText(Log_in.this, "Error❌:"+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                        spinKitView.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
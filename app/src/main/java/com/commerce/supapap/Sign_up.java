package com.commerce.supapap;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;


public class Sign_up extends AppCompatActivity {
    private TextView ediTextEmail;
    private TextView editTextTextPassword;
    private TextView editTextConfirm,retailerlogin;
    private Button googlebtn;
    private Button facebookbtn;
    private Button createAccountbtn;
    private TextView gotAccounttxt;
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 007;
    private static final int REQ_ONE_TAP = 2;
    private boolean showOneTapUI = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_sign_up);

        googlebtn = (Button) findViewById(R.id.googlebtn);
        facebookbtn = (Button) findViewById(R.id.facebookbtn);
        createAccountbtn = (Button) findViewById(R.id.createAccountbtn);
        gotAccounttxt = findViewById(R.id.gotAccounttxt);
        ediTextEmail = findViewById(R.id.editTextEmail);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextConfirm = findViewById(R.id.editTextConfirm);
        mAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();
        retailerlogin = findViewById(R.id.retailerlogin);



                LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(Sign_up.this, "success✔", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Sign_up.this, Dashboard.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(Sign_up.this, "failed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull FacebookException e) {
                        Toast.makeText(Sign_up.this, "error", Toast.LENGTH_LONG).show();
                    }
                });


        gotAccounttxt.setOnClickListener(view -> {
            startActivity(new Intent(Sign_up.this, Log_in.class));
        });

        createAccountbtn.setOnClickListener(view -> {
            createUser();
        });
        retailerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_up.this, Retailerlogin.class);
                startActivity(intent);
            }
        });


        facebookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginManager.getInstance().logInWithReadPermissions(Sign_up.this, Arrays.asList("public_profile"));
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
                switch (v.getId()) {
                    case R.id.googlebtn:
                        signIn();
                        break;
                }
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
            Intent intent = new Intent(Sign_up.this, Dashboard.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode());

        }
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
                                            Intent intent = new Intent(Sign_up.this, Dashboard.class);
                                            startActivity(intent);
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


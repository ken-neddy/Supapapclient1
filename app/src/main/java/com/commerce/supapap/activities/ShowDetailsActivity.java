package com.commerce.supapap.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import com.commerce.supapap.R;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.facebook.FacebookSdk;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.commerce.supapap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ShowDetailsActivity extends AppCompatActivity {
    private TextView addtocartbtn;
    private TextView titleTxt;
    private TextView priceTxt;
    private TextView descriptiontxt;
    private TextView numberofOrdertxt;
    private TextView minusbtn;
    private TextView plusbtn;
    private TextView totalpricetxt;
    private ImageView productimg;
    int count;
    String userId;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        String name = getIntent().getStringExtra("NAME");
        String productkey = getIntent().getStringExtra("PRODUCTKEY");
        String price = getIntent().getStringExtra("FEE");
        String description = getIntent().getStringExtra("DESCRIPTION");
        int image = getIntent().getIntExtra("PIC",0);
        String image1 = getIntent().getStringExtra("PIC");
        Log.d("tag4", "Value is: " + image1);
        Log.d("tagid", "Value is: " + productkey);

        addtocartbtn = findViewById(R.id.addtocartbtn);
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        descriptiontxt = findViewById(R.id.descriptiontxt);
        numberofOrdertxt = findViewById(R.id.numberofOrdertxt);
        minusbtn = findViewById(R.id.minusbtn);
        plusbtn = findViewById(R.id.plusbtn);
        productimg = findViewById(R.id.productimg);
        totalpricetxt = findViewById(R.id.totalpricetxt);

        titleTxt.setText(name);
        priceTxt.setText(price);
        descriptiontxt.setText(description);
       // productimg.setImageResource(Integer.parseInt(image1));

        Glide.with(productimg.getContext())
                .load(image1)
                .placeholder(R.drawable.ic_launcher_background)
                .circleCrop()
                .error(R.drawable.ic_launcher_background)
                .into(productimg);

        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                numberofOrdertxt.setText(String.valueOf(count));
            }
        });

        minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count <=1){
                    Toast.makeText(ShowDetailsActivity.this, "Not Applicable", Toast.LENGTH_SHORT).show();
                }
                if(count >=100000){
                    Toast.makeText(ShowDetailsActivity.this, "🛑Limit reached.Contact admin", Toast.LENGTH_SHORT).show();
                }

                else {

                    count--;
                    numberofOrdertxt.setText(String.valueOf(count));
                }
            }
        });

        numberofOrdertxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                    String p = String.valueOf(s);
                if(p.equals("")){
                    totalpricetxt.setText("0");
                }
                else {
                    Integer q = parseInt(p);
                    Log.d("tag", "Value is: " + q);
                    Integer r = parseInt(price);
                    Integer t = r * q;
                    Log.d("tag1", "Value is: " + t);
                    totalpricetxt.setText(String.valueOf(t));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nootaddtocartmethod();
                prctxtaddtocartmethod();
                ttprtxtaddtocartmethod();
                prdctkyaddtocartmethod();


            }
        });
      //  initView();
        //getbundle();
    }

    private void nootaddtocartmethod() {
        TextView numberofOrdertxttv = findViewById(R.id.numberofOrdertxt);
        String x = numberofOrdertxt.getText().toString();
        String productkey = getIntent().getStringExtra("PRODUCTKEY");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        String currentUserId = null;
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            currentUserId=currentUser.getUid();
        }
           databaseReference.child("carts").child(currentUserId).child(productkey).child("numberoforder").setValue(x);
        }

    private void prctxtaddtocartmethod() {
        TextView priceTxt = findViewById(R.id.priceTxt);
        String y = priceTxt.getText().toString();
        String productkey = getIntent().getStringExtra("PRODUCTKEY");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        String currentUserId = null;
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            currentUserId=currentUser.getUid();
        }
        databaseReference.child("carts").child(currentUserId).child(productkey).child("productprice").setValue(y);


    }
    private void ttprtxtaddtocartmethod() {
        TextView totalpricetxt = findViewById(R.id.totalpricetxt);
        String z = totalpricetxt.getText().toString();
        String productkey = getIntent().getStringExtra("PRODUCTKEY");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        String currentUserId = null;
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            currentUserId=currentUser.getUid();
        }
        databaseReference.child("carts").child(currentUserId).child(productkey).child("totalprice").setValue(z);
    }
    private void prdctkyaddtocartmethod() {
        String productkey = getIntent().getStringExtra("PRODUCTKEY");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        String currentUserId = null;
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            currentUserId=currentUser.getUid();
        }
       databaseReference.child("carts").child(currentUserId).child(productkey).child("productkey").setValue(productkey);

    }
    
    }

//    private void getbundle() {
//    }
//
//    private void initView() {
//        addtocartbtn = findViewById(R.id.addtocartbtn);
//        titleTxt = findViewById(R.id.titleTxt);
//        priceTxt = findViewById(R.id.priceTxt);
//        descriptiontxt = findViewById(R.id.descriptiontxt);
//        NumberofOrdertxt = findViewById(R.id.NumberofOrdertxt);
//        minusbtn = findViewById(R.id.minusbtn);
//        plusbtn = findViewById(R.id.plusBtn);
//    }


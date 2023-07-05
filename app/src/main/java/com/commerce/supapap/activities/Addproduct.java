package com.commerce.supapap.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.commerce.supapap.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class Addproduct extends AppCompatActivity {

    StorageReference storageReference;
    DatabaseReference databaseReference;
    String userId;
    private TextView productName,tv8,numberinstock;
    private Button uploaddata,addImageBtn,minusinadd,addinadd;
    FirebaseAuth mAuth;
    int count=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        Spinner categorySpinner= findViewById(R.id.categorySpinner);
        ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>(Addproduct.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nameCategorySpinner));
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categorySpinnerAdapter);

        productName = findViewById(R.id.productName);
        tv8 = findViewById(R.id.tv8);
        uploaddata = findViewById(R.id.uploaddata);
        minusinadd = findViewById(R.id.minusinadd);
        addinadd = findViewById(R.id.addinadd);
        numberinstock = findViewById(R.id.numberinstock);
        /*addImageBtn = findViewById(R.id.addImageBtn);*/
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        mAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        Intent j = getIntent();
        String key1 = j.getStringExtra(Productpic.EXTRA_NAME);
        tv8.setText(key1);
        Log.d("funguo", "This is the key... " + key1);

        addinadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    count++;
                    numberinstock.setText(String.valueOf(count));
            }
        });

        minusinadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count <=1){
                    Toast.makeText(Addproduct.this, "Not Applicable", Toast.LENGTH_SHORT).show();
                }

                else {
                    count--;
                    numberinstock.setText(String.valueOf(count));
                }
            }
        });

        uploaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savenamemethod();
                savedescriptionmethod();
                savepricemethod();
                savecategorymethod();
                saveproducttypemethod();
                savenumbermethod();
                savekey();
                Toast.makeText(Addproduct.this, "Saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Addproduct.this, Productpic.class);
                startActivity(intent);
            }
        });

        /*addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addproduct.this, Productpic.class);
                startActivity(intent);
            }
        });*/

    }

    private void savenamemethod() {
        EditText editText = findViewById(R.id.productName);
        String productName = editText.getText().toString().trim();
        Intent j = getIntent();
        String key1 = j.getStringExtra(Productpic.EXTRA_NAME);
        if (TextUtils.isEmpty(productName)) {
            editText.setError("please fill");
            editText.requestFocus();
        } else{
            //   storageReference = FirebaseStorage.getInstance().getReference();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            String upload = databaseReference.push().getKey();
            databaseReference.child("products").child(key1).child("name").setValue(productName);
           // Toast.makeText(Addproduct.this, "Saved", Toast.LENGTH_LONG).show();
        }
    }

    private void savekey() {

        Intent j = getIntent();
        String key1 = j.getStringExtra(Productpic.EXTRA_NAME);
            //   storageReference = FirebaseStorage.getInstance().getReference();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            String upload = databaseReference.push().getKey();
            databaseReference.child("products").child(key1).child("productkey").setValue(key1);
            // Toast.makeText(Addproduct.this, "Saved", Toast.LENGTH_LONG).show();

    }

    private void saveproducttypemethod() {
        EditText editText = findViewById(R.id.productType);
        String productName = editText.getText().toString().trim();
        Intent j = getIntent();
        String key1 = j.getStringExtra(Productpic.EXTRA_NAME);
        if (TextUtils.isEmpty(productName)) {
            editText.setError("please fill");
            editText.requestFocus();
        } else{
            //   storageReference = FirebaseStorage.getInstance().getReference();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            String upload = databaseReference.push().getKey();
            databaseReference.child("products").child(key1).child("type").setValue(productName);
           // Toast.makeText(Addproduct.this, "Saved", Toast.LENGTH_LONG).show();
        }
    }
    private void savecategorymethod() {
        Spinner spinner = findViewById(R.id.categorySpinner);
        String productName = spinner.getSelectedItem().toString().trim();
        Intent j = getIntent();
        String key1 = j.getStringExtra(Productpic.EXTRA_NAME);
        if (TextUtils.isEmpty(productName)) {
            spinner.requestFocus();
        } else{
            //   storageReference = FirebaseStorage.getInstance().getReference();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            String upload = databaseReference.push().getKey();
            databaseReference.child("products").child(key1).child("category").setValue(productName);
          //  Toast.makeText(Addproduct.this, "Saved", Toast.LENGTH_LONG).show();
        }
    }
    private void savepricemethod() {
        EditText editText = findViewById(R.id.price);
        String productName = editText.getText().toString().trim();
        Intent j = getIntent();
        String key1 = j.getStringExtra(Productpic.EXTRA_NAME);
        if (TextUtils.isEmpty(productName)) {
            editText.setError("please fill");
            editText.requestFocus();
        } else{
            //   storageReference = FirebaseStorage.getInstance().getReference();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            String upload = databaseReference.push().getKey();
            databaseReference.child("products").child(key1).child("price").setValue(productName);
          //  Toast.makeText(Addproduct.this, "Saved", Toast.LENGTH_LONG).show();
        }
    }

    private void savenumbermethod() {
        TextView textView = findViewById(R.id.numberinstock);
        String productName = textView.getText().toString().trim();
        Intent j = getIntent();
        String key1 = j.getStringExtra(Productpic.EXTRA_NAME);

            //   storageReference = FirebaseStorage.getInstance().getReference();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            String upload = databaseReference.push().getKey();
            databaseReference.child("products").child(key1).child("stock").setValue(productName);
            //  Toast.makeText(Addproduct.this, "Saved", Toast.LENGTH_LONG).show();
        }

    private void savedescriptionmethod() {
        EditText editText = findViewById(R.id.description);
        String productName = editText.getText().toString().trim();
        Intent j = getIntent();
        String key1 = j.getStringExtra(Productpic.EXTRA_NAME);
        if (TextUtils.isEmpty(productName)) {
            editText.setError("please fill");
            editText.requestFocus();
        } else{
            //   storageReference = FirebaseStorage.getInstance().getReference();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            String upload = databaseReference.push().getKey();
            databaseReference.child("products").child(key1).child("description").setValue(productName);
          //  Toast.makeText(Addproduct.this, "Saved", Toast.LENGTH_LONG).show();
        }
    }
}
package com.commerce.supapap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Addproduct extends AppCompatActivity {

    StorageReference storageReference;
    DatabaseReference databaseReference;
    String userId;
    private TextView productName;
    private Button uploaddata,addImageBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        Spinner categorySpinner= findViewById(R.id.categorySpinner);
        ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>(Addproduct.this,
               android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nameCategorySpinner));
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categorySpinnerAdapter);

        productName = findViewById(R.id.productName);
        uploaddata = findViewById(R.id.uploaddata);
        /*addImageBtn = findViewById(R.id.addImageBtn);*/
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);



        uploaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savenamemethod();
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
        if (TextUtils.isEmpty(productName)) {
            editText.setError("please fill");
            editText.requestFocus();
        } else{
            //   storageReference = FirebaseStorage.getInstance().getReference();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            String upload = databaseReference.push().getKey();
            databaseReference.child("Descriptionsfolder").child("mydescriptions").setValue(productName);
            Toast.makeText(Addproduct.this, "Saved", Toast.LENGTH_LONG).show();
        }
    }
}
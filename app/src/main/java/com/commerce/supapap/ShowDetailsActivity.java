package com.commerce.supapap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowDetailsActivity extends AppCompatActivity {
    private TextView addtocartbtn;
    private TextView titleTxt,priceTxt,descriptiontxt,NumberofOrdertxt,minusbtn,plusbtn;
    private ImageView product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        
        initView();
        getbundle();
    }

    private void getbundle() {
    }

    private void initView() {
        addtocartbtn = findViewById(R.id.addtocartbtn);
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        descriptiontxt = findViewById(R.id.descriptiontxt);
        NumberofOrdertxt = findViewById(R.id.NumberofOrdertxt);
        minusbtn = findViewById(R.id.minusbtn);
        plusbtn = findViewById(R.id.plusBtn);
    }
}
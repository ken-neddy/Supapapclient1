package com.commerce.supapap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Addproduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        Spinner categorySpinner= findViewById(R.id.categorySpinner);

        ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>(Addproduct.this,
               android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nameCategorySpinner));

        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categorySpinnerAdapter);
    }
}
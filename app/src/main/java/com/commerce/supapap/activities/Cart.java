package com.commerce.supapap.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.commerce.supapap.Dbtest.BDAdapter;
import com.commerce.supapap.R;
import com.commerce.supapap.adaptors.cartAdapter;
import com.commerce.supapap.domains.BestDealsDomain;
import com.commerce.supapap.domains.CartDomain;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    private RecyclerView recyclerViewCart;
    ArrayList<CartDomain> list1;
    DatabaseReference databaseReference;
    cartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerViewCart = findViewById(R.id.BestdealsRV);
        databaseReference = FirebaseDatabase.getInstance().getReference("carts");
      //  recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new cartAdapter(this,list1);
//        recyclerViewCart.setAdapter(cartAdapter);

        list1 = new ArrayList<>();
    }
}
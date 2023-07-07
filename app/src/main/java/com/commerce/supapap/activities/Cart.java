package com.commerce.supapap.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.commerce.supapap.R;
import com.commerce.supapap.adaptors.CartAdapter;
//import com.commerce.supapap.domains.BestDealsDomain;
import com.commerce.supapap.domains.BestDealsDomain;
import com.commerce.supapap.domains.CartDomain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Cart extends AppCompatActivity {
    private RecyclerView recyclerViewCart;
    ArrayList<CartDomain> cartlist;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    String userid;
   // CartAdapter cartAdapter;
    private RecyclerView.Adapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerViewCart = findViewById(R.id. recyclerViewCart);
       // userid = mAuth.getCurrentUser().getUid();
       // String userid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance();
        cartlist = new ArrayList<>();

        FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
        if(mFirebaseUser != null) {
            userid = mFirebaseUser.getUid();
        }
//        databaseReference = FirebaseDatabase.getInstance().getReference("carts").child("AvCtRsOlblVpm6AlVdtlSk0RXsm1").child("-NLzL8dAfBj-RDavgW9I");
        databaseReference = FirebaseDatabase.getInstance().getReference("carts").child("AvCtRsOlblVpm6AlVdtlSk0RXsm1");


        Toast.makeText(Cart.this, userid, Toast.LENGTH_LONG).show();
        //Toast.makeText(Cart.this, userid, Toast.LENGTH_LONG).show();


//        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // Sign in logic here.
//                }
//            }
//        });



//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot:snapshot.getChildren())
//                {
//                    CartDomain cartDomain = dataSnapshot.getValue(CartDomain.class);
//                    cartlist.add(cartDomain);
//
//                }
//                cartAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("DATA",snapshot.toString());
                CartDomain cartDomain = snapshot.getValue(CartDomain.class);
                cartlist.add(cartDomain);
                Log.e("DATA C",String.valueOf(cartlist.size()));
                cartAdapter = new CartAdapter(Cart.this,cartlist);
                recyclerViewCart.setAdapter(cartAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                cartAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
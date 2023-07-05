package com.commerce.supapap.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.commerce.supapap.R;
import com.commerce.supapap.adaptors.BestDealsAdapter;
import com.commerce.supapap.adaptors.CategoryAdaptor;
import com.commerce.supapap.domains.BestDealsDomain;
import com.commerce.supapap.domains.CategoryDomain;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Dashboard extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapterBestDeals;
    private RecyclerView recyclerViewCategoryList, recyclerViewBestDeals;
    private ImageView settingsBtn;
    private TextView locationtxt;
    private FloatingActionButton cart;
    DatabaseReference databaseReference;
    BestDealsAdapter bdAdapter;
    ArrayList<BestDealsDomain>list;

    FusedLocationProviderClient fusedLocationProviderClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyclerViewBestDeals = findViewById(R.id.BestdealsRV);
        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        list = new ArrayList<>();
        recyclerViewBestDeals.setLayoutManager(new LinearLayoutManager(this));
        bdAdapter = new BestDealsAdapter(this,list);
        recyclerViewBestDeals.setAdapter(bdAdapter);
        locationtxt=findViewById(R.id.locationtxt);
        cart = findViewById(R.id.cart);

        locationtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, editlocation.class);
                startActivity(intent);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Cart.class);
                startActivity(intent);
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(Dashboard.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(Dashboard.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location1 = task.getResult();
                if (location1 != null) {
                    Geocoder geocoder = new Geocoder(Dashboard.this,
                            Locale.getDefault());

                    try {

                        List<Address> addresses = geocoder.getFromLocation(
                                location1.getLatitude(), location1.getLongitude(), 1
                        );
                        // locationtxt.setText(String.valueOf(addresses.get(0).getLatitude()));
                        //  tv2.setText(String.valueOf(addresses.get(0).getLongitude()));
                        //  locationtxt.setText(String.valueOf(addresses.get(0).getCountryName()));
                        //  locationtxt.setText(String.valueOf(addresses.get(0).getLocality()));
                        locationtxt.setText(String.valueOf(addresses.get(0).getAddressLine(0)));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(Dashboard.this, "❌ Please enable location services ❌", Toast.LENGTH_LONG).show();
                    locationtxt.setText("Enable location on your device");

                }
                if (location1 == null){
                    getLocation();
                }
            }
        });






        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    BestDealsDomain bestDealsDomain = dataSnapshot.getValue(BestDealsDomain.class);
                    list.add(bestDealsDomain);

                }
                bdAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerViewCategory();
     //   recyclerViewBestDeals();

        settingsBtn = findViewById(R.id.settingsBtn);

        ImageSlider imageSlider = findViewById(R.id.slider);
        ArrayList<SlideModel> slideModelList = new ArrayList<>();
        slideModelList.add(new SlideModel(R.drawable.cart1, ScaleTypes.FIT));
        slideModelList.add(new SlideModel(R.drawable.avocado_g54c1a6a4e_1920_removebg_preview, ScaleTypes.FIT));
        slideModelList.add(new SlideModel(R.drawable.electric_iron_g32915ab35_1920_removebg_preview, ScaleTypes.FIT));
        slideModelList.add(new SlideModel(R.drawable.logo, ScaleTypes.FIT));
        slideModelList.add(new SlideModel(R.drawable.kitchen_geaa5d35fa_1920_removebg_preview, ScaleTypes.FIT));
        slideModelList.add(new SlideModel(R.drawable.spices_667115_1920_removebg_preview, ScaleTypes.FIT));
        slideModelList.add(new SlideModel(R.drawable.pills_384846_1920_removebg_preview, ScaleTypes.FIT));

        imageSlider.setImageList(slideModelList);

     //   List<BDFetchData> bdFetchData;
        //BestDealsAdapter bdHelperAdapter;

      /*mtoolbar = findViewById(R.id.toolbar1);
       setSupportActionBar(mtoolbar);*/

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Settings.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {


        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false
        );
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Bakery", "logo3"));
        category.add(new CategoryDomain("Beverages", "logo3"));
        category.add(new CategoryDomain("Cutlery", "R.drawable.kitchen_geaa5d35fa_1920_removebg_preview"));
        category.add(new CategoryDomain("Electronics", "logo3"));
        category.add(new CategoryDomain("Groceries", "logo3"));
        category.add(new CategoryDomain("Cereals", "logo3"));
        category.add(new CategoryDomain("Pharmacy", "logo3"));
        category.add(new CategoryDomain("Spices", "logo3"));
        category.add(new CategoryDomain("Spices", "logo3"));
        category.add(new CategoryDomain("Spices", "logo3"));
        category.add(new CategoryDomain("Spices", "logo3"));
        category.add(new CategoryDomain("Spices", "logo3"));
        category.add(new CategoryDomain("Spices", "logo3"));
        category.add(new CategoryDomain("Spices", "logo3"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


//        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
//            @Override
//            public void onComplete(@NonNull Task<Location> task) {
//                Location location1 = task.getResult();
//                if (location1 != null) {
//                    Geocoder geocoder = new Geocoder(Dashboard.this,
//                            Locale.getDefault());
//                    try {
//
//                        List<Address> addresses = geocoder.getFromLocation(
//                                location1.getLatitude(), location1.getLongitude(), 1
//                        );
//                      // locationtxt.setText(String.valueOf(addresses.get(0).getLatitude()));
//                      //  tv2.setText(String.valueOf(addresses.get(0).getLongitude()));
//                    //  locationtxt.setText(String.valueOf(addresses.get(0).getCountryName()));
//                      //  locationtxt.setText(String.valueOf(addresses.get(0).getLocality()));
//                     locationtxt.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                else{
//                    Toast.makeText(Dashboard.this, "❌ Please enable location services ❌", Toast.LENGTH_LONG).show();
//                    locationtxt.setText("Enable location on your device");
//
//                }
//            }
//        });

//        location2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //  Log.d(String.valueOf(tv1), "tv1: " + tv1.getText().toString());
//                ParseObject location = new ParseObject("location");
//
//                double numb1 = Double.parseDouble(tv1.getText().toString());
//                double numb2 = Double.parseDouble(tv2.getText().toString());
//                String numb3 = String.valueOf(tv3.getText());
//                String numb4 = String.valueOf(tv4.getText());
//                String numb5 = String.valueOf(tv5.getText());
//
//
//            }
//        });

    }

//    private void recyclerViewBestDeals() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false
//        );
//        recyclerViewBestDeals = findViewById(R.id.BestdealsRV);
//        recyclerViewBestDeals.setLayoutManager(linearLayoutManager);
//        ArrayList<BestDealsDomain>list;
//        DatabaseReference databaseReference;
//        BDAdapter bdAdapter;
//
////        ArrayList<BestDealsDomain> bestdeals = new ArrayList<>();
////        bestdeals.add(new BestDealsDomain("Nutrino sugar, IKG ","logo3","sugar with no mercury",199.00));
////        bestdeals.add(new BestDealsDomain("Dola all purpose baking flour, 2KG","logo1","Cook the best mandazi in town",170.00));
//
//
////        adapterBestDeals = new BestDealsAdaptor(bestdeals);
////        recyclerViewBestDeals.setAdapter(adapterBestDeals);
//    }
}
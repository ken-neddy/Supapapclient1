package com.commerce.supapap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.commerce.supapap.adaptors.CategoryAdaptor;
import com.commerce.supapap.adaptors.PopularAdaptor;
import com.commerce.supapap.domains.BestDealsDomain;
import com.commerce.supapap.domains.CategoryDomain;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapterBestDeals;
    private RecyclerView recyclerViewCategoryList, recyclerViewBestDeals;
    private ImageView settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerViewCategory();
        recyclerViewBestDeals();

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

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewBestDeals() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false
        );
        recyclerViewBestDeals = findViewById(R.id.BestdealsRV);
        recyclerViewBestDeals.setLayoutManager(linearLayoutManager);

        ArrayList<BestDealsDomain> bestdeals = new ArrayList<>();
        bestdeals.add(new BestDealsDomain("Nutrino sugar, IKG ","logo3","sugar with no mercury",199.00,3));
        bestdeals.add(new BestDealsDomain("Dola all purpose baking flour, 2KG","logo1","Cook the best mandazi in town",170.00,2));
        bestdeals.add(new BestDealsDomain("Taifa Maize flour, 2KG","logo2","sifted af",99.00,10));
        bestdeals.add(new BestDealsDomain("Samsung tv, 50 INCH, Black","logo1","inches of awesomeness",56000.00,1));
        bestdeals.add(new BestDealsDomain("radio","logo1","noma sana",5600.00,1));
        bestdeals.add(new BestDealsDomain("Pizza","logo2","Slices of Gold",999.00,2));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));

        adapterBestDeals = new PopularAdaptor(bestdeals);
        recyclerViewBestDeals.setAdapter(adapterBestDeals);
    }
}
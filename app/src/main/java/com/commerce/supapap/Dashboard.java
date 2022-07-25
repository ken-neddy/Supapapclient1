package com.commerce.supapap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.commerce.supapap.Adaptor.CategoryAdaptor;
import com.commerce.supapap.Adaptor.PopularAdaptor;
import com.commerce.supapap.Domain.BestDealsDomain;
import com.commerce.supapap.Domain.CategoryDomain;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapterBestDeals;
    private RecyclerView recyclerViewCategoryList, recyclerViewBestDeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerViewCategory();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false
        );
        recyclerViewBestDeals = findViewById(R.id.BestdealsRV);
        recyclerViewBestDeals.setLayoutManager(linearLayoutManager);

        ArrayList<BestDealsDomain> bestdeals = new ArrayList<>();
        bestdeals.add(new BestDealsDomain("sugar","logo3","sugar with no mercury",199.00,3));
        bestdeals.add(new BestDealsDomain("baking flour","logo1","Cook the best mandazi in town",170.00,2));
        bestdeals.add(new BestDealsDomain("Maize flour","logo2","sifted af",99.00,10));
        bestdeals.add(new BestDealsDomain("Samsung 50' tv","logo1","inches of awesomeness",56000.00,1));
        bestdeals.add(new BestDealsDomain("radio","logo1","noma sana",5600.00,1));
        bestdeals.add(new BestDealsDomain("Pizza","logo2","Slices of Gold",999.00,2));
        bestdeals.add(new BestDealsDomain("Yorgurt","logo2","Thickkkkk",89.00,3));

        adapter = new PopularAdaptor(bestdeals);
        recyclerViewCategoryList.setAdapter(adapterBestDeals);
    }
}
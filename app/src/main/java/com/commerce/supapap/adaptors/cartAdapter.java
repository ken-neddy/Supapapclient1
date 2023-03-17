package com.commerce.supapap.adaptors;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.commerce.supapap.activities.Cart;
import com.commerce.supapap.domains.BestDealsDomain;
import com.commerce.supapap.domains.CartDomain;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter {
    public cartAdapter(Cart cart, ArrayList<CartDomain> list1) {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

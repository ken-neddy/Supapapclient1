package com.commerce.supapap.adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.commerce.supapap.R;
import com.commerce.supapap.domains.CartDomain;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    ArrayList<CartDomain>cartlist;
    DatabaseReference databaseReference;

    public CartAdapter(Context context, ArrayList<CartDomain> cartlist) {
        this.context = context;
        this.cartlist = cartlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View x = LayoutInflater.from(context).inflate(R.layout.viewholder_cart,parent,false);
        return new MyViewHolder(x);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartDomain cartDomain=cartlist.get(position);
       // databaseReference = FirebaseDatabase.getInstance().getReference("carts");
        holder.productkey.setText(cartDomain.getProductkey());
        holder.totalprice.setText(cartDomain.getTotalprice());
       // holder.name.setText(cartDomain.getname);
       // holder.description.setText(cartDomain.getdescription);
        holder.productprice.setText(cartDomain.getProductprice());
        holder.itemsincart.setText(cartDomain.getNumberoforder());

    }


    @Override
    public int getItemCount() {
        ArrayList<CartDomain>cartlist=new ArrayList<>();
       // Log.d("count","count is : "+ cartlist.size());
        return cartlist.size();


    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,description,productprice,productkey,itemsincart,totalprice;
        ImageView pic;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
             description = itemView.findViewById(R.id.description);
             productprice = itemView.findViewById(R.id.productprice);
            // pic = (ImageView) itemView.findViewById(R.id.pic);
             productkey = itemView.findViewById(R.id.productkey);
             itemsincart = itemView.findViewById(R.id.itemsincart);
             totalprice = itemView.findViewById(R.id.totalprice);


        }
    }
}

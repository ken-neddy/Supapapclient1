package com.commerce.supapap.Dbtest;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.commerce.supapap.R;
import com.commerce.supapap.activities.ShowDetailsActivity;
import com.commerce.supapap.domains.BestDealsDomain;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BDAdapter extends RecyclerView.Adapter<BDAdapter.MyViewHolder>{
    Context context;
    ArrayList<BestDealsDomain>list;
    DatabaseReference databaseReference;

    public BDAdapter(Context context, ArrayList<BestDealsDomain> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewholder_bestdeals,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BestDealsDomain bestDealsDomain = list.get(position);
        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(v.getContext(), ShowDetailsActivity.class);
                m.putExtra("NAME",bestDealsDomain.getName());
                m.putExtra("DESCRIPTION",bestDealsDomain.getDescription());
                m.putExtra("FEE",bestDealsDomain.getPrice());
                m.putExtra("PIC",bestDealsDomain.getImage());
                m.putExtra("PRODUCTKEY",bestDealsDomain.getProductkey());
                Log.d("tga", "Value is: " + m.putExtra("PRODUCTKEY", bestDealsDomain.getProductkey()));
                context.startActivity(m);
            }
        });
        holder.name.setText(bestDealsDomain.getName());
        holder.description.setText(bestDealsDomain.getDescription());
        holder.fee.setText(bestDealsDomain.getPrice());
        Glide.with(holder.pic.getContext())
                .load(bestDealsDomain.getImage())
                .placeholder(R.drawable.addproduct4)
                .circleCrop()
                .error(R.drawable.ic_launcher_background)
                .into(holder.pic);
        switch (position) {
            case 0: {
                holder.itemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
            case 1: {
                holder.itemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background2));
                break;
            }
            case 2: {
                holder.itemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background3));
                break;
            }
            case 3: {
                holder.itemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 4: {
                holder.itemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background5));
                break;
            }
            case 5: {
                holder.itemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background2));
                break;
            }

        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,description,fee;
        ImageView pic;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            fee = itemView.findViewById(R.id.fee);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            
        }
    }
}









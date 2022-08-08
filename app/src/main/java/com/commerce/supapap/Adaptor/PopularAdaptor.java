package com.commerce.supapap.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.commerce.supapap.Domain.BestDealsDomain;
import com.commerce.supapap.R;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.Viewholder>{
    ArrayList<BestDealsDomain> bestDealsDomains;

    public PopularAdaptor(ArrayList<BestDealsDomain> bestDealsDomains) {
        this.bestDealsDomains = bestDealsDomains;
    }


    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bestdeals,parent,false);
        return new Viewholder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.title.setText(bestDealsDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(bestDealsDomains.get(position).getFee()));
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(bestDealsDomains.get(position).getPic(),"drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return bestDealsDomains.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        TextView addBtn;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
                title = itemView.findViewById(R.id.title);
                fee = itemView.findViewById(R.id.fee);
                pic = itemView.findViewById(R.id.pic);
                addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}

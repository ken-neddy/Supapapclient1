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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new Viewholder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.categoryName.setText(bestDealsDomains.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0:{
                picUrl = "muffins_gbfe92f978_1920_removebg_preview";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
           break;
            }
            case 1:{
                picUrl = "liquor_gd0ea7ba53_1280_removebg_preview";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background2));
          break;
            }
            case 2:{
                picUrl = "kitchen_geaa5d35fa_1920_removebg_preview";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background3));
            break;
            }
            case 3:{
                picUrl = "electric_iron_g32915ab35_1920_removebg_preview";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background4));
           break;
            }
            case 4:{
                picUrl = "avocado_g54c1a6a4e_1920_removebg_preview";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background5));
           break;
            }
            case 5:{
                picUrl = "nuts_3248743_1920_removebg_preview";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background2));
                break;
            }
            case 6:{
                picUrl = "pills_384846_1920_removebg_preview";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background3));
                break;
            }
            case 7:{
                picUrl = "spices_667115_1920_removebg_preview";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background4));
                break;
            }

        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);
    }

    @Override
    public int getItemCount() {
        return bestDealsDomains.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

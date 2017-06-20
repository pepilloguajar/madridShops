package com.jmontesr.madridshops.views;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmontesr.madridshops.R;
import com.jmontesr.madridshops.domain.model.Shop;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class ShopRowViewHolder extends RecyclerView.ViewHolder {


    private  TextView shopNameTextView;
    private ImageView shopLogoImageView;
    private WeakReference<Context> context;

    public ShopRowViewHolder(View rowShops) {
        super(rowShops);

        this.context = new WeakReference<>(rowShops.getContext());

        shopNameTextView = (TextView) rowShops.findViewById(R.id.row_shop__shop_name);
        shopLogoImageView = (ImageView) rowShops.findViewById(R.id.row_shop__shop_logo);
    }

    public  void setShop(Shop shop){
        if (shop == null){
            return;
        }

        shopNameTextView.setText(shop.getName());
        Picasso.with(context.get()).
                load(shop.getLogoUrl()).
                placeholder(R.drawable.shop_place_holder).
               // networkPolicy(NetworkPolicy.NO_CACHE).
                into(shopLogoImageView);
    }
}

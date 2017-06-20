package com.jmontesr.madridshops.domain.managers.network.mappers;

import android.util.Log;

import com.jmontesr.madridshops.domain.managers.network.entities.ShopEntity;
import com.jmontesr.madridshops.domain.model.Shop;
import com.jmontesr.madridshops.domain.model.Shops;

import java.util.List;

/**
 * Created by pepillo on 19/6/17.
 */

public class ShopEntityShopsMapper {

    public static Shops map(final List<ShopEntity> shopEntities){
        Shops shops = new Shops();

        for (ShopEntity shopEntity : shopEntities){
            Shop shop = Shop.of(shopEntity.getId(), shopEntity.getName());

            shop.setDescription(shopEntity.getDescription_es());
            shop.setLatitude(getCorrectCoordinateComponent(shopEntity.getGps_lat()));
            shop.setLongitude(getCorrectCoordinateComponent(shopEntity.getGps_lon()));
            shop.setAddress(shopEntity.getAddress());
            shop.setImageUrl(shopEntity.getImg());
            shop.setLogoUrl(shopEntity.getLogo_img());
            shop.setUrl(shopEntity.getUrl());

            shops.add(shop);


        }


        return shops;
    }


    public static float getCorrectCoordinateComponent(String coordinateComponent) {
        // Problem: JSON has this errors "-3.234234324,232"

        float coordinate = 0.0f;

        String s = coordinateComponent.replace(",", "");
        try {

            coordinate = Float.parseFloat(s);
        }catch (Exception e){
            Log.d("ERROR CONVERTING", "Can't convert "+ coordinateComponent);
        }

        return coordinate;

    }


}



package com.jmontesr.madridshops.utils.map.model;

import com.jmontesr.madridshops.domain.model.Shop;
import com.jmontesr.madridshops.domain.model.Shops;
import com.jmontesr.madridshops.utils.map.MapPinable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepillo on 22/6/17.
 */

public class ShopPin implements MapPinable<Shop>{
    private Shop shop;

    public static List<MapPinable> shopsPinsFromShops(Shops shops){
        List<Shop> shopsList = shops.allShops();
        List<MapPinable> shopPinList = new ArrayList<>();

        for (Shop shop :
                shopsList) {
            ShopPin sp = new ShopPin(shop);
            shopPinList.add(sp);
        }

        return shopPinList;
    }

    public ShopPin(Shop shop) {
        this.shop = shop;
    }

    @Override
    public float getLatitude() {
        return shop.getLatitude();
    }

    @Override
    public float getLongitude() {
        return shop.getLongitude();
    }

    @Override
    public String getPinDescription() {
        return shop.getName() + " - " + shop.getAddress();
    }

    @Override
    public String getPinImageUrl() {
        return shop.getLogoUrl();
    }

    @Override
    public Shop getRelatedModelObject() {
        return shop;
    }
}

package com.jmontesr.madridshops.utils;

import com.jmontesr.madridshops.domain.model.Shop;

/**
 * Created by pepillo on 20/6/17.
 */

public class StaticMapImage {

    public static String getMapImageUrl(Shop shop){
         String url = String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=320x220&scale=2",
                 shop.getLatitude(), shop.getLongitude());

        return url;
    }
}

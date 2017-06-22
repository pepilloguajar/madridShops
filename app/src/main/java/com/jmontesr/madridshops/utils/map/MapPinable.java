package com.jmontesr.madridshops.utils.map;

/**
 * Created by pepillo on 22/6/17.
 */

public interface MapPinable<E> {


        float getLatitude();
        float getLongitude();
        String getPinDescription();
        String getPinImageUrl();
        E getRelatedModelObject();


}

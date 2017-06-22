package com.jmontesr.madridshops.domain.interactos;

/**
 * Created by pepillo on 20/6/17.
 */

public interface SetAllShopsCacheInteractor {

    public static final String SHOPS_SAVED = "SHOPS_SAVED";


    void execute(boolean shopSaved);
}

package com.jmontesr.madridshops.domain.managers.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pepillo on 19/6/17.
 */

public class ShopsResponseEntity {

    @SerializedName("result") private List<ShopEntity> result;

    public List<ShopEntity> getResult() {
        return result;
    }
}

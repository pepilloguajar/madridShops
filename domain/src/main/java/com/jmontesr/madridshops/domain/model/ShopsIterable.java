package com.jmontesr.madridshops.domain.model;

import java.util.List;

/**
 * Created by pepillo on 13/6/17.
 */

public interface ShopsIterable {

    long size();
    Shop get(long index);
    List<Shop> allShops();

}

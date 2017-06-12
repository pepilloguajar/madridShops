package com.jmontesr.madridshops.domain.model;

/**
 * Created by pepillo on 13/6/17.
 */

public interface ShopsUpdatable {

    void add(Shop shop);
    void delete(Shop shop);
    void update(Shop newShop, long index);

}

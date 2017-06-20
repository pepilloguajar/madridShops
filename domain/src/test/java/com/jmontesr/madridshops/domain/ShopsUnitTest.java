package com.jmontesr.madridshops.domain;

import com.jmontesr.madridshops.domain.model.Shop;
import com.jmontesr.madridshops.domain.model.Shops;

import org.junit.Test;

import static org.junit.Assert.*;



public class ShopsUnitTest {

    @Test
    public void after_creation_shops_size_is_zero() throws Exception {

        Shops shops = new Shops();
        assertEquals(0, shops.size());
    }


    @Test
    public void shops_adding_one_shop_size_is_one() throws  Exception{
        Shops sut = new Shops();

        sut.add(Shop.of(1,"My shop"));

        assertEquals(1, sut.size());


    }

    @Test
    public void shops_adding_one_shop_and_deleting_size_is_zero() throws  Exception{
        Shops sut = new Shops();

        Shop shop = Shop.of(1, "My shop");
        sut.add(shop);
        sut.delete(shop);

        assertEquals(0, sut.size());


    }

    @Test
    public void shops_adding_one_shop_and_getting_returns_that_shop() throws  Exception{
        Shops sut = new Shops();

        Shop shop = Shop.of(1, "My shop");
        sut.add(shop);
        Shop shop1 = sut.get(0);

        assertEquals(shop1.getId(), shop.getId());
        assertEquals(shop1.getName(), shop.getName());


    }

    @Test
    public void shops_adding_several_shops_return_alls_shops() throws  Exception{
        Shops sut = new Shops();

        for (int i = 0; i < 10; i++) {
            Shop shop = Shop.of(i, "My shop " + i);
            sut.add(shop);
        }

        assertEquals(10, sut.size());
        assertEquals(10, sut.allShops().size());
        assertEquals(0, sut.allShops().get(0).getId());
        assertEquals("My shop 0", sut.allShops().get(0).getName());


    }


}
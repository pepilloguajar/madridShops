package com.jmontesr.madridshops.domain;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.jmontesr.madridshops.domain.managers.db.ShopDAO;
import com.jmontesr.madridshops.domain.model.Shop;
import com.jmontesr.madridshops.domain.model.Shops;

import org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class ShopDAOTest {

    public static final int TEST_ID = 888;
    public static final String TEST_NAME = "name";

    @Test
    public void given_shop_DAO_insert_shop() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        ShopDAO sut = new ShopDAO(appContext);
        Shop shop = Shop.of(1,"Shop test").setAddress("address").setLatitude(10).setLongitude(11);

        long id = sut.insert(shop);

        assertTrue(id > 0);


    }

    public  void given_inserted_shops_DAO_queries_all_shops() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        Shop shop = insertShop(1, "Shop test", "address", 10, 11);

        List<Shop> shops = sut.query();

        assertNotNull(shops);
        assertTrue(shops.size() >= 1);

    }


    @Test
    public void given_inserted_shops_deleteall_emty_talbe() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        insertShops();

        sut.deleteAll();

        List<Shop> shopList = sut.query();
        assertNull(shopList);
    }

    @Test
    public void  given_one_inserted_shop_I_can_delete_that_shop() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        sut.deleteAll();
        Shop insertedShop = insertShop(TEST_ID, TEST_NAME, "", 19, 13);
        Shop shop = sut.query(insertedShop.getId());

        assertNotNull(shop);

        assertEquals(insertedShop.getId(), shop.getId());
        assertEquals(TEST_NAME, shop.getName());

    }

    private  Shop insertShop(long id, String name, String address, float latitude, float longitude){

        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        Shop shop = Shop.of(id, name).setLongitude(longitude).setLatitude(latitude).setAddress(address);

        long insertedId = sut.insert(shop);
        return shop;

    }

    private  void insertShops(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        ShopDAO sut = new ShopDAO(appContext);

        for (int i =0; i < 10; i++){
            insertShop( i, "Shop "+ i, "Address " + i, i+1, i);
        }
    }
}




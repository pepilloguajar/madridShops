package com.jmontesr.madridshops.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jmontesr.madridshops.R;
import com.jmontesr.madridshops.domain.model.Shop;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);


        Shop.of(1, "Mi tienda").
                setAddress("C/ dfas").
                setLatitude(10).
                setLatitude(22);


    }
}

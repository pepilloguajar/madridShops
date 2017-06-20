package com.jmontesr.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jmontesr.madridshops.R;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractor;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorCompletion;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorFakeImpl;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorImpl;
import com.jmontesr.madridshops.domain.interactos.InteractorErrorCompletion;
import com.jmontesr.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.jmontesr.madridshops.domain.managers.network.NetworkManager;
import com.jmontesr.madridshops.domain.model.Shop;
import com.jmontesr.madridshops.domain.model.Shops;
import com.jmontesr.madridshops.fragments.ShopsFragment;
import com.jmontesr.madridshops.navigator.Navigator;
import com.jmontesr.madridshops.views.OnElementClick;

public class ShopListActivity extends AppCompatActivity {

    ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);


        // obtener shops list

        NetworkManager manager = new GetAllShopsManagerImpl(this);
        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImpl(manager);
        getAllShopsInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(Shops shops) {
                shopsFragment.setShops(shops);
                shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
                    @Override
                    public void clickedOn(@NonNull Shop element, int position) {
                        Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, element, position);
                    }
                });

            }
        }, new InteractorErrorCompletion() {
            @Override
            public void onError(String errorDescription) {

            }
        });


    }
}

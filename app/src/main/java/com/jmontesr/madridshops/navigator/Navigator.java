package com.jmontesr.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.jmontesr.madridshops.activities.MainActivity;
import com.jmontesr.madridshops.activities.ShopDetailActivity;
import com.jmontesr.madridshops.activities.ShopListActivity;
import com.jmontesr.madridshops.domain.interactos.InteractorErrorCompletion;
import com.jmontesr.madridshops.domain.model.Shop;

import static com.jmontesr.madridshops.utils.Constants.INTENT_SHOP_DETAIL;

/**
 * Created by pepillo on 12/6/17.
 */

public class Navigator {

    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {

        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);

        mainActivity.startActivity(i);

        return i;
    }


    public static Intent navigateFromShopListActivityToShopDetailActivity(@NonNull final ShopListActivity shopListActivity, final Shop shop, final int position){

        final Intent i = new Intent(shopListActivity, ShopDetailActivity.class);
        i.putExtra(INTENT_SHOP_DETAIL,shop);
        shopListActivity.startActivity(i);

        return i;

    }

}

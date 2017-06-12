package com.jmontesr.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.jmontesr.madridshops.activities.MainActivity;
import com.jmontesr.madridshops.activities.ShopListActivity;

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

}

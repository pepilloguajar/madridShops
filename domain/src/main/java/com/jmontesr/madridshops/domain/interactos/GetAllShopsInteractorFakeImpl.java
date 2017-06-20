package com.jmontesr.madridshops.domain.interactos;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jmontesr.madridshops.domain.model.Shop;
import com.jmontesr.madridshops.domain.model.Shops;

/**
 * Created by pepillo on 13/6/17.
 */

public class GetAllShopsInteractorFakeImpl implements GetAllShopsInteractor {

    @Override
    public void execute(GetAllShopsInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        Shops shops = new Shops();

        for (int i = 0; i < 10; i++) {
            Shop shop = Shop.of(i, "My shop " + i).setLogoUrl("http://105.imagebam.com/download/6B0k97mGaZuhKHsqfW6fYA/41266/412650634/tumblr_n5u7669bjP1slffxko4_500.jpg");
            shops.add(shop);
        }

        if (completion != null){
            completion.completion(shops);
        }

    }
}

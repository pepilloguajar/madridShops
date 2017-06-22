package com.jmontesr.madridshops.domain.managers.cache;

import android.content.Context;

import com.jmontesr.madridshops.domain.managers.db.ShopDAO;
import com.jmontesr.madridshops.domain.model.Shop;
import com.jmontesr.madridshops.domain.model.Shops;

import java.lang.ref.WeakReference;

/**
 * Created by pepillo on 22/6/17.
 */

public class SaveAllShopsIntoCacheManagerDaoImpl implements SaveAllShopsIntoCacheManager {

    WeakReference<Context> context;

    public SaveAllShopsIntoCacheManagerDaoImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(Shops shops, Runnable completion) {
        ShopDAO dao = new ShopDAO(context.get());
        for(Shop shop : shops.allShops()){
            dao.insert(shop);
        }

        completion.run();
    }
}

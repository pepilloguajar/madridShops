package com.jmontesr.madridshops.domain.managers.cache;

import android.content.Context;

import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorCompletion;
import com.jmontesr.madridshops.domain.managers.db.ShopDAO;
import com.jmontesr.madridshops.domain.model.Shop;
import com.jmontesr.madridshops.domain.model.Shops;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by pepillo on 20/6/17.
 */

public class GetAllShopsFromCacheManagerDAOImpl implements GetAllShopsFromCacheManager {

    private WeakReference<Context> context;

    public GetAllShopsFromCacheManagerDAOImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }


    @Override
    public void execute(GetAllShopsFromCacheManagerCompletion completion) {
        ShopDAO dao = new ShopDAO(context.get());
        List<Shop> shopList = dao.query();
        if(shopList == null){
            return;
        }
        Shops shops =  Shops.from(shopList);
        completion.completion(shops);
    }
}

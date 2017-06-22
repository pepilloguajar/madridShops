package com.jmontesr.madridshops.domain.managers.cache;

import android.content.Context;

import com.jmontesr.madridshops.domain.managers.db.ShopDAO;

import java.lang.ref.WeakReference;

/**
 * Created by pepillo on 22/6/17.
 */

public class ClearCacheManagerDAOImple implements ClearCacheManager {

    private WeakReference<Context> context;

    public ClearCacheManagerDAOImple(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(Runnable completion) {
        ShopDAO dao = new ShopDAO(context.get());
        dao.deleteAll();
        completion.run();
    }
}

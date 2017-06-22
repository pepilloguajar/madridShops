package com.jmontesr.madridshops.domain.interactos;

import com.jmontesr.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import com.jmontesr.madridshops.domain.model.Shops;

/**
 * Created by pepillo on 22/6/17.
 */

public class SaveAllShopsIntoCacheInteractorImpl implements SaveAllShopsIntoCacheInteractor {

    private SaveAllShopsIntoCacheManager manager;

    public SaveAllShopsIntoCacheInteractorImpl(SaveAllShopsIntoCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Shops shops, Runnable completion) {
        manager.execute(shops, completion);
    }
}

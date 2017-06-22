package com.jmontesr.madridshops.domain.interactos;

import com.jmontesr.madridshops.domain.managers.cache.ClearCacheManager;

/**
 * Created by pepillo on 22/6/17.
 */

public class ClearCacheInteractorImp implements ClearCacheInteractor {

    private ClearCacheManager manager;

    public ClearCacheInteractorImp(ClearCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Runnable completion) {
        manager.execute(completion);
    }
}

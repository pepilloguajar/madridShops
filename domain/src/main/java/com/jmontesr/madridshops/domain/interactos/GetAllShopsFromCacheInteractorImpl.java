package com.jmontesr.madridshops.domain.interactos;

import android.support.annotation.NonNull;

import com.jmontesr.madridshops.domain.managers.cache.GetAllShopsFromCacheManager;
import com.jmontesr.madridshops.domain.managers.cache.GetAllShopsFromCacheManagerCompletion;
import com.jmontesr.madridshops.domain.model.Shops;

/**
 * Created by pepillo on 20/6/17.
 */

public class GetAllShopsFromCacheInteractorImpl implements GetAllShopsFromCacheInteractor {

    private GetAllShopsFromCacheManager cacheManager;

    public GetAllShopsFromCacheInteractorImpl(GetAllShopsFromCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion) {
        cacheManager.execute(new GetAllShopsFromCacheManagerCompletion() {
            @Override
            public void completion(@NonNull Shops shops) {
                completion.completion(shops);

            }
        });
    }
}

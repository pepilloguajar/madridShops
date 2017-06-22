package com.jmontesr.madridshops.domain.managers.cache;

import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorCompletion;

/**
 * Created by pepillo on 20/6/17.
 */

public interface GetAllShopsFromCacheManager {

    void execute(final GetAllShopsFromCacheManagerCompletion completion);
}

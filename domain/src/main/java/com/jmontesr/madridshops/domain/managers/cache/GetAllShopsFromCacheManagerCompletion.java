package com.jmontesr.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorCompletion;
import com.jmontesr.madridshops.domain.model.Shops;

/**
 * Created by pepillo on 20/6/17.
 */

public interface GetAllShopsFromCacheManagerCompletion {

    void completion(@NonNull final Shops shops);
}

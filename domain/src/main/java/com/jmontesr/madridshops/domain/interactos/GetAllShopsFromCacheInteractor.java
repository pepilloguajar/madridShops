package com.jmontesr.madridshops.domain.interactos;

import android.support.annotation.NonNull;

/**
 * Created by pepillo on 20/6/17.
 */

public interface GetAllShopsFromCacheInteractor {
    void execute(@NonNull final GetAllShopsInteractorCompletion completion);

}

package com.jmontesr.madridshops.domain.managers.network;

import android.support.annotation.NonNull;

import com.jmontesr.madridshops.domain.managers.network.entities.ShopEntity;
import com.jmontesr.madridshops.domain.model.Shops;

import java.util.List;

/**
 * Created by pepillo on 19/6/17.
 */

public interface GetAllShopsManagerCompletion {

    void completion(@NonNull final List<ShopEntity> shopsEntities);


}

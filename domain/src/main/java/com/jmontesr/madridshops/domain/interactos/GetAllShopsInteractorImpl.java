package com.jmontesr.madridshops.domain.interactos;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jmontesr.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import com.jmontesr.madridshops.domain.managers.network.ManagerErrorCompletion;
import com.jmontesr.madridshops.domain.managers.network.NetworkManager;
import com.jmontesr.madridshops.domain.managers.network.entities.ShopEntity;
import com.jmontesr.madridshops.domain.managers.network.mappers.ShopEntityShopsMapper;
import com.jmontesr.madridshops.domain.model.Shops;

import java.util.List;

/**
 * Created by pepillo on 19/6/17.
 */

public class GetAllShopsInteractorImpl implements GetAllShopsInteractor  {

   private NetworkManager networkManager;

    public GetAllShopsInteractorImpl(@NonNull final NetworkManager networkManager){
        this.networkManager = networkManager;
    }

    @Override
    public void execute(final GetAllShopsInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError) {
       if (this.networkManager == null){
           if (onError == null){
               throw  new IllegalStateException("Network manager can't be null");
           }else {
               onError.onError("Error en la conexión");
           }
       }

        this.networkManager.getShopsFromServer(new GetAllShopsManagerCompletion() {
            @Override
            public void completion(@NonNull List<ShopEntity> shopsEntities) {

                if (completion != null){
                    Shops shops = ShopEntityShopsMapper.map(shopsEntities);
                    completion.completion(shops);
                }
            }
        }, new ManagerErrorCompletion() {
            @Override
            public void onError(String errorDescription) {
                if (onError != null){
                    onError.onError(errorDescription);
                }
            }
        });
    }


}

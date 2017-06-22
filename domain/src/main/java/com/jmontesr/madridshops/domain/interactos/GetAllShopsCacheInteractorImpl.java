package com.jmontesr.madridshops.domain.interactos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

/**
 * Created by pepillo on 20/6/17.
 */

public class GetAllShopsCacheInteractorImpl implements GetAllShopsCacheIntetactor {

    private WeakReference<Context> context;

    public GetAllShopsCacheInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }



    @Override
    public void execute(Runnable onAllShopsAreCached, Runnable onAllShopsAreNotCached) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean shopsSaved = preferences.getBoolean(SetAllShopsCacheInteractor.SHOPS_SAVED, false);

        if(shopsSaved){
            onAllShopsAreCached.run();
        }else{
            onAllShopsAreNotCached.run();
        }
    }
}

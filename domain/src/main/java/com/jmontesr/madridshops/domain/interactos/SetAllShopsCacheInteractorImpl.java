package com.jmontesr.madridshops.domain.interactos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

/**
 * Created by pepillo on 20/6/17.
 */

public class SetAllShopsCacheInteractorImpl implements SetAllShopsCacheInteractor {

    private WeakReference<Context> context;

    public SetAllShopsCacheInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(boolean shopSaved) {
        ;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(SetAllShopsCacheInteractorImpl.SHOPS_SAVED, shopSaved);

        editor.commit();
    }
}

package com.jmontesr.madridshops;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractor;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorCompletion;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorImpl;
import com.jmontesr.madridshops.domain.interactos.InteractorErrorCompletion;
import com.jmontesr.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import com.jmontesr.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.jmontesr.madridshops.domain.managers.network.ManagerErrorCompletion;
import com.jmontesr.madridshops.domain.managers.network.NetworkManager;
import com.jmontesr.madridshops.domain.model.Shops;
import com.squareup.picasso.Picasso;


public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    @Override
    public void onCreate() {
        super.onCreate();


        //init app

        Log.d(APP_NAME, "App strarting...");

        //Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();


        // low memory: dum something


    }


}

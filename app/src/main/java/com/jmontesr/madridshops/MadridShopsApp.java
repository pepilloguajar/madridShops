package com.jmontesr.madridshops;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.util.Log;


public class MadridShopsApp extends MultiDexApplication {

    public static final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    @Override
    public void onCreate() {
        super.onCreate();


        //init app

        Log.d(APP_NAME, "App strarting...");

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();


        // low memory: dum something


    }


}

package com.jmontesr.madridshops.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jmontesr.madridshops.MadridShopsApp;
import com.jmontesr.madridshops.R;
import com.jmontesr.madridshops.domain.interactos.ClearCacheInteractor;
import com.jmontesr.madridshops.domain.interactos.ClearCacheInteractorImp;
import com.jmontesr.madridshops.domain.interactos.SetAllShopsCacheInteractor;
import com.jmontesr.madridshops.domain.interactos.SetAllShopsCacheInteractorImpl;
import com.jmontesr.madridshops.domain.managers.cache.ClearCacheManager;
import com.jmontesr.madridshops.domain.managers.cache.ClearCacheManagerDAOImple;
import com.jmontesr.madridshops.navigator.Navigator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main__shops_button) Button shopsButton;
    @BindView(R.id.activity_main__activities_button) Button activitiesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        shopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MadridShopsApp.APP_NAME,"Hello");

                Navigator.navigateFromMainActivityToShopListActivity(MainActivity.this);

            }
        });

        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MadridShopsApp.APP_NAME,"Hello activities");
            }
        });


        //launcInBackgroundThread();
    }


    private void launcInBackgroundThread(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Hilo", Thread.currentThread().getName());
                testMultithread();

            }
        });

    }


    private void testMultithread() {
        final String web = "http://freegeoip.net/json/";


        try {
            URL url = new URL(web);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            InputStream is = (InputStream) request.getContent();
            Log.d("Web", streamToString(is));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    String streamToString(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        for(String line = br.readLine(); line != null; line = br.readLine())
            out.append(line);
        br.close();
        return out.toString();
    }



    @OnClick(R.id.activity_main__clear_cache_button) void clearCache(){
        ClearCacheManager manager = new ClearCacheManagerDAOImple(this);
        ClearCacheInteractor clearCacheInteractor = new ClearCacheInteractorImp(manager);
        clearCacheInteractor.execute(new Runnable() {
            @Override
            public void run() {
                SetAllShopsCacheInteractor setAllShopsCacheInteractor = new SetAllShopsCacheInteractorImpl(getBaseContext());
                setAllShopsCacheInteractor.execute(false);
            }
        });
    }

}

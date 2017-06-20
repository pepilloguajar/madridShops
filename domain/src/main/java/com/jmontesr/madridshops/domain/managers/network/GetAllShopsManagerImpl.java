package com.jmontesr.madridshops.domain.managers.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jmontesr.domain.R;
import com.jmontesr.madridshops.domain.managers.network.entities.ShopEntity;
import com.jmontesr.madridshops.domain.managers.network.jsonparser.ShopsJsonParser;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by pepillo on 19/6/17.
 */

public class GetAllShopsManagerImpl  implements NetworkManager {

    WeakReference<Context> weakContext;


    public GetAllShopsManagerImpl(Context context){
        weakContext = new WeakReference<Context>(context);
    }

    @Override
    public void getShopsFromServer(@NonNull final GetAllShopsManagerCompletion completion, @Nullable final ManagerErrorCompletion errorCompletion) {

        String url = weakContext.get().getString(R.string.shops_url);

        RequestQueue queue = Volley.newRequestQueue(weakContext.get());

        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("JSON", response);

                        ShopsJsonParser parser = new ShopsJsonParser();
                        List<ShopEntity> entities = parser.parse(response);

                        if(completion != null ){
                            completion.completion(entities);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON_ERROR", error.toString());
                        if(errorCompletion != null){
                            errorCompletion.onError(error.getMessage());
                        }
                    }
                }

        );
        //aquí se lanza
        queue.add(request);


    }
}

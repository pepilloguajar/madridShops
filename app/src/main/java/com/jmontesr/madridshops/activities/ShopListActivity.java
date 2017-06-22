package com.jmontesr.madridshops.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jmontesr.madridshops.R;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsCacheInteractorImpl;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsCacheIntetactor;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsFromCacheInteractor;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsFromCacheInteractorImpl;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractor;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorCompletion;
import com.jmontesr.madridshops.domain.interactos.GetAllShopsInteractorImpl;
import com.jmontesr.madridshops.domain.interactos.InteractorErrorCompletion;
import com.jmontesr.madridshops.domain.interactos.SaveAllShopsIntoCacheInteractor;
import com.jmontesr.madridshops.domain.interactos.SaveAllShopsIntoCacheInteractorImpl;
import com.jmontesr.madridshops.domain.interactos.SetAllShopsCacheInteractor;
import com.jmontesr.madridshops.domain.interactos.SetAllShopsCacheInteractorImpl;
import com.jmontesr.madridshops.domain.managers.cache.GetAllShopsFromCacheManager;
import com.jmontesr.madridshops.domain.managers.cache.GetAllShopsFromCacheManagerDAOImpl;
import com.jmontesr.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import com.jmontesr.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManagerDaoImpl;
import com.jmontesr.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import com.jmontesr.madridshops.domain.managers.network.NetworkManager;
import com.jmontesr.madridshops.domain.model.Shop;
import com.jmontesr.madridshops.domain.model.Shops;
import com.jmontesr.madridshops.fragments.ShopsFragment;
import com.jmontesr.madridshops.navigator.Navigator;
import com.jmontesr.madridshops.utils.map.MapPinable;
import com.jmontesr.madridshops.utils.map.MapUtil;
import com.jmontesr.madridshops.utils.map.model.ShopPin;
import com.jmontesr.madridshops.views.OnElementClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jmontesr.madridshops.utils.map.MapUtil.centerMapInPosition;

public class ShopListActivity extends AppCompatActivity {

    ShopsFragment shopsFragment;
    @BindView(R.id.activity_shop_list__progress_bar)
    ProgressBar progressBar;
    private SupportMapFragment mapFragment;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);

        initializeMap();

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shops);




    }

    private void readDataFromCache() {

        GetAllShopsFromCacheManager getAllShopsFromCacheManager = new GetAllShopsFromCacheManagerDAOImpl(this);
        GetAllShopsFromCacheInteractor getAllShopsFromCacheInteractor = new GetAllShopsFromCacheInteractorImpl(getAllShopsFromCacheManager);
        getAllShopsFromCacheInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(Shops shops) {
                configShopsFragment(shops);
            }
        });

    }


    private void obtenerShopsList() {

        progressBar.setVisibility(View.VISIBLE);

        NetworkManager manager = new GetAllShopsManagerImpl(this);
        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImpl(manager);
        getAllShopsInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(Shops shops) {


                // persistir en DB
                SaveAllShopsIntoCacheManager saveManager = new SaveAllShopsIntoCacheManagerDaoImpl(getBaseContext());
                SaveAllShopsIntoCacheInteractor saveInteractor = new SaveAllShopsIntoCacheInteractorImpl(saveManager);
                saveInteractor.execute(shops, new Runnable() {
                    @Override
                    public void run() {
                        SetAllShopsCacheInteractor setAllShopsCacheInteractor = new SetAllShopsCacheInteractorImpl(getBaseContext());
                        setAllShopsCacheInteractor.execute(true);
                    }
                });

                configShopsFragment(shops);

                progressBar.setVisibility(View.INVISIBLE);

            }
        }, new InteractorErrorCompletion() {
            @Override
            public void onError(String errorDescription) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }


    private void configShopsFragment(final Shops shops) {

        shopsFragment.setShops(shops);
        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(@NonNull Shop element, int position) {
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, element, position);
            }
        });

        putShopPinOnMap(shops);
    }

    private void putShopPinOnMap(Shops shops) {


        List<MapPinable> shopPins = ShopPin.shopsPinsFromShops(shops);
        MapUtil.addPins(shopPins, map, this);

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){

            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTag() == null || !(marker.getTag() instanceof Shop)){
                    return;
                }
                Shop shop = (Shop) marker.getTag();
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopListActivity.this, shop, 0);
            }
        });
    }


    private void initializeMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    map = googleMap;
                    checkCacheData();
                    setupMap(googleMap);
                }
            }
        });
    }

    private void setupMap(GoogleMap map) {
        centerMapInPosition(map, 40.411335, -3.674908);
        map.setBuildingsEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.getUiSettings().setRotateGesturesEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(true);



        MarkerOptions retiroMarkerOptions = new MarkerOptions().position(new LatLng(40.411335,-3.674908)).title("Retiro").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        MarkerOptions solMarkerOptions = new MarkerOptions().position(new LatLng(40.4167122,-3.7036626)).title("Sol")
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_camera));
        Marker marker = map.addMarker(retiroMarkerOptions);
        Marker marke2r = map.addMarker(solMarkerOptions);






        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        map.setMyLocationEnabled(true);

    }


    public void checkCacheData(){
        GetAllShopsCacheIntetactor getAllShopsCacheIntetactor = new GetAllShopsCacheInteractorImpl(this);

        getAllShopsCacheIntetactor.execute(new Runnable() {
            @Override
            public void run() {
                // all cache alreadey, no need to downldad things, just read from DB
                readDataFromCache();

            }
        }, new Runnable() {
            @Override
            public void run() {
                // nothing cached yet
                obtenerShopsList();

            }
        });
    }



}

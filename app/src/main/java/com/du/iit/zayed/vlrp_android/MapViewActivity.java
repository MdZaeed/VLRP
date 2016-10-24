package com.du.iit.zayed.vlrp_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Zayed on 24-Oct-16.
 */
public class MapViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    MapView mapView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_view);

        mapView=(MapView) findViewById(R.id.main_map_view);

        mapView.onCreate(savedInstanceState);

        if (mapView != null) {
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng Dhaka=new LatLng(23.7000,90.3667);
        CameraPosition cameraPosition=new CameraPosition.Builder()
                .zoom(10)
                .target(Dhaka)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        MarkerOptions endMarkerOption = new MarkerOptions().position(Dhaka)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(endMarkerOption);
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.setOnMarkerClickListener(null);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }
}

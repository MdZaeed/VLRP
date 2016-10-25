package com.du.iit.zayed.vlrp_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.du.iit.zayed.vlrp_android.models.Vehicle;
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
    Vehicle passedVehicle;

    TextView vehicleNameTextView;
    TextView driverNameTextView;
    TextView routesTextView;
    TextView statusTextView;
    TextView universityTextView;
    Button mapViewButton;

    GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent=getIntent();
        passedVehicle=(Vehicle) intent.getSerializableExtra(MainActivity.PASSED_OBJECT);

        mapView=(MapView) findViewById(R.id.main_map_view);

        mapView.onCreate(savedInstanceState);

        if (mapView != null) {
            mapView.getMapAsync(this);
        }

        setInitialVehicleInfo();
    }

    private void setInitialVehicleInfo() {
        vehicleNameTextView=(TextView) findViewById(R.id.tv_vehicle_name);
        driverNameTextView=(TextView) findViewById(R.id.tv_driver_name);
        routesTextView=(TextView) findViewById(R.id.tv_routes);
        statusTextView=(TextView) findViewById(R.id.tv_status);
        universityTextView=(TextView) findViewById(R.id.tv_university);
        mapViewButton=(Button) findViewById(R.id.btn_map);

        vehicleNameTextView.setText(passedVehicle.getVehicleName());
        driverNameTextView.setText(passedVehicle.getDriverName());
        routesTextView.setText(passedVehicle.getRoutes());
        statusTextView.setText(passedVehicle.getActiveTime());
        universityTextView.setText(passedVehicle.getUniversityName());
        mapViewButton.setVisibility(View.GONE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapView.onResume();
        this.googleMap=googleMap;
        LatLng Dhaka=new LatLng(23.7000,90.3667);
        CameraPosition cameraPosition=new CameraPosition.Builder()
                .zoom(15)
                .target(Dhaka)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        MarkerOptions endMarkerOption = new MarkerOptions().position(Dhaka)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(endMarkerOption);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.setOnMarkerClickListener(null);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public final void onDestroy() {
        if (mapView != null) {
            mapView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public final void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public final void onPause() {
        if (mapView != null) {
            mapView.onPause();
        }
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.general_menu, menu);

        if(passedVehicle.getIsFavorite().toLowerCase().equals("false")) {
            menu.getItem(0).setIcon(getResources().getDrawable(android.R.drawable.btn_star_big_off));
        }else
        {
            menu.getItem(0).setIcon(getResources().getDrawable(android.R.drawable.btn_star_big_on));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_favorite:
                if(passedVehicle.getIsFavorite().toLowerCase().equals("false"))
                {
                    item.setIcon(getResources().getDrawable(android.R.drawable.btn_star_big_on));
                    passedVehicle.setIsFavorite("true");
                }else if(passedVehicle.getIsFavorite().toLowerCase().equals("true"))
                {
                    item.setIcon(getResources().getDrawable(android.R.drawable.btn_star_big_off));
                    passedVehicle.setIsFavorite("false");
                }
                break;

            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

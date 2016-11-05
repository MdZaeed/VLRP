package com.du.iit.zayed.vlrp_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.du.iit.zayed.vlrp_android.adapter.ApiAdapter;
import com.du.iit.zayed.vlrp_android.adapter.RecyclerViewListAdapter;
import com.du.iit.zayed.vlrp_android.models.LocationResponse;
import com.du.iit.zayed.vlrp_android.models.Vehicle;
import com.du.iit.zayed.vlrp_android.models.VehicleResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Zayed on 24-Oct-16.
 */
public class MapViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    MapView mapView;
    VehicleResponse passedVehicle;

    TextView vehicleNameTextView;
    TextView driverNameTextView;
    TextView routesTextView;
    TextView statusTextView;
    TextView universityTextView;
    Button mapViewButton;

    ArrayList<LocationResponse> locationResponses;

    GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent=getIntent();
        passedVehicle=(VehicleResponse) intent.getSerializableExtra(MainActivity.PASSED_OBJECT);

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

        vehicleNameTextView.setText("Noooooooo");
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
        ApiAdapter apiAdapter=new ApiAdapter();
        Call<List<LocationResponse>> call=apiAdapter.vlrpApi.getLocation(Integer.parseInt(passedVehicle.getVehicleId()));
        call.enqueue(new Callback<List<LocationResponse>>() {
            @Override
            public void onResponse(Response<List<LocationResponse>> response, Retrofit retrofit) {
                locationResponses=new ArrayList<>(response.body());
                if(!locationResponses.isEmpty()) {
                    LatLng position = getLatestPosition(locationResponses);
                    Toast.makeText(MapViewActivity.this, position.latitude + "," + position.longitude, Toast.LENGTH_LONG).show();


//                LatLng Dhaka=new LatLng(23.7000,90.3667);
                    setMarker(position);
                }else
                {
                    Toast.makeText(MapViewActivity.this,"Sorry, no location available", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MapViewActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.setOnMarkerClickListener(null);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private void setMarker(LatLng marker)
    {
        CameraPosition cameraPosition=new CameraPosition.Builder()
                .zoom(15)
                .target(marker)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        MarkerOptions endMarkerOption = new MarkerOptions().position(marker)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(endMarkerOption);
        mapView.onResume();
    }

    private LatLng getLatestPosition(ArrayList<LocationResponse> locationResponses) {
        LocationResponse tempLocationResponse=locationResponses.get(0);
        for (LocationResponse locationResponse:
             locationResponses) {
            if(Double.parseDouble(locationResponse.getTime())>Double.parseDouble(tempLocationResponse.getTime()))
            {
                tempLocationResponse=locationResponse;
            }
        }
        LatLng latLng=new LatLng(Double.parseDouble(tempLocationResponse.getLatitude()),Double.parseDouble(tempLocationResponse.getLongitude()));
        return latLng;
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

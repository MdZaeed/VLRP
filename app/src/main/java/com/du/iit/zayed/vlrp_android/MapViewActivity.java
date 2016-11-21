package com.du.iit.zayed.vlrp_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.du.iit.zayed.vlrp_android.Api.DirectionApi;
import com.du.iit.zayed.vlrp_android.Utils.Tools;
import com.du.iit.zayed.vlrp_android.adapter.ApiAdapter;
import com.du.iit.zayed.vlrp_android.adapter.DirectionApiAdapter;
import com.du.iit.zayed.vlrp_android.adapter.RecyclerViewListAdapter;
import com.du.iit.zayed.vlrp_android.models.DirectionResponse;
import com.du.iit.zayed.vlrp_android.models.FavoritesModel;
import com.du.iit.zayed.vlrp_android.models.LocationResponse;
import com.du.iit.zayed.vlrp_android.models.LoginResponse;
import com.du.iit.zayed.vlrp_android.models.Step;
import com.du.iit.zayed.vlrp_android.models.Vehicle;
import com.du.iit.zayed.vlrp_android.models.VehicleResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONObject;

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
    ApiAdapter apiAdapter;
    DirectionApiAdapter directionApiAdapter;
    boolean exited=false;
    Marker drawnMarker;

    ArrayList<LocationResponse> locationResponses;

    GoogleMap googleMap;

    Handler mHandler;

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d("Running","Yes");
                getLocationData(false);
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, 5000);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        apiAdapter=new ApiAdapter();
        directionApiAdapter=new DirectionApiAdapter();
        mHandler=new Handler(Looper.getMainLooper());

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

        vehicleNameTextView.setText("Dummy Driver Name");
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

        getLocationData(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.setOnMarkerClickListener(null);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public void getLocationData(final boolean shouldDrawRoute)
    {
        Call<List<LocationResponse>> call=apiAdapter.vlrpApi.getLocation(Integer.parseInt(passedVehicle.getVehicleId()));
        call.enqueue(new Callback<List<LocationResponse>>() {
            @Override
            public void onResponse(Response<List<LocationResponse>> response, Retrofit retrofit) {
                locationResponses=new ArrayList<>(response.body());
                if(!locationResponses.isEmpty()) {
                    LatLng position = getLatestPosition(locationResponses);
                    Toast.makeText(MapViewActivity.this, position.latitude + "," + position.longitude, Toast.LENGTH_LONG).show();

//                LatLng Dhaka=new LatLng(23.7000,90.3667);
                    if(mStatusChecker!=null) {
                        setMarker(position);
                    }
                    if(shouldDrawRoute) {
                        prepareRouteCoords();
                        mStatusChecker.run();
                    }
                }else
                {
                    Toast.makeText(MapViewActivity.this,"Sorry, no location available", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MapViewActivity.this,"Failed",Toast.LENGTH_LONG).show();

                LatLng Dhaka=new LatLng(23.728560,90.400099);
                setMarker(Dhaka);

                drawRoutes("23.732527,90.395915","23.728208,90.403607");

            }
        });
    }

    public void prepareRouteCoords()
    {
        String coords=passedVehicle.getRoutesCoords();
        if(null!=coords) {
            String[] partCoords = coords.split(";");
            if (partCoords.length > 1) {
                for (int i = 1; i < partCoords.length; i++) {
                    drawRoutes(partCoords[i], partCoords[i - 1]);
                }
            } else {
                Toast.makeText(this, "Sorry, cannot draw any routes", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void drawRoutes(String origin, String destination)
    {
        Call<DirectionResponse> call=directionApiAdapter.directionApi.getAllVehicle(origin,destination,getString(R.string.new_map_key));
        call.enqueue(new Callback<DirectionResponse>() {
            @Override
            public void onResponse(Response<DirectionResponse> response, Retrofit retrofit) {
                DirectionResponse directionResponse=response.body();
                List<Step> steps=directionResponse.getRoutes().get(0).getLegs().get(0).getSteps();
/*                googleMap.addPolyline(new PolylineOptions()
                        .addAll(PolyUtil.decode(directionResponse.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getPolyline().getPoints()))
                        .width(5)
                        .color(Color.RED));*/
                drawLinesFromSteps(steps);
                Log.d("Yes",response.body().toString());
                int i=0;
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MapViewActivity.this,"Failed again",Toast.LENGTH_LONG).show();
                t.printStackTrace();
                int i=0;
            }
        });
    }
    
    public void drawLinesFromSteps(List<Step> steps)
    {
        for (Step step :
                steps) {
            googleMap.addPolyline(new PolylineOptions()
                    .addAll(PolyUtil.decode(step.getPolyline().getPoints()))
                    .width(5)
                    .color(Color.RED));
        }
    }

    private void setMarker(LatLng marker)
    {
        if(drawnMarker==null) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .zoom(15)
                    .target(marker)
                    .build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        } else
        {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(marker)
                    .build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            drawnMarker.remove();
        }

        MarkerOptions endMarkerOption = new MarkerOptions().position(marker)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        drawnMarker=googleMap.addMarker(endMarkerOption);
        if(null!=mapView) {
            mapView.onResume();
        }
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
            mStatusChecker=null;
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
            mStatusChecker=null;
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
                    addFavorites(item);
/*                    item.setIcon(getResources().getDrawable(android.R.drawable.btn_star_big_on));
                    passedVehicle.setIsFavorite("true");*/
                }else if(passedVehicle.getIsFavorite().toLowerCase().equals("true"))
                {
                    removeFavorites(item);
/*                    item.setIcon(getResources().getDrawable(android.R.drawable.btn_star_big_off));
                    passedVehicle.setIsFavorite("false");*/
                }
                break;

            case android.R.id.home:
                mStatusChecker=null;
                exited=true;
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addFavorites(final MenuItem menuItem)
    {
        Call<LoginResponse> call=apiAdapter.vlrpApi.addFavorites(Tools.AuthToken,new FavoritesModel(passedVehicle.getVehicleId()));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                if(response.isSuccess())
                {
                    menuItem.setIcon(getResources().getDrawable(android.R.drawable.btn_star_big_on));
                    passedVehicle.setIsFavorite("true");
                } else
                {
                    Toast.makeText(MapViewActivity.this,"Sorry Cannot change right now",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MapViewActivity.this,"Failed, Try Later!!!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void removeFavorites(final MenuItem menuItem)
    {
        Call<LoginResponse> call=apiAdapter.vlrpApi.removeFavorites(Tools.AuthToken,new FavoritesModel(passedVehicle.getVehicleId()));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                if(response.isSuccess())
                {
                    menuItem.setIcon(getResources().getDrawable(android.R.drawable.btn_star_big_off));
                    passedVehicle.setIsFavorite("false");
                } else
                {
                    Toast.makeText(MapViewActivity.this,"Sorry Cannot change right now",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MapViewActivity.this,"Failed, Try later!!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}

package com.du.iit.zayed.vlrp_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.du.iit.zayed.vlrp_android.Utils.Tools;
import com.du.iit.zayed.vlrp_android.adapter.ApiAdapter;
import com.du.iit.zayed.vlrp_android.adapter.RecyclerViewListAdapter;
import com.du.iit.zayed.vlrp_android.models.Vehicle;
import com.du.iit.zayed.vlrp_android.models.VehicleListResponse;
import com.du.iit.zayed.vlrp_android.models.VehicleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements RecyclerViewListAdapter.OnVehicleElementClicked{

    RecyclerView vehicleRecyclerView;
    ArrayList<VehicleResponse> vehicles;
    ArrayList<VehicleResponse> filteredResult;
    EditText searchEditText;
    RecyclerViewListAdapter recyclerViewListAdapter;
    Spinner filterSpinner;

    public final static String PASSED_OBJECT="vehicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ApiAdapter apiAdapter=new ApiAdapter();
        Call<List<VehicleResponse>> call=apiAdapter.vlrpApi.getAllVehicle(Tools.AuthToken);
        call.enqueue(new Callback<List<VehicleResponse>>() {
            @Override
            public void onResponse(Response<List<VehicleResponse>> response, Retrofit retrofit) {
                vehicles=new ArrayList<>(response.body());
                filteredResult=new ArrayList<>();

                vehicleRecyclerView=(RecyclerView) findViewById(R.id.rclv_vehicle_list);
                recyclerViewListAdapter=new RecyclerViewListAdapter(MainActivity.this, vehicles);
                recyclerViewListAdapter.setOnVehicleElementClicked(MainActivity.this);
                vehicleRecyclerView.setAdapter(recyclerViewListAdapter);
                vehicleRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                initializeViews();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initializeViews()
    {
        searchEditText =(EditText) findViewById(R.id.et_search_box);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterResultsByString(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        filterSpinner=(Spinner) findViewById(R.id.sp_filter_spinner);
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filterResultBySelectedPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void filterResultBySelectedPosition(int position)
    {
        switch (position)
        {
            case 0:
                filteredResult=vehicles;
                filterResultsByString("");
                break;

            case 1:
                filterResultsByFavorites();
                break;
        }
    }

    private void filterResultsByString(String searchString) {
        filteredResult=new ArrayList<>();
        for (VehicleResponse vehicle :
                vehicles) {
/*            if (vehicle.getVehicleName().toLowerCase().contains(searchString.toLowerCase()))
            {
                filteredResult.add(vehicle);
            }else */
            if(vehicle.getRoutes().toLowerCase().contains(searchString.toLowerCase()))
            {
                filteredResult.add(vehicle);
            }else if(vehicle.getDriverName().toLowerCase().contains(searchString.toLowerCase()))
            {
                filteredResult.add(vehicle);
            }
        }

        recyclerViewListAdapter.setVehicles(filteredResult);
        recyclerViewListAdapter.notifyDataSetChanged();
    }

    private void filterResultsByFavorites() {
        filteredResult=new ArrayList<>();
        for (VehicleResponse vehicle :
                vehicles) {
            if (vehicle.getIsFavorite().toLowerCase().contains("true"))
            {
                filteredResult.add(vehicle);
            }
        }

        recyclerViewListAdapter.setVehicles(filteredResult);
        recyclerViewListAdapter.notifyDataSetChanged();
    }

    private void fillUpWithMockData(ArrayList<Vehicle> vehicles) {

        for (int i=0;i<5;i++)
        {
            Vehicle vehicle= new Vehicle();
            vehicle.mockDataFiller(i);
            vehicles.add(vehicle);
        }
    }

    @Override
    public void onVehicleClicked(VehicleResponse vehicle) {
        Intent intent=new Intent(this,MapViewActivity.class);
        intent.putExtra(PASSED_OBJECT,vehicle);
        startActivity(intent);
    }
}

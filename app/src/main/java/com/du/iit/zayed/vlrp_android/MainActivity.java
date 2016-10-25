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

import com.du.iit.zayed.vlrp_android.adapter.RecyclerViewListAdapter;
import com.du.iit.zayed.vlrp_android.models.Vehicle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewListAdapter.OnVehicleElementClicked{

    RecyclerView vehicleRecyclerView;
    ArrayList<Vehicle> vehicles;
    ArrayList<Vehicle> filteredResult;
    EditText searchEditText;
    RecyclerViewListAdapter recyclerViewListAdapter;
    Spinner filterSpinner;

    public final static String PASSED_OBJECT="vehicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        vehicles=new ArrayList<>();
        filteredResult=new ArrayList<>();
        fillUpWithMockData(vehicles);

        vehicleRecyclerView=(RecyclerView) findViewById(R.id.rclv_vehicle_list);
        recyclerViewListAdapter=new RecyclerViewListAdapter(this, vehicles);
        recyclerViewListAdapter.setOnVehicleElementClicked(this);
        vehicleRecyclerView.setAdapter(recyclerViewListAdapter);
        vehicleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        for (Vehicle vehicle :
                vehicles) {
            if (vehicle.getVehicleName().toLowerCase().contains(searchString.toLowerCase()))
            {
                filteredResult.add(vehicle);
            }else if(vehicle.getRoutes().toLowerCase().contains(searchString.toLowerCase()))
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
        for (Vehicle vehicle :
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
    public void onVehicleClicked(Vehicle vehicle) {
        Intent intent=new Intent(this,MapViewActivity.class);
        intent.putExtra(PASSED_OBJECT,vehicle);
        startActivity(intent);
    }
}

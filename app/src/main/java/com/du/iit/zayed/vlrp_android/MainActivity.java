package com.du.iit.zayed.vlrp_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.du.iit.zayed.vlrp_android.adapter.RecyclerViewListAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView vehicleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicleRecyclerView=(RecyclerView) findViewById(R.id.rclv_vehicle_list);
        RecyclerViewListAdapter recyclerViewListAdapter=new RecyclerViewListAdapter(this, null);
        vehicleRecyclerView.setAdapter(recyclerViewListAdapter);

        vehicleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

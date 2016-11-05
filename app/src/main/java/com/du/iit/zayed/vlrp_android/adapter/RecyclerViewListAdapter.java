package com.du.iit.zayed.vlrp_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.du.iit.zayed.vlrp_android.R;
import com.du.iit.zayed.vlrp_android.models.Vehicle;
import com.du.iit.zayed.vlrp_android.models.VehicleResponse;

import java.util.ArrayList;


/**
 * Created by chandradasdipok on 5/17/2016.
 */
public class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewListAdapter.VehicleViewHolder>{

    private ArrayList<VehicleResponse> vehicles;
    Context context;
    private OnVehicleElementClicked onVehicleElementClicked;

    public RecyclerViewListAdapter(Context context,ArrayList<VehicleResponse> vehicles)
    {
        this.context=context;
        this.setVehicles(vehicles);
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle,parent,false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        VehicleResponse tempVehicle=getItem(position);

        holder.vehicleNameTextView.setText("Noooooo");
        holder.driverNameTextView.setText(tempVehicle.getDriverName());
        holder.routesTextView.setText(tempVehicle.getRoutes());
        holder.universityTextView.setText(tempVehicle.getUniversityName());
        holder.statusTextView.setText(tempVehicle.getActiveTime());
    }

    @Override
    public int getItemCount() {
        return getVehicles().size();
    }

    public VehicleResponse getItem(int position)
    {
        return getVehicles().get(position);
    }

    public OnVehicleElementClicked getOnVehicleElementClicked() {
        return onVehicleElementClicked;
    }

    public void setOnVehicleElementClicked(OnVehicleElementClicked onVehicleElementClicked) {
        this.onVehicleElementClicked = onVehicleElementClicked;
    }

    public ArrayList<VehicleResponse> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<VehicleResponse> vehicles) {
        this.vehicles = vehicles;
    }

    public class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView vehicleNameTextView;
        TextView driverNameTextView;
        TextView routesTextView;
        TextView statusTextView;
        TextView universityTextView;
        Button mapViewButton;

        public VehicleViewHolder(View itemView) {
            super(itemView);

            vehicleNameTextView=(TextView) itemView.findViewById(R.id.tv_vehicle_name);
            driverNameTextView=(TextView) itemView.findViewById(R.id.tv_driver_name);
            routesTextView=(TextView) itemView.findViewById(R.id.tv_routes);
            statusTextView=(TextView) itemView.findViewById(R.id.tv_status);
            universityTextView=(TextView) itemView.findViewById(R.id.tv_university);
            mapViewButton=(Button) itemView.findViewById(R.id.btn_map);

            mapViewButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onVehicleElementClicked.onVehicleClicked(getItem(getAdapterPosition()));
        }
    }

    public interface OnVehicleElementClicked
    {
        void onVehicleClicked(VehicleResponse vehicle);
    }
}

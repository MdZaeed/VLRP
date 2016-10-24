package com.du.iit.zayed.vlrp_android.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleListResponse {

    @SerializedName("vehicles")
    @Expose
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();

    /**
     * 
     * @return
     *     The vehicles
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * 
     * @param vehicles
     *     The vehicles
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

}

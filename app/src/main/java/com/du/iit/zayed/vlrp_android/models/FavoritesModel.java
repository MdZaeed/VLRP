package com.du.iit.zayed.vlrp_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Zayed on 20-Nov-16.
 */
public class FavoritesModel {

    @SerializedName("vehicleid")
    @Expose
    private String vehicleId;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public FavoritesModel(String vehicleId)
    {
        this.vehicleId=vehicleId;
    }
}

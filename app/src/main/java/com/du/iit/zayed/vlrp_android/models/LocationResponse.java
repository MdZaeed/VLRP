package com.du.iit.zayed.vlrp_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationResponse {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("VehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("LocationId")
    @Expose
    private String locationId;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The vehicleId
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * 
     * @param vehicleId
     *     The VehicleId
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The Longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The Latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The Time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The locationId
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * 
     * @param locationId
     *     The LocationId
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

}

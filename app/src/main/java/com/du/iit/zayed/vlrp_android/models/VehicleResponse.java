package com.du.iit.zayed.vlrp_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VehicleResponse implements Serializable{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("DriverName")
    @Expose
    private String driverName;
    @SerializedName("Routes")
    @Expose
    private String routes;
    @SerializedName("ActiveTime")
    @Expose
    private String activeTime;
    @SerializedName("UniversityName")
    @Expose
    private String universityName;
    @SerializedName("VehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("IsFavorite")
    @Expose
    private String isFavorite;

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
     *     The driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * 
     * @param driverName
     *     The DriverName
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /**
     * 
     * @return
     *     The routes
     */
    public String getRoutes() {
        return routes;
    }

    /**
     * 
     * @param routes
     *     The Routes
     */
    public void setRoutes(String routes) {
        this.routes = routes;
    }

    /**
     * 
     * @return
     *     The activeTime
     */
    public String getActiveTime() {
        return activeTime;
    }

    /**
     * 
     * @param activeTime
     *     The ActiveTime
     */
    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    /**
     * 
     * @return
     *     The universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * 
     * @param universityName
     *     The UniversityName
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}

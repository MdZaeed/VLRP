package com.du.iit.zayed.vlrp_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VehicleResponse implements Serializable{

    public VehicleResponse(String id,String driverName,String routes,String activeTime,String universityName,String vehicleId, String isFavorite)
    {
        this.id=id;
        this.driverName=driverName;
        this.routes=routes;
        this.activeTime=activeTime;
        this.universityName=universityName;
        this.vehicleId=vehicleId;
        this.isFavorite=isFavorite;
        this.routesCoords="23.728158,90.403263;23.732696,90.395216;23.732401,90.385088;23.764137,90.370809";
    }

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
    @SerializedName("RouteCoords")
    @Expose
    private String routesCoords;

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

    public String getRoutesCoords() {
        return routesCoords;
    }

    public void setRoutesCoords(String routesCoords) {
        this.routesCoords = routesCoords;
    }
}

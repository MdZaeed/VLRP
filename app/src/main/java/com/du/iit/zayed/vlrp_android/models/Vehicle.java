
package com.du.iit.zayed.vlrp_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("vehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("driverName")
    @Expose
    private String driverName;
    @SerializedName("routes")
    @Expose
    private String routes;
    @SerializedName("activeTime")
    @Expose
    private String activeTime;
    @SerializedName("universityName")
    @Expose
    private String universityName;

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
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The vehicleName
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * 
     * @param vehicleName
     *     The vehicleName
     */
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
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
     *     The driverName
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
     *     The routes
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
     *     The activeTime
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
     *     The universityName
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public void mockDataFiller(int serial)
    {
        id="id : " + serial;
        vehicleName="vehicle : " + serial;
        driverName="Samrat : " + serial;
        routes="Sahidullah : " + serial;
        activeTime="Active Time" + serial;
        universityName="University : " + serial;
    }
}

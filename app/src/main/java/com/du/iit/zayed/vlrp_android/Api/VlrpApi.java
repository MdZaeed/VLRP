package com.du.iit.zayed.vlrp_android.Api;

import com.du.iit.zayed.vlrp_android.models.LocationResponse;
import com.du.iit.zayed.vlrp_android.models.LoginPost;
import com.du.iit.zayed.vlrp_android.models.LoginResponse;
import com.du.iit.zayed.vlrp_android.models.VehicleResponse;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by BS-86 on 4/15/2016.
 */
public interface VlrpApi {

    @GET("/api/Vehicles")
    Call<List<VehicleResponse>> getAllVehicle(@Header("Auth-key") String authuytr);

    @GET("/api/getlocation")
    Call<List<LocationResponse>> getLocation(@Query("id") int id);

    @POST("/api/login")
    Call<LoginResponse> login(@Body LoginPost loginPost);
}
package com.du.iit.zayed.vlrp_android.Api;

import com.du.iit.zayed.vlrp_android.models.DirectionResponse;
import com.du.iit.zayed.vlrp_android.models.VehicleResponse;

import org.json.JSONObject;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by Zayed on 21-Nov-16.
 */
public interface DirectionApi {

    @GET("api/directions/json")
    Call<DirectionResponse> getAllVehicle(@Query("origin") String origin,
                                          @Query("destination") String destination,
                                          @Query("key") String apiKey);
}

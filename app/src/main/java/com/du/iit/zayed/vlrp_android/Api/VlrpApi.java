package com.du.iit.zayed.vlrp_android.Api;

import com.du.iit.zayed.vlrp_android.models.FavoritesModel;
import com.du.iit.zayed.vlrp_android.models.LocationResponse;
import com.du.iit.zayed.vlrp_android.models.LoginPost;
import com.du.iit.zayed.vlrp_android.models.LoginResponse;
import com.du.iit.zayed.vlrp_android.models.RegisterPostModel;
import com.du.iit.zayed.vlrp_android.models.VehicleResponse;

import org.json.JSONObject;

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

    @GET("/api/location")
    Call<List<LocationResponse>> getLocation(@Query("id") int id);

    @POST("/api/login")
    Call<LoginResponse> login(@Body LoginPost loginPost);

    @POST("/api/addfavorites")
    Call<LoginResponse> addFavorites(@Header("Auth-key") String authkey,@Body FavoritesModel favoritesModel);

    @POST("/api/removefavorites")
    Call<LoginResponse> removeFavorites(@Header("Auth-key") String authkey,@Body FavoritesModel favoritesModel);

    @POST("api/resgister")
    Call<JSONObject> register(@Body RegisterPostModel registerPostModel);
}
package com.du.iit.zayed.vlrp_android.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.du.iit.zayed.vlrp_android.MainActivity;
import com.du.iit.zayed.vlrp_android.models.VehicleResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zayed on 25-Oct-16.
 */
public class Tools {

    public static final String[] driverNames=
            { "Rudra" , "Shaon" , "Fuad", "Sharif" , "Atiq" };

    public static final String[] vehicleNames=
            {"Bus 1","Rickshaw 1","Bus 2","Bus 3","Cycle 1"};

    public static final String[] routes=
            {"kalabagan,dhanmondi","jhenidah,khulna","mugda,badda","du,ku","ruet,buet"};

    public static final String[] activeTimes=
            {"9-12","12-15","18-21","24-27","1-4"};

    public static final String[] universityNames=
            {"University of Dhaka","Jahangirnagar University","KUET","BUET","K. C. College"};

    public static final String[] booleanStrings=
            {"true","false"};

    public static String AuthToken="pe2fEBQ5Fv7pMQBln8JcDaqkICS9aum7Fj2LeVw+Zpa7MBwlUOZkXSIzQ6ZutY7mv0TWT7a7+vpE8XquusuXAoJNQAAOZYv4O1wUyWpEqsjN+zxql6HPvqXmhy2t5BDs";

    public static List<VehicleResponse> getMockVehicleResposnse()
    {
        List<VehicleResponse> vehicleResponses=new ArrayList<>();

        for(int i=0;i<5;i++)
        {
            VehicleResponse vehicleResponse=new VehicleResponse(i+"","Driver " + i,
                    "Routes " + i,"Status " + i,"University " + i, i+"","true");
            vehicleResponses.add(vehicleResponse);
        }


        return vehicleResponses;
    }
}

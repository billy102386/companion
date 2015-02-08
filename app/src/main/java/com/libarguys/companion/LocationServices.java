package com.libarguys.companion;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by androiddev on 2/8/15.
 */
public class LocationServices extends Service implements LocationListener {
    private LocationManager locationManager;
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    private Location userLoc;
    private double lat;
    private double lon;
public LocationServices(Context context) {
    locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
    Boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    Log.i("CompanionGPS", "GPS Enabled: " + isGPSEnabled);
    Double lat = 0.0;
    Double lon = 0.0;
    if (isGPSEnabled) {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        Log.d("GPS Enabled", "GPS Enabled");
        if (locationManager != null) {
            userLoc = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (userLoc != null) {
                lat = userLoc.getLatitude();
                lon = userLoc.getLongitude();
            }
        }

        Log.i("CompanionGPS", "Lat: " + lat);
        Log.i("CompanionGPS", "Lon: " + lon);
    }
}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(userLoc != null){
            lat = userLoc.getLatitude();
        }

        // return latitude
        return lat;
    }

    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(userLoc != null){
            lon = userLoc.getLongitude();
        }

        // return longitude
        return lon;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

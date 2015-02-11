package com.libarguys.companion;

import com.libarguys.companion.model.WeatherResponse;
import com.libarguys.companion.view.WeatherView;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by androiddev on 2/8/15.
 */
public interface Api {

    @GET("/weather")
    WeatherResponse getWeather(@Query("lat") Double lat, @Query("lon") Double lon, @Query("units") String units);
   // void getWeather(@Query("lat") Double lat, @Query("lon") Double lon, @Query("units") String units,
     //               Callback<WeatherResponse> callback);

}

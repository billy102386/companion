package com.libarguys.companion.view;

import android.util.Log;

import com.libarguys.companion.LocationServices;
import com.libarguys.companion.RestClient;
import com.libarguys.companion.model.WeatherResponse;
import com.libarguys.companion.util.TimeOfDay;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by rich on 2/8/15.
 */
public class WeatherView implements IMessage {

    private double _dLat;
    private double _dLon;


    private WeatherResponse _wrWeatherResponse;


    public WeatherView(Double dLat, Double dLon)
    {
        _dLat = dLat;
        _dLon = dLon;

    }


    public String getMessage() {

        checkWeather();
        WeatherResponse w = MessageFactory.getFactory().getWeatherResponse();
        if ( w!= null) {


            TimeOfDay tod = TimeOfDay.getTimeOfDay();


            if (tod == TimeOfDay.LATE_NIGHT)
                return getMessageNight();
            else if (tod == TimeOfDay.EARLY_MORNING)
                return getMessageMorning();
            else if (tod == TimeOfDay.MORNING)
                return getMessageMorning();
            else if (tod == TimeOfDay.NOON)
                return getMessageAfternoon();
            else if (tod == TimeOfDay.AFTERNOON)
                return getMessageAfternoon();
            else if (tod == TimeOfDay.EVENING)
                return getMessageAfternoon();
            else if (tod == TimeOfDay.NIGHT)
                return getMessageNight();
        }

        return "";
    }


    private void checkWeather()
    {

        Log.i("Companion", "Making HTTP Call for Weather");
        /*RestClient.get().getWeather(_dLat,_dLon,"imperial", new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                // success!
                MessageFactory.getFactory().setWeatherResponse(weatherResponse);

                 Log.i("App", weatherResponse.getWeather().get(0).getDescription());
                // you get the point...
            }

            @Override
            public void failure(RetrofitError error) {
                // something went wrong
                Log.e("Companion",error.getMessage());

                _wrWeatherResponse = null;
            }
        });*/
        WeatherResponse weatherResponse=RestClient.get().getWeather(_dLat,_dLon,"imperial");
        MessageFactory.getFactory().setWeatherResponse(weatherResponse);

        Log.i("WeatherView","Weather:"+weatherResponse.getWeather().get(0).getDescription());

    }


    private String getMessageMorning()
    {
        return "Weather Morning";
    }

    private String getMessageAfternoon()
    {
        return "Weather Afternoon";
    }

    private String getMessageNight()
    {
        return "Weather Night";
    }



}

package com.libarguys.companion.view;

import android.content.Context;
import android.util.Log;

import com.libarguys.companion.LocationServices;
import com.libarguys.companion.MainActivity;
import com.libarguys.companion.RestClient;
import com.libarguys.companion.SettingsFactory;
import com.libarguys.companion.model.WeatherResponse;
import com.libarguys.companion.util.TimeOfDay;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by rich on 2/8/15.
 */
public class WeatherView implements IMessage {

    private WeatherResponse res;
    private Context context;

    public WeatherView(Context c)
    {
        context = c;
    }


    public String getMessage() {

        res = checkWeather();


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


        return "";
    }


    private WeatherResponse checkWeather()
    {

        Log.i("Companion", "Making HTTP Call for Weather");

        LocationServices locServices = MainActivity.locServices;
        //double lat = 0.0;
        //double lon = 0.0;


        WeatherResponse weatherResponse=RestClient.get().getWeather(locServices.getLatitude(),locServices.getLongitude(),"imperial");

        Log.i("WeatherView","Weather:"+weatherResponse.getWeather().get(0).getDescription());

        return weatherResponse;

    }


    private String getMessageMorning()
    {
        return "Weather Morning";
    }

    private String getMessageAfternoon()
    {
      return "Current conditions for " + res.getName() + ", " + res.getWeather().get(0).getDescription();

    }

    private String getMessageNight()
    {
        return "Weather Night";
    }



}

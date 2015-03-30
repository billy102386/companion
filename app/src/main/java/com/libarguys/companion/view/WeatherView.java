package com.libarguys.companion.view;

import android.content.Context;
import android.util.Log;

import com.libarguys.companion.LocationServices;
import com.libarguys.companion.MainActivity;
import com.libarguys.companion.RestClient;
import com.libarguys.companion.SettingsFactory;
import com.libarguys.companion.model.ForecastResponse;
import com.libarguys.companion.model.WeatherResponse;
import com.libarguys.companion.util.TimeOfDay;
import com.libarguys.companion.util.Util;

import java.util.Calendar;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by rich on 2/8/15.
 */
public class WeatherView implements IMessage {

    private WeatherResponse res;
    private Context context;
    private final String api_key = "3efff6d147054c27a15320da0731d70a";

    public WeatherView(Context c)
    {
        context = c;
    }


    public String getMessage() {

        res = checkWeather();


            TimeOfDay tod = TimeOfDay.getTimeOfDay();


            if (tod == TimeOfDay.LATE_NIGHT)
                return getMessageLateNight();
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



        WeatherResponse weatherResponse=RestClient.get().getWeather(locServices.getLatitude(),locServices.getLongitude(),"imperial",api_key);

        Log.i("WeatherView","Weather:"+weatherResponse.getWeather().get(0).getDescription());

        return weatherResponse;

    }

    private ForecastResponse getForecast()
    {
        Log.i("Companion", "Making HTTP Call for Weather");

        LocationServices locServices = MainActivity.locServices;

        ForecastResponse forecastResponse = RestClient.get().getForecast(locServices.getLatitude(), locServices.getLongitude(), "imperial", api_key);



        Log.i("WeatherView","Forecast response received");

        return forecastResponse;

    }


    private String getMessageMorning()
    {

        int highs = (int) Math.round(getForecast().getList().get(0).getTemp().getMax());
        int lows = (int)Math.round(getForecast().getList().get(0).getTemp().getMin());

        String sDescr = NormalizeWeatherDescription(getForecast().getList().get(0).getWeather().get(0).getDescription());


        return "Current conditions for " + res.getName() + ", " + res.getWeather().get(0).getDescription() + ". Today's highs will reach " + highs + ", with lows approaching " + lows + ". ";
    }

    private String getMessageAfternoon()
    {
      return "Current conditions for " + res.getName() + ", " + res.getWeather().get(0).getDescription() + ". ";

    }

    private String getMessageNight()
    {
        double nightTemp = getForecast().getList().get(0).getTemp().getNight();

        nightTemp = Math.round(nightTemp);

        int iNightTemp = (int)nightTemp;

        return "Current conditions for " + res.getName() + ", " + res.getWeather().get(0).getDescription() + ". Low's tonight will near " + iNightTemp + ". ";
    }

    private String NormalizeWeatherDescription(String sDesc)
    {

        String sLDesc = sDesc.toLowerCase();

        if(sLDesc.equals("sky is clear"))
            return "Clear Skies";

        return sDesc;

    }


    private String getMessageLateNight()
    {
        long lUnixTime=0;
        Calendar c;

        Calendar now = Util.EasyCalendar();

        int index = -1;

        do
        {
            index = index+1;
            lUnixTime = getForecast().getList().get(index).getDt();
            c = Util.EasyCalendar(lUnixTime);


        }
        while(c.getTime().compareTo(now.getTime()) < 1 && index < 3);





        int highs = (int) Math.round(getForecast().getList().get(index).getTemp().getMax());
        int lows = (int)Math.round(getForecast().getList().get(index).getTemp().getMin());

        String sDescr = NormalizeWeatherDescription(getForecast().getList().get(index).getWeather().get(0).getDescription());
        Calendar c2 = Util.EasyCalendar(getForecast().getList().get(index).getDt());

        Log.i("LateNightWeather", "Weather for " + c2.getTime().toString());

        return "Current conditions for " + res.getName() + ", " + res.getWeather().get(0).getDescription() + ". Tomorrows forecast is " + sDescr + " with high's nearing " + highs + " and lows approaching  " + lows + ". ";
    }




}

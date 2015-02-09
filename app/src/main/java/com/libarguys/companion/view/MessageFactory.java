package com.libarguys.companion.view;

import com.libarguys.companion.model.WeatherResponse;

/**
 * Created by rich on 2/8/15.
 */
public class MessageFactory {

    private static MessageFactory _factory;

    public static MessageFactory getFactory()
    {
        if(_factory == null)
        {
            _factory = new MessageFactory();
        }

        return _factory;
    }

    private double _dLat;
    private double _dLon;

    private WeatherResponse _wrWeatherResponse;

    public WeatherResponse getWeatherResponse()
    {
        return _wrWeatherResponse;
    }

    public void setWeatherResponse(WeatherResponse wr)
    {
        _wrWeatherResponse = wr;
    }



    public MessageFactory(double dLat, double dLon)
    {
        _dLat = dLat;
        _dLon = dLon;
    }

    public MessageFactory()
    {
        _dLat =0;
        _dLon = 0;
    }

    public double getLat()
    {
        return _dLat;
    }

    public double getLon()
    {
        return _dLon;
    }

    public void setLat(double dLat)
    {
        _dLat = dLat;

    }

    public void setLon(double dLon)
    {
        _dLon = dLon;
    }


    public String getMessages()
    {
        String sMessage = "";

        GreetingView gv = new GreetingView();
        sMessage += gv.getMessage();


        WeatherView wv = new WeatherView(_dLat, _dLon);

        sMessage += wv.getMessage();

        return sMessage;



    }



}

package com.libarguys.companion.controller;

import com.libarguys.companion.model.ForecastResponse;
import com.libarguys.companion.model.WeatherResponse;

/**
 * Created by rich on 2/15/15.
 */
public class WeatherController {

    private WeatherResponse _wrRes;
    private ForecastResponse _fcRes;


    public WeatherController(WeatherResponse wr, ForecastResponse fc)
    {
        _wrRes = wr;
        _fcRes = fc;
    }

    public Double getTodaysHigh()
    {
        return 1000.0;
    }
}

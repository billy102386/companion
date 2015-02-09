package com.libarguys.companion.model;

import java.util.List;

/**
 * Created by androiddev on 2/8/15.
 */
public class WeatherResponse {

    private int cod;
    private String base;
    private Weather[] weather;


    public WeatherResponse(int cod, String base, Weather[] weather) {
        this.cod = cod;
        this.base = base;
        this.weather = weather;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getBase() {
        return base;
    }

    public Weather[] getWeather()
    {
        return weather;
    }




}

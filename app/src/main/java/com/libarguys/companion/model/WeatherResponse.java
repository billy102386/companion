package com.libarguys.companion.model;

import java.util.List;

/**
 * Created by androiddev on 2/8/15.
 */
public class WeatherResponse {

    private int cod;
    private String base;
    private Weather main;

    public WeatherResponse(int cod, String base) {
        this.cod = cod;
        this.base = base;
       // this.main = main;
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

    public void setBase(String base) {
        this.base = base;
    }
    public Weather getMain() {
        return main;
    }

    public void setMain(Weather main) {
        this.main = main;
    }

}

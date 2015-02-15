package com.libarguys.companion.model;

/**
 * Created by rich on 2/15/15.
 */
public class SettingsTest {

    private Double _dLat;
    private Double _dLon;

    public SettingsTest()
    {}

    public Double getLat()
    {
        return _dLat;
    }

    public Double getLon()
    {
        return _dLon;
    }

    public void setLat(Double dLat)
    {
        if(dLat == 0)
            dLat = 40.6924402; //wantagh
        _dLat = dLat;
    }

    public void setLon(Double dLon)
    {
        if(dLon == 0)
            dLon = -73.52564152;

        _dLon = dLon;
    }


}

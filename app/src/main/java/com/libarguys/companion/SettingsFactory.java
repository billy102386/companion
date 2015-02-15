package com.libarguys.companion;

import com.libarguys.companion.model.SettingsTest;

/**
 * Created by rich on 2/15/15.
 */
public class SettingsFactory {


    private static SettingsTest _settings;

    public static SettingsTest getSettings()
    {
        if(_settings == null)
        {
            _settings = new SettingsTest();
        }


        return _settings;
    }




}

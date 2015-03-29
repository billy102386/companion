package com.libarguys.companion.model;

import com.libarguys.companion.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rich on 2/15/15.
 */
public class Countdown {

    private Calendar _calTarget;

    private String _sTitle;


    public Countdown(Calendar target, String sTitle)
    {
        _calTarget = target;
        _sTitle = sTitle;
    }

    public long getDaysUntil()
    {
        Calendar c = Calendar.getInstance();

        return daysBetween(c, _calTarget);



    }


    public static long daysBetween(Calendar startDate, Calendar endDate) {

        long daysBetween = 0;
        while (startDate.before(endDate)) {
            startDate.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }

        return daysBetween;
    }

    public String getTitle()
    {
        return _sTitle;
    }


    public static ArrayList<Countdown> GetCountdownEvents()
    {
        //TODO Interface with Settings to get list of countdown events;

        Calendar target = Util.EasyCalendar(2015, 4,1);

        Countdown c = new Countdown(target, "Trip to Ireland");

        ArrayList<Countdown> countdowns = new ArrayList<Countdown>();
        countdowns.add(c);

        return countdowns;

    }

}

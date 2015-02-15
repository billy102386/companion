package com.libarguys.companion.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rich on 2/15/15.
 */
public class Util {

    public static Calendar DateToCalendar(Date dt)
    {
        Calendar cal = Calendar.getInstance();       // get calendar instance
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millisecond in second

        return cal;                                  // return the date part
    }

    public static Calendar EasyCalendar(int year, int month, int date)
    {
        Calendar cal = Calendar.getInstance();       // get calendar instance
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, date);
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millisecond in second

        return cal;
    }

}

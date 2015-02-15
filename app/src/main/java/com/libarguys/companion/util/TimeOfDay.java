package com.libarguys.companion.util;

import java.util.Calendar;

/**
 * Created by rich on 2/8/15.
 */

public enum TimeOfDay
{
    //TODO: allow for minutes
    LATE_NIGHT(0, 4), EARLY_MORNING(4, 6), MORNING(6,12), NOON(12, 15), AFTERNOON(15, 18), EVENING(18, 20), NIGHT(20, 24);

    public final double START;
    public final double END;
    TimeOfDay(double start, double end) {
        this.START = start;
        this.END = end;
    }

    public static TimeOfDay getTimeOfDay() {
        Calendar c = Calendar.getInstance();

        return getTimeOfDay(c);

    }

    public static TimeOfDay getTimeOfDay(Calendar c)
    {
    
        int hour = c.get(Calendar.HOUR_OF_DAY);
        
        if (hour >= LATE_NIGHT.START && hour <= LATE_NIGHT.END)
            return LATE_NIGHT;
        else if (hour >= EARLY_MORNING.START && hour <= EARLY_MORNING.END)
            return EARLY_MORNING;
        else if (hour >= MORNING.START && hour <= MORNING.END)
            return MORNING;
        else if (hour >= NOON.START && hour <= NOON.END)
            return NOON;
        else if (hour >= AFTERNOON.START && hour <= AFTERNOON.END)
            return AFTERNOON;
        else if (hour >= EVENING.START && hour <= EVENING.END)
            return EVENING;
        else if (hour >= NIGHT.START && hour <= NIGHT.END)
            return NIGHT;

        return TimeOfDay.MORNING;

    }


}

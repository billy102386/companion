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

        if (c.HOUR >= LATE_NIGHT.START && c.HOUR <= LATE_NIGHT.END)
            return LATE_NIGHT;
        else if (c.HOUR >= EARLY_MORNING.START && c.HOUR <= EARLY_MORNING.END)
            return EARLY_MORNING;
        else if (c.HOUR >= MORNING.START && c.HOUR <= MORNING.END)
            return MORNING;
        else if (c.HOUR >= NOON.START && c.HOUR <= NOON.END)
            return NOON;
        else if (c.HOUR >= AFTERNOON.START && c.HOUR <= AFTERNOON.END)
            return AFTERNOON;
        else if (c.HOUR >= EVENING.START && c.HOUR <= EVENING.END)
            return EVENING;
        else if (c.HOUR >= NIGHT.START && c.HOUR <= NIGHT.END)
            return NIGHT;

        return TimeOfDay.MORNING;

    }


}

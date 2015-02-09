package com.libarguys.companion.view;

import com.libarguys.companion.util.TimeOfDay;

import java.util.Calendar;

/**
 * Created by rich on 2/8/15.
 */
public class GreetingView implements IMessage {

    public String getMessage()
    {
        TimeOfDay tod = TimeOfDay.getTimeOfDay();

        if (tod == TimeOfDay.LATE_NIGHT)
            return "Late night, Sir? Please be safe. ";
        else if (tod == TimeOfDay.EARLY_MORNING)
            return "Good Morning Rich! You're up early. ";
        else if (tod == TimeOfDay.MORNING)
            return "Good Morning Sir! ";
        else if (tod == TimeOfDay.NOON)
            return "Good afternoon bud. ";
        else if (tod == TimeOfDay.AFTERNOON)
            return "Good afternoon bud. ";
        else if (tod == TimeOfDay.EVENING)
            return "Good evening, Rich. ";
        else if (tod == TimeOfDay.NIGHT)
            return "Good evening, Rich. ";

        return "";
    }

}

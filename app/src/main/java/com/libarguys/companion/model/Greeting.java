package com.libarguys.companion.model;

import java.util.Date;
import java.util.*;


/**
 * Created by rich on 2/8/15.
 */
public class Greeting {

    final int LATE_NIGHT = 3;
    final int TOO_EARLY = 6;
    final int MORNING = 11;
    final int AFTERNOON = 18;
    final int EVENING = 24;



    public String getGreeting()
    {
        Calendar c = Calendar.getInstance();

        int iCurHour = c.get(Calendar.HOUR_OF_DAY);

        if (iCurHour < LATE_NIGHT)
            return "Late night, Sir? Please be safe. ";
        else if (iCurHour < TOO_EARLY)
            return "Good Morning Rich! You're up early. ";
        else if (iCurHour < MORNING)
            return "Good Morning Sir! ";
        else if (iCurHour < AFTERNOON)
            return "Good afternoon bud. ";
        else
            return "Good evening, Rich. ";
    }


}

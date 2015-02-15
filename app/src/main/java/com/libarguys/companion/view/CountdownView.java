package com.libarguys.companion.view;

import com.libarguys.companion.model.Countdown;

import java.util.ArrayList;

/**
 * Created by rich on 2/15/15.
 */
public class CountdownView implements IMessage {

    public String getMessage()
    {
        //Get countdowns;

        ArrayList<Countdown> countdowns = Countdown.GetCountdownEvents();

        return "There are " + countdowns.get(0).getDaysUntil() + " days until " + countdowns.get(0).getTitle();
    }


}

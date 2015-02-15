package com.libarguys.companion.view;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Calendars;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by androiddev on 2/10/15.
 */
public class CalendarView implements IMessage {

    static Cursor cursor;

    private String calMessage;
    Context context;


    ArrayList<String> CalendarNames = new ArrayList<String>();

    public CalendarView(Context inContext) {
        context=inContext;

        ContentResolver contentResolver = context.getContentResolver();

        String[] projection = new String[]{
                CalendarContract.Calendars._ID,
                CalendarContract.Calendars.ACCOUNT_NAME,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME};

        // Fetch a list of all calendars synced with the device, their display names and whether the
        Uri uri = Calendars.CONTENT_URI;

        cursor = contentResolver.query(uri, projection, null, null, null);

        CalendarNames = new ArrayList<String>();

        try {
            int j = 0;
            System.out.println("Count=" + cursor.getCount());
            if (cursor.getCount() > 0) {
                System.out.println("the control is just inside of the cursor.count loop");
                while (cursor.moveToNext()) {
                    j++;
                    String _id = cursor.getString(0);
                    String accName = cursor.getString(1);
                    String displayName = cursor.getString(2);

                    Log.i("CalendarView", "Id: " + _id + " acct Name: " + accName + " displayName:" + displayName);
                    CalendarNames.add(displayName);
                }
            }
            cursor.close();


        } catch (AssertionError ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public ArrayList<String> getCalendarEvents() {
            ArrayList<String> eventList = new ArrayList<String>();
            try{
                Uri.Builder eventsUriBuilder = CalendarContract.Instances.CONTENT_URI
                        .buildUpon();
                TimeZone tz = TimeZone.getDefault();
                Log.i("CalendarView", "Timezone: " + tz.getDisplayName());
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTimeZone(tz);
                Long timeNow = rightNow.getTimeInMillis();
                Calendar endOfDayCal = Calendar.getInstance();
                endOfDayCal.setTimeZone(tz);
                endOfDayCal.set(Calendar.HOUR_OF_DAY, 23);
                endOfDayCal.set(Calendar.MINUTE, 59);
                endOfDayCal.set(Calendar.SECOND, 59);

                long endOfToday = endOfDayCal.getTimeInMillis();
                Date now = rightNow.getTime();
                Date endOfTodayDate = endOfDayCal.getTime();
                String nowString = DateFormat.format("MM/dd/yyyy hh:mm:ss", now).toString();
                String endOfDayString = DateFormat.format("MM/dd/yyyy hh:mm:ss", endOfTodayDate).toString();

                Log.i("CalendarView", "Looking for events between " + nowString + "and" + endOfDayString);
                ContentUris.appendId(eventsUriBuilder, timeNow);
                ContentUris.appendId(eventsUriBuilder, endOfToday);
                Uri eventsUri = eventsUriBuilder.build();
                Cursor cursor = null;
                cursor = context.getContentResolver().query(eventsUri, new String[]{Calendars._ID, Events.TITLE, Events.DESCRIPTION,
                        Events.DTSTART, Events.DTEND, Events.EVENT_LOCATION, Events.EVENT_TIMEZONE}, null, null, CalendarContract.Instances.DTSTART + " ASC");
                int length = cursor.getCount();
                cursor.moveToFirst();
                Log.i("CalendarView", "Events Found= " + length);

                for (int i = 0; i < length; i++) {
                    long startTime = cursor.getLong(3);
                    String eventTimeZone = cursor.getString(6);
                    Log.i("CalendarView", " RAW start time = " + startTime + " TZ:" + eventTimeZone);
                    Date startTimeDate = new Date(startTime);

                    String dateString = DateFormat.format("MM/dd/yyyy hh:mm:ss", new Date(startTime)).toString();
                    String eventDesc = cursor.getString(1)+" at "+dateString;
                    Log.i("CalendarView", "Found Event on calendar :" + cursor.getString(0) + " named: " + eventDesc + " at: " + dateString);

                    eventList.add(eventDesc);
//                ce.setTitle(cursor.getString(1));
//                ce.setNote(cursor.getString(2));
//                ce.setDate(cursor.getLong(3));
//
//                if (cursor.getString(1).length() > 0) {
//                    mItems.add(ce);
//                }

                    cursor.moveToNext();
                }

                cursor.close();

            }
/*
            uri= CalendarContract.Events.CONTENT_URI;
            Log.i("CalendarView","Processed "+j+" calendars");
            Cursor cursor = context.getContentResolver().query(Uri.parse
                            ("content://com.android.calendar/events"),
                    new String[]{Calendars._ID, Events.TITLE, Events.DESCRIPTION,
                            Events.DTSTART, Events.DTEND, Events.EVENT_LOCATION}, "calendar_id=9", null, null);

            cursor.moveToFirst();

            int length = cursor.getCount();

            for (int i = 0; i < length; i++) {
              long startTime = cursor.getLong(3);
                String dateString= DateFormat.format("MM/dd/yyyy hh:mm:ss", new Date(startTime)).toString();

                Log.i("CalendarView","Found Event: "+cursor.getString(1)+" at: "+dateString);

//                ce.setTitle(cursor.getString(1));
//                ce.setNote(cursor.getString(2));
//                ce.setDate(cursor.getLong(3));
//
//                if (cursor.getString(1).length() > 0) {
//                    mItems.add(ce);
//                }

                cursor.moveToNext();
            }

            cursor.close();
        }
        */ catch (AssertionError ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return eventList;
        }


    public ArrayList<String> getCalendarNames() {
        return CalendarNames;
    }

    public void setCalendarNames(ArrayList<String> calendarNames) {
        CalendarNames = calendarNames;
    }



    @Override
    public String getMessage() {
        calMessage="";
        ArrayList<String> events = getCalendarEvents();
        for(String event:events)
        {
            calMessage+= event+" ";
        }
        Log.i("CalendarView","Calendar Message: "+calMessage);
        return calMessage;    }
}
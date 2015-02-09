package com.libarguys.companion;


import com.libarguys.companion.model.Greeting;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.EditText;

import com.libarguys.companion.model.WeatherResponse;

import java.io.FileOutputStream;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Greeting g = new Greeting();
        EditText output = (EditText)findViewById(R.id.txtOutput);

        output.setText(g.getGreeting());
        WriteFile();

    }


    protected void WriteFile()
    {
        String filename = "myoutput";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_WORLD_READABLE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("output", "wrote to file");
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void checkWeather(View view)
    {

           Log.i("Companion Main Screen","Someone clicked the weather button");
        getWeather();
    }
    public void getWeather()
    {
        Log.i("Companion","Making HTTP Call for Weather");
        RestClient.get().getWeather("California", new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                // success!

                Log.i("App", weatherResponse.getBase());
                Log.i("App", weatherResponse.getWeather()[0].getMain());
                Log.i("App", weatherResponse.getWeather()[0].getDescription());
                // you get the point...
            }

            @Override
            public void failure(RetrofitError error) {
                // something went wrong
                Log.e("Companion",error.getMessage());
            }
        });
    }

}

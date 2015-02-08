package com.libarguys.companion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;

import com.libarguys.companion.model.WeatherResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                //Log.i("App", weatherResponse.getMain().getMain());
                //Log.i("App", weatherResponse.getMain().getDescription());
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

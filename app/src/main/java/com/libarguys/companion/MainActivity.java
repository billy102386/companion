package com.libarguys.companion;



import com.libarguys.companion.model.Greeting;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import com.libarguys.companion.model.WeatherResponse;
import java.io.FileOutputStream;
import java.util.Locale;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {


    TextToSpeech tts;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Greeting g = new Greeting();
        EditText output = (EditText)findViewById(R.id.txtOutput);

        output.setText(g.getGreeting());
        WriteFile();

        tts=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        //ConvertTextToSpeech();
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });

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
        // getting GPS status
        LocationServices locServices = new LocationServices(this);
       Double lat = 0.0;
        Double lon = 0.0;
        lat = locServices.getLatitude();
        lon = locServices.getLongitude();
        Log.i("Companion","Making HTTP Call for Weather");
        RestClient.get().getWeather(lat,lon,"imperial", new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                // success!

                Log.i("App", weatherResponse.getBase());
                Log.i("App", weatherResponse.getWeather()[0].getMain());
                Log.i("App", weatherResponse.getWeather()[0].getDescription());
                Log.i("App", String.valueOf(weatherResponse.getMain().getTemp()));
                ConvertTextToSpeech("The weather is going to be "+weatherResponse.getWeather().get(0).getDescription());

                // Log.i("App", weatherResponse.getMain().getDescription());
                // you get the point...
            }

            @Override
            public void failure(RetrofitError error) {
                // something went wrong
                Log.e("Companion",error.getMessage());
            }
        });
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }


    private void ConvertTextToSpeech(String message) {
        // TODO Auto-generated method stub
        if(message==null||"".equals(message))
        {
            message = "Content not available";
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(message+"", TextToSpeech.QUEUE_FLUSH, null);
    }



}

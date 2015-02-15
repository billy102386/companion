package com.libarguys.companion;



import com.libarguys.companion.model.Greeting;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import com.libarguys.companion.model.WeatherResponse;
import com.libarguys.companion.view.CalendarView;
import com.libarguys.companion.view.CountdownView;
import com.libarguys.companion.view.GreetingView;
import com.libarguys.companion.view.WeatherView;

import java.io.FileOutputStream;
import java.net.URL;
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


        EditText output = (EditText) findViewById(R.id.txtOutput);

        output.addTextChangedListener(new TextWatcher() {

                                          public void afterTextChanged(Editable t)
                                          {
                                              if (!t.equals("")) {

                                                  ConvertTextToSpeech(t.toString());
                                              }
                                          }

                                          public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
                                          {}



            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            }
        );

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

    public void checkWeather(View view) {

        //ConvertTextToSpeech(MessageFactory.getFactory().getMessages());

        new MessageTask().execute();

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if (tts != null) {

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }


    private void ConvertTextToSpeech(String message) {
        // TODO Auto-generated method stub

        if(tts == null)
        {
            tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    // TODO Auto-generated method stub
                    if (status == TextToSpeech.SUCCESS) {
                        int result = tts.setLanguage(Locale.US);
                        if (result == TextToSpeech.LANG_MISSING_DATA ||
                                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e("error", "This Language is not supported");
                        } else {
                            //ConvertTextToSpeech();



                        }
                    } else
                        Log.e("error", "Initilization Failed!");
                }
            });
        }
        if (message == null || "".equals(message)) {
            message = "Content not available";
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        } else {
            tts.speak(message + "", TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    public class MessageTask extends AsyncTask<URL, Integer, String> {

        @Override
        protected String doInBackground(URL... urls) {

            String sMessage = "";
            Context c = getApplicationContext();


            GreetingView gv = new GreetingView();
            sMessage += gv.getMessage();


            WeatherView wv = new WeatherView(c);

            sMessage += wv.getMessage();

            CountdownView cdv = new CountdownView();

            sMessage += cdv.getMessage();


            CalendarView cv = new CalendarView(c);
            sMessage += " ";
            sMessage += cv.getMessage();


            return sMessage;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            return;
        }


        @Override
        protected void onPostExecute(String result) {


            EditText output = (EditText) findViewById(R.id.txtOutput);

            output.setText(result);


        }

    }
}

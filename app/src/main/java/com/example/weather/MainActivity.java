package com.example.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView cityName;
    Button search;
    TextView show;
    String url;

    static class GetWeather extends AsyncTask<String, Void, String> {

        private TextView show;

        public GetWeather(TextView show) {
            this.show = show;
        }

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);

                    // Extract main weather information
                    JSONObject mainObject = jsonObject.getJSONObject("main");

                    // Get temperature in Kelvin
                    double tempKelvin = mainObject.getDouble("temp");
                    double tempKelvinFeelCelsius = mainObject.getDouble("feels_like");
                    double tempKelvinMaxKelvin = mainObject.getDouble("temp_max");
                    double tempKelvinMinKelvin = mainObject.getDouble("temp_min");

                    // Convert temperature from Kelvin to Celsius
                    double tempCelsius = tempKelvin - 273.15;
                    double tempFeelCelsius = tempKelvinFeelCelsius - 273.15;
                    double tempMaxKelvin = tempKelvinMaxKelvin - 273.15;
                    double tempMinKelvin = tempKelvinMinKelvin - 273.15;

                    // Update weatherInfo string with Celsius temperature
                    String weatherInfo = "Temperature : " + String.format("%.2f°C", tempCelsius);
                     weatherInfo += "\nFeel Like : " + String.format("%.2f°C", tempFeelCelsius);
                     weatherInfo += "\nMax Temp : " + String.format("%.2f°C", tempMaxKelvin);
                     weatherInfo += "\nMin Temp : " + String.format("%.2f°C", tempMinKelvin);


                    weatherInfo += "\nPressure : " + mainObject.getInt("pressure");
                    weatherInfo += "\nHumidity : " + mainObject.getInt("humidity");
                    // Display the updated weather information
                    show.setText(weatherInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                show.setText("Cannot able to find Weather");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.city_name_input);
        search = findViewById(R.id.btn_enter);
        show = findViewById(R.id.weather_output);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityName.getText().toString().trim();
                if (!city.isEmpty()) {
                    url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=5973ca1f898a0e27e767d5933ccbae71";
                    GetWeather task = new GetWeather(show);
                    task.execute(url);
                } else {
                    Toast.makeText(MainActivity.this, "Enter City Name Please, Bitch!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

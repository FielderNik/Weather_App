package com.example.weatherapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.weatherapp.model.Weather
import com.example.weatherapp.service.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), LocationListener {

    val LOG_TAG = "myTag"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherService = retrofit.create(WeatherService::class.java)

    lateinit var tvText: TextView
    lateinit var coord: TextView

    lateinit var longitude: String
    lateinit var latitude: String

    lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        tvText = findViewById(R.id.tvText)



    }

    fun clickGetWeather(view: View){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                (this as Activity?)!!,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
            return
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0f, this)
        val loc =     locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        longitude = loc?.longitude.toString()
        latitude = loc?.latitude.toString()

/*        coord = findViewById(R.id.coord)
        coord.text = "LONG: $longitude -- LAT: $latitude"*/

        showWeather(longitude, latitude)


    }


    fun showWeather(longitude: String, latitude: String){

        tvText = findViewById(R.id.tvText)

        val weatherVar: Call<Weather> = weatherService.getWeather(longitude, latitude)

        weatherVar.enqueue(object : Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
//                Log.d(LOG_TAG, "OK")
                val temp = response.body()?.main?.temp?.toInt().toString()
                tvText.text = temp

                coord = findViewById(R.id.coord)
                coord.text = response.body()?.name
//
//                Log.d(LOG_TAG, "temp = $temp")

            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Connection failed", Toast.LENGTH_SHORT).show()
//                Log.d(LOG_TAG, "fail: $t")
            }
        }
        )
    }

    override fun onLocationChanged(location: Location) {

    }
}
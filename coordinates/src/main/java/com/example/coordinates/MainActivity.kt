package com.example.coordinates

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(), LocationListener {
    val LOG_TAG = "milk"
    lateinit var btn: Button
    lateinit var tvText: TextView

    lateinit var locationManager: LocationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        Log.d(LOG_TAG, "locationManager: $locationManager")*/

/*       if (ActivityCompat.checkSelfPermission(
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
        }*/


//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0f, this)

/*        val loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val long = loc?.longitude
        val lat = loc?.latitude

        Log.d(LOG_TAG, "long: $long ---- lat: $lat")*/

    }


    override fun onLocationChanged(location: Location) {
//        val lat = location.latitude
//        val lon = location.longitude
//        Log.d(LOG_TAG, "latitude: $lat | longitude: $lon")
    }

    fun getloc(view: View){
        Log.d(LOG_TAG, "btn Click")
        tvText = findViewById(R.id.textView)

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
        val long = loc?.longitude
        val lat = loc?.latitude

        val newText = "LONG: $long ---- LAT: $lat"

        tvText.text = newText
    }
}
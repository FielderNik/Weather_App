package com.example.coordinates

import android.location.Location
import android.location.LocationListener
import android.util.Log

class MyLocationListener: LocationListener {
    override fun onLocationChanged(location: Location) {
        val lon = location.longitude
        val lat = location.latitude

//        UseGps.this.mlocManager.removeUpdates(this);

        Log.d("milk", "lon: $lon | lat: $lat")

    }
}
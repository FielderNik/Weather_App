package com.example.weatherapp.service

import com.example.weatherapp.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.openweathermap.org/data/2.5/weather?q=moscow&units=metric&appid=cc41909cd0d5c2ee0ad33bec80888a10
interface WeatherService {
    @GET("/data/2.5/weather?&units=metric&appid=cc41909cd0d5c2ee0ad33bec80888a10")
    fun getWeather(
        @Query("lon")
        longitude: String,

        @Query("lat")
        latitude: String
    ) : Call<Weather>
}
package com.example.openweather.domain.network

import com.example.openweather.BuildConfig
import com.example.openweather.domain.network.model.OpenWeatherMapResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherMapApi {
    @GET("/data/2.5/weather")
    suspend fun getWeather(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_KEY
    ): OpenWeatherMapResponse
}
package com.example.openweather.domain

data class Weather(
    val cityName: String,
    val temperature:Double,
    val windSpeed:Double,
    val humidity:Int,
    val visibility:Int,
    val timeOfSunrise:Long,
    val timeOfSunset:Long)
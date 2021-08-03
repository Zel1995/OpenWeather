package com.example.openweather.domain.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherEntity")
data class WeatherEntity(
    @PrimaryKey
    val id: String,
    val cityName: String,
    val temperature: Double,
    val windSpeed: Double,
    val humidity: Int,
    val visibility: Int,
    val timeOfSunrise: Long,
    val timeOfSunset: Long
)
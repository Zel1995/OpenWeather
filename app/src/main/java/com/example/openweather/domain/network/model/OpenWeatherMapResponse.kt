package com.example.openweather.domain.network.model

import com.google.gson.annotations.SerializedName


data class OpenWeatherMapResponse(
    @SerializedName("weather")
    val weather: List<OpenWeather>,
    @SerializedName("main")
    val main: Main,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("name")
    val name: String,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("sys")
    val sys: Sys
)
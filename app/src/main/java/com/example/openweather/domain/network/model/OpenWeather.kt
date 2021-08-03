package com.example.openweather.domain.network.model

import com.google.gson.annotations.SerializedName

data class OpenWeather(
    @SerializedName("desctiprion")
    val description: String
)
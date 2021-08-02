package com.example.openweather.domain.network.model

import com.google.gson.annotations.SerializedName

data class Main (
    @SerializedName("temp")
    val temp:Double,
    @SerializedName("feels_like")
    val feelsLike:Double,
    @SerializedName("pressure")
    val pressure:Int,
    @SerializedName("humidity")
    val humidity:Int
)
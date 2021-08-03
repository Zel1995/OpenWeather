package com.example.openweather.domain.network.model

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
)
package com.example.openweather.domain.Repository

import com.example.openweather.domain.Weather

interface Repository {
    suspend fun getWeather(zipCode:String):RepositoryResult<Weather>
}
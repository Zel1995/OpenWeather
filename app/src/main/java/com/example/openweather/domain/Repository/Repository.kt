package com.example.openweather.domain.Repository

import com.example.openweather.domain.Weather
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getWeather(zipCode:String): Flow<RepositoryResult<Weather>>
}
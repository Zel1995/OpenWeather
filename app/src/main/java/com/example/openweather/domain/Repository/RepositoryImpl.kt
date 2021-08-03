package com.example.openweather.domain.Repository

import com.example.openweather.domain.Weather
import com.example.openweather.domain.network.OpenWeatherMapApi
import com.example.openweather.domain.storage.WeatherDao
import com.example.openweather.domain.storage.WeatherEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val openWeatherMapApi: OpenWeatherMapApi,
    private val weatherDao: WeatherDao
) : Repository {
    companion object {
        const val LAST_REQUEST = "LAST_REQUEST"
    }

    override suspend fun getWeather(zipCode: String): Flow<RepositoryResult<Weather>> = flow {
        val cachedWeatherInfo = weatherDao.weatherForZipCode(zipCode)
        cachedWeatherInfo?.let {
            val weather = Weather(
                it.cityName,
                it.temperature,
                it.windSpeed,
                it.humidity,
                it.visibility,
                it.timeOfSunrise,
                it.timeOfSunset
            )
            emit(Success(weather))
        }
        try {
            val response = openWeatherMapApi.getWeather(zipCode)
            val weatherEntity = WeatherEntity(
                zipCode,
                response.name,
                response.main.temp,
                response.wind.speed,
                response.main.humidity,
                response.visibility,
                response.sys.sunrise,
                response.sys.sunset
            )
            weatherDao.add(weatherEntity)
            weatherDao.add(weatherEntity.copy(id = LAST_REQUEST))
            with(response) {
                val weather = Weather(
                    name,
                    main.temp,
                    wind.speed,
                    main.humidity,
                    visibility,
                    sys.sunrise,
                    sys.sunset
                )
                emit(Success(weather))
            }
        } catch (exc: Exception) {
            if (cachedWeatherInfo == null) {
                emit(Error(exc))
            }
        }
    }
}

sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val value: Throwable) : RepositoryResult<T>()
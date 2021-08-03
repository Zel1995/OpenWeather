package com.example.openweather.domain.Repository

import com.example.openweather.domain.Weather
import com.example.openweather.domain.network.OpenWeatherMapApi
import java.lang.Exception

class RepositoryImpl(private val openWeatherMapApi: OpenWeatherMapApi):Repository {
    override suspend fun getWeather(zipCode:String):RepositoryResult<Weather> {
        try {
            val response = openWeatherMapApi.getWeather(zipCode)
            with(response){
                val weather = Weather(
                    name,
                    main.temp,
                    wind.speed,
                    main.humidity,
                    visibility,
                    sys.sunrise.toString(),
                    sys.sunset.toString()
                )
                return Success(weather)
            }
        }catch (exc:Exception){
            return Error(exc)
        }
    }
}
sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val value: Throwable) : RepositoryResult<T>()
package com.example.openweather.di

import android.app.Application
import androidx.room.Room
import com.example.openweather.domain.Repository.Repository
import com.example.openweather.domain.Repository.RepositoryImpl
import com.example.openweather.domain.network.OpenWeatherMapApi
import com.example.openweather.domain.storage.WeatherDao
import com.example.openweather.domain.storage.WeatherDataBase
import com.example.openweather.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org"

val repositoryModule = module {
    factory <Repository>{ RepositoryImpl(get(),get()) }
    factory { provideRetrofit() }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}
val dataBaseModule = module {
    single { provideWeatherDao(androidApplication()) }
}

fun provideRetrofit(): OpenWeatherMapApi {
    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit.create(OpenWeatherMapApi::class.java)
}
fun provideWeatherDao(application: Application): WeatherDao {
    return Room.databaseBuilder(application, WeatherDataBase::class.java,"WeatherDataBase").build().weatherDao()
}

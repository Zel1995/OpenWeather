package com.example.openweather.di

import com.example.openweather.domain.Repository.Repository
import com.example.openweather.domain.Repository.RepositoryImpl
import com.example.openweather.domain.network.OpenWeatherMapApi
import com.example.openweather.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org"

val repositoryModule = module {
    single <Repository>{ RepositoryImpl(get()) }
    single { provideRetrofit() }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

fun provideRetrofit(): OpenWeatherMapApi {
    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit.create(OpenWeatherMapApi::class.java)
}

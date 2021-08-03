package com.example.openweather.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweather.domain.Repository.Repository
import com.example.openweather.domain.Repository.Success
import com.example.openweather.domain.Weather
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*


class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _weatherLiveData = MutableLiveData<Weather?>()
    private val _errorLiveData = MutableLiveData<String>()

    val weatherLiveData = _weatherLiveData
    val errorLiveData = _errorLiveData

    fun fetchWeather(zipCode: String) {
        viewModelScope.launch {
            repository.getWeather(zipCode).collect {
                when(it) {
                    is Success -> _weatherLiveData.value = it.value
                    is Error -> _errorLiveData.value = it.printStackTrace().toString()
                }
            }
        }
    }

}
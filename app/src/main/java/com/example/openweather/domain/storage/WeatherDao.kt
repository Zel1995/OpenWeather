package com.example.openweather.domain.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherEntity WHERE id = :id")
    suspend fun weatherForZipCode(id: String): WeatherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(entity: WeatherEntity)
}
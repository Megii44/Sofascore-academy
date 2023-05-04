package com.example.homework_5.database

import androidx.room.*
import com.example.homework_5.model.CurrentLocationWeather
import com.example.homework_5.model.CurrentLocationWeatherResponse

@Dao
interface CityWeatherDao {

    @Query("SELECT * FROM city_weather")
    suspend fun getAll(): List<CurrentLocationWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityWeather(cityWeather: CurrentLocationWeatherResponse)

    @Delete
    suspend fun deleteCityWeather()
}
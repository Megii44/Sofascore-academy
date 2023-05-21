package com.example.homework_5.networking

import com.example.homework_5.model.CurrentLocationWeatherResponse
import com.example.homework_5.model.ForecastResponse
import com.example.homework_5.model.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("search.json")
    suspend fun searchLocations(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Response<List<Location>>

    @GET("current.json")
    suspend fun getCurrentWeatherForLocation(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Response<CurrentLocationWeatherResponse>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") locationName: String,
        @Query("days") days: Int
    ): Response<ForecastResponse>
}
package com.example.homework_5.networking

import com.example.homework_5.model.AllCitiesResponse
import retrofit2.http.GET

interface WeatherApiService {

    @GET("cities")
    suspend fun getAllCities() : AllCitiesResponse
}
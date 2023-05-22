package com.example.minisofascore.network

import com.example.minisofascore.data.models.EventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MiniSofascoreApiService {
    @GET("application/json")
    suspend fun getEventsForDay(
        @Query("q") query: String
    ): Response<List<EventResponse>>

}
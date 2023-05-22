package com.example.minisofascore.network

import com.example.minisofascore.data.models.EventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MiniSofascoreApiService {

    @GET("sport/{sport}/events/{date}")
    suspend fun getEventsForDay(
        @Path("sport") sport: String,
        @Path("date") date: String
    ): Response<List<EventResponse>>

}
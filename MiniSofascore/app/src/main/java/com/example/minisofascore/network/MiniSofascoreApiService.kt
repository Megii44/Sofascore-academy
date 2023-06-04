package com.example.minisofascore.network

import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.data.models.Tournament
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MiniSofascoreApiService {

    @GET("sport/{sport}/events/{date}")
    suspend fun getEventsForDay(
        @Path("sport") sport: String,
        @Path("date") date: String
    ): Response<List<EventResponse>>

    @GET("team/{id}/image")
    suspend fun getTeamLogo(
        @Path("id") teamId: Int,
    ): Response<String>

    @GET("sport/{slug}/tournaments")
    suspend fun getLeagues(
        @Path("slug") sport: String,
    ): Response<List<Tournament>>

}
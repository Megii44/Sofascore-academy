package com.example.minisofascore.network

import com.example.minisofascore.data.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MiniSofascoreApiService {

    @GET("sport/{sport}/events/{date}")
    suspend fun getEventsForDay(
        @Path("sport") sport: String,
        @Path("date") date: String
    ): Response<List<Event>>

    @GET("team/{id}/image")
    suspend fun getTeamLogo(
        @Path("id") teamId: Int,
    ): Response<String>

    @GET("sport/{slug}/tournaments")
    suspend fun getLeagues(
        @Path("slug") sport: String,
    ): Response<List<Tournament>>

    @GET("tournament/{id}/image")
    suspend fun getTournamentLogo(
        @Path("id") tournamentId: Int,
    ): Response<String>

    @GET("event/{id}/incidents")
    suspend fun getIncidentsForEvent(
        @Path("id") eventId: Int,
    ): Response<List<Incident>>

    @GET("team/{id}")
    suspend fun getTeamDetails(
        @Path("id") teamId: Int,
    ): Response<Team>

    @GET("team/{id}/players")
    suspend fun getTeamPlayers(
        @Path("id") teamId: Int,
    ): Response<List<Player>>

    @GET("team/{id}/events/{span}/{page}")
    suspend fun getTeamEvents(
        @Path("id") teamId: Int,
        @Path("span") span: String,
        @Path("page") page: Int,
    ): Response<List<Event>>

    @GET("team/{id}/tournaments")
    suspend fun getTeamTournaments(
        @Path("id") teamId: Int,
    ): Response<List<Tournament>>
}
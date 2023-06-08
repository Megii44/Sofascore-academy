package com.example.minisofascore.data.repositories

import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.data.models.Player
import com.example.minisofascore.data.models.Team
import com.example.minisofascore.data.models.Tournament
import com.example.minisofascore.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRepository {
    private val networkService = Network().getService()

    suspend fun getTeamDetails(teamId: Int): Team = withContext(Dispatchers.IO) {
        val response = networkService.getTeamDetails(teamId)
        if (response.isSuccessful) {
            response.body() ?: throw Exception("Received empty response body")
        } else {
            throw Exception("Failed to fetch team details: ${response.message()}")
        }
    }

     suspend fun getTeamPlayers(teamId: Int): List<Player> = withContext(Dispatchers.IO) {
         val response = networkService.getTeamPlayers(teamId)
         if (response.isSuccessful) {
             response.body() ?: throw Exception("Received empty response body")
         } else {
             throw Exception("Failed to fetch team players: ${response.message()}")
         }
     }

    suspend fun getTeamEvents(teamId: Int, span: String, page: Int): List<EventResponse> = withContext(Dispatchers.IO) {
        val response = networkService.getTeamEvents(teamId, span, page)
        if (response.isSuccessful) {
            response.body() ?: throw Exception("Received empty response body")
        } else {
            throw Exception("Failed to fetch team events: ${response.message()}")
        }
    }

    suspend fun getTeamTournaments(teamId: Int): List<Tournament> = withContext(Dispatchers.IO) {
        val response = networkService.getTeamTournaments(teamId)
        if (response.isSuccessful) {
            response.body() ?: throw Exception("Received empty response body")
        } else {
            throw Exception("Failed to fetch team tournaments: ${response.message()}")
        }
    }
}

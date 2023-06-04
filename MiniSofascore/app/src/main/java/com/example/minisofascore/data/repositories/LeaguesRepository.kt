package com.example.minisofascore.data.repositories

import com.example.minisofascore.data.models.Tournament
import com.example.minisofascore.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeaguesRepository {
    private val networkService = Network().getService()

    suspend fun getLeagues(sport: String): List<Tournament> = withContext(Dispatchers.IO) {
        val response = networkService.getLeagues(sport)
        if (response.isSuccessful) {
            val fetchedTournaments = response.body() ?: emptyList()

            // Fetch tournament logos
            for (tournament in fetchedTournaments) {
                getTournamentLogo(tournament)
            }

            fetchedTournaments
        } else {
            throw Exception("Failed to fetch leagues: ${response.message()}")
        }
    }


    private suspend fun getTournamentLogo(tournament: Tournament) {
        try {
            val response = networkService.getTournamentLogo(tournament.id)
            if (response.isSuccessful) {
                response.body()?.let { logoUrl ->
                    tournament.logo = logoUrl
                }
            }
        } catch (e: Exception) {
            // Handle the exception here
        }
    }
}

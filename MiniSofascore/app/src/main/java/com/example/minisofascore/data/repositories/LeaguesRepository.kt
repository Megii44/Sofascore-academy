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
            response.body() ?: emptyList()
        } else {
            throw Exception("Failed to fetch leagues: ${response.message()}")
        }
    }
}

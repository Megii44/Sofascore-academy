package com.example.minisofascore.data.repositories

import com.example.minisofascore.data.models.Incident
import com.example.minisofascore.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventDetailsRepository {
    private val networkService = Network().getService()

    suspend fun getIncidents(eventId: Int): List<Incident> = withContext(Dispatchers.IO) {
        val response = networkService.getIncidentsForEvent(eventId)
        if (response.isSuccessful) {
            val fetchedIncidents = response.body() ?: emptyList()

            fetchedIncidents
        } else {
            throw Exception("Failed to fetch incidents for event: ${response.message()}")
        }
    }
}

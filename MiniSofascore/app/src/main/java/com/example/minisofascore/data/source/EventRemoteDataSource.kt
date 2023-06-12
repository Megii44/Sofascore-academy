package com.example.minisofascore.data.source

import android.util.Log
import com.example.minisofascore.data.models.Event
import com.example.minisofascore.network.MiniSofascoreApiService

class EventRemoteDataSource (
    private val apiService: MiniSofascoreApiService
) {
    suspend fun fetchEvents(teamId: Int, span: String, page: Int): List<Event> {
        val events: List<Event> = apiService.getTeamEvents(teamId, span, page).body() ?: emptyList()

        Log.d("EventRemoteDataSource", "Fetched events: $events")

        return events
    }
}

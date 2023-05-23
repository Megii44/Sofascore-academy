package com.example.minisofascore.ui.football

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.data.models.Team
import com.example.minisofascore.network.Network
import kotlinx.coroutines.*

class FootballViewModel : ViewModel() {
    private val _events = MutableLiveData<List<EventResponse>>()
    val events: LiveData<List<EventResponse>> get() = _events

    fun getEvents(sport: String, date: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = Network().getService().getEventsForDay(sport, date)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    // For each event
                    for (event in body) {
                        // Get the logo for each team
                        coroutineScope {
                            val homeLogoDeferred = async { getTeamLogo(event.homeTeam) }
                            val awayLogoDeferred = async { getTeamLogo(event.awayTeam) }

                            // Wait for the logo fetching to complete
                            homeLogoDeferred.await()
                            awayLogoDeferred.await()
                        }
                    }
                    // Post the updated events
                    _events.postValue(body)
                }
            }
        }
    }

    private suspend fun getTeamLogo(team: Team) {
        try {
            val response = Network().getService().getTeamLogo(team.id)
            if (response.isSuccessful) {
                response.body()?.let { logoUrl ->
                    team.logo = logoUrl
                }
            }
        } catch (e: Exception) {
            // Handle the exception here
        }
    }


}

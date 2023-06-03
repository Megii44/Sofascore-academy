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

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val network = Network()

    fun getEvents(sport: String, date: String) {
        _loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val response = network.getService().getEventsForDay(sport, date)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    // Immediately post the events
                    _events.postValue(body)
                    // For each event, asynchronously fetch logos
                    for (event in body) {
                        launch {
                            getTeamLogo(event.homeTeam)
                            getTeamLogo(event.awayTeam)
                            // After each logo is fetched, post the updated events
                            _events.postValue(body)
                        }
                    }
                }
            }
            _loading.postValue(false)
        }
    }

    private suspend fun getTeamLogo(team: Team) {
        try {
            val response = network.getService().getTeamLogo(team.id)
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


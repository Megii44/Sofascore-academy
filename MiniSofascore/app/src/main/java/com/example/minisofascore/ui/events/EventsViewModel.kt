package com.example.minisofascore.ui.events

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minisofascore.data.enums.SportEnum
import com.example.minisofascore.data.models.Event
import com.example.minisofascore.data.models.Team
import com.example.minisofascore.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class EventsViewModel : ViewModel() {
    private val _eventsGroupedByTournament = MutableLiveData<List<Pair<String, List<Event>>>>()
    val eventsGroupedByTournament: LiveData<List<Pair<String, List<Event>>>> get() = _eventsGroupedByTournament

    private val _selectedSport = MutableLiveData<Int>()
    val selectedSport: LiveData<Int> = _selectedSport

    private val _selectedDay = MutableLiveData<LocalDate>()
    val selectedDay: LiveData<LocalDate> = _selectedDay

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val network = Network()

    fun selectSport(sport: Int) {
        _selectedSport.value = sport
    }

    fun selectDay(day: LocalDate) {
        _selectedDay.value = day
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateDateAndSport(date: LocalDate, sport: SportEnum) {
        _selectedDay.value = date
        _selectedSport.value = sport.ordinal
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getEvents(sport: String?, date: String?) {
        _loading.value = true

        var selectedSport = selectedSport.value?.let { SportEnum.values()[it].toString() } ?: sport ?: SportEnum.Football.toString()
        val selectedDate = selectedDay.value?.toString() ?: date ?: LocalDate.now().toString()

        selectedSport = selectedSport.lowercase()

        CoroutineScope(Dispatchers.IO).launch {
            val response = network.getService().getEventsForDay(selectedSport, selectedDate)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    // Group events by tournament
                    val groupedEvents = body.groupBy { it.tournament.name }.map { it.key to it.value }
                    // Immediately post the grouped events
                    _eventsGroupedByTournament.postValue(groupedEvents)
                    // For each event, asynchronously fetch logos
                    for (event in body) {
                        launch {
                            getTeamLogo(event.homeTeam)
                            getTeamLogo(event.awayTeam)
                            // After each logo is fetched, post the updated grouped events
                            _eventsGroupedByTournament.postValue(groupedEvents)
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

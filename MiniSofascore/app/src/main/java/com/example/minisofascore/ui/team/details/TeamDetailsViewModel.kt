package com.example.minisofascore.ui.team.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.data.models.Player
import com.example.minisofascore.data.models.Tournament
import com.example.minisofascore.data.repositories.TeamRepository
import kotlinx.coroutines.launch

class TeamDetailsViewModel(private val teamRepository: TeamRepository) : ViewModel() {
    private val _teamPlayers = MutableLiveData<List<Player>>()
    val teamPlayers: LiveData<List<Player>> get() = _teamPlayers

    private val _teamTournaments = MutableLiveData<List<Tournament>>()
    val teamTournaments: LiveData<List<Tournament>> get() = _teamTournaments

    private val _teamEvents = MutableLiveData<List<EventResponse>>()
    val teamEvents: LiveData<List<EventResponse>> get() = _teamEvents

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun fetchTeamPlayers(teamId: Int) {
        viewModelScope.launch {
            try {
                Log.d("TeamDetailsViewModel", "Fetching players for team $teamId")
                val fetchedPlayers = teamRepository.getTeamPlayers(teamId)
                Log.d("TeamDetailsViewModel", "Fetched ${fetchedPlayers.size} players")
                _teamPlayers.value = fetchedPlayers
            } catch (e: Exception) {
                // Handle or report the error
                Log.e("TeamDetailsViewModel", "Error fetching leagues", e)
            }
        }
    }

    fun fetchTeamTournaments(teamId: Int) {
        viewModelScope.launch {
            try {
                Log.d("TeamDetailsViewModel", "Fetching players for team $teamId")
                val fetchedTournaments = teamRepository.getTeamTournaments(teamId)
                Log.d("TeamDetailsViewModel", "Fetched ${fetchedTournaments.size} tournaments")
                _teamTournaments.value = fetchedTournaments
            } catch (e: Exception) {
                // Handle or report the error
                Log.e("TeamDetailsViewModel", "Error fetching leagues", e)
            }
        }
    }

    fun fetchNextMatch(teamId: Int) {
        viewModelScope.launch {
            try {
                Log.d("TeamDetailsViewModel", "Fetching next match for team $teamId")
                val fetchedMatches = teamRepository.getTeamEvents(teamId, "next", 0)
                Log.d("TeamDetailsViewModel", "Fetched ${fetchedMatches.size} matches")
                _teamEvents.value = fetchedMatches
            } catch (e: Exception) {
                // Handle or report the error
                Log.e("TeamDetailsViewModel", "Error fetching matches", e)
            }
        }
    }
}

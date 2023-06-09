package com.example.minisofascore.ui.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minisofascore.data.models.*
import com.example.minisofascore.data.repositories.CountryRepository
import com.example.minisofascore.data.repositories.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel(
    private val teamRepository: TeamRepository,
    private val countryRepository: CountryRepository
) : ViewModel() {
    private val _teamDetails = MutableLiveData<Team>()
    val teamDetails: LiveData<Team> get() = _teamDetails

    private val _teamPlayers = MutableLiveData<List<Player>>()
    val teamPlayers: LiveData<List<Player>> get() = _teamPlayers

    private val _teamTournaments = MutableLiveData<List<Tournament>>()
    val teamTournaments: LiveData<List<Tournament>> get() = _teamTournaments

    private val _teamEvents = MutableLiveData<List<EventResponse>>()
    val teamEvents: LiveData<List<EventResponse>> get() = _teamEvents

    private val _countryFlag = MutableLiveData<CountryInfo>()
    val countryFlag: LiveData<CountryInfo> get() = _countryFlag

    fun fetchTeamDetails(teamId: Int) {
        viewModelScope.launch {
            try {
                Log.d("TeamDetailsViewModel", "Fetching details for team $teamId")
                val team = teamRepository.getTeamDetails(teamId)
                _teamDetails.value = team
            } catch (e: Exception) {
                // Handle or report the error
                Log.e("TeamDetailsViewModel", "Error fetching team details", e)
            }
        }
    }

    fun fetchTeamPlayers(teamId: Int) {
        viewModelScope.launch {
            try {
                Log.d("TeamDetailsViewModel", "Fetching players for team $teamId")
                val fetchedPlayers = teamRepository.getTeamPlayers(teamId)
                Log.d("TeamDetailsViewModel", "Fetched ${fetchedPlayers.size} players")
                _teamPlayers.value = fetchedPlayers
            } catch (e: Exception) {
                // Handle or report the error
                Log.e("TeamDetailsViewModel", "Error fetching team players", e)
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
                Log.e("TeamDetailsViewModel", "Error fetching tournaments", e)
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

    fun fetchCountryFlag(countryName: String) {
        viewModelScope.launch {
            try {
                Log.d("TeamDetailsViewModel", "Fetching flag for country $countryName")
                val countryList = countryRepository.fetchCountryFlag(countryName)
                _countryFlag.value = countryList[0]
            } catch (e: Exception) {
                // Handle or report the error
                Log.e("TeamDetailsViewModel", "Error fetching country flag", e)
            }
        }
    }
}

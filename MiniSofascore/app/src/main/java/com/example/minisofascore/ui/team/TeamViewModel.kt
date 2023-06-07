package com.example.minisofascore.ui.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minisofascore.data.models.Tournament
import com.example.minisofascore.data.repositories.LeaguesRepository
import kotlinx.coroutines.launch

class TeamViewModel(private val leaguesRepository: LeaguesRepository) : ViewModel() {
    private val _leagues = MutableLiveData<List<Tournament>>()
    val leagues: LiveData<List<Tournament>> get() = _leagues

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _selectedSport = MutableLiveData<Int>()
    val selectedSport: LiveData<Int> = _selectedSport

    fun selectSport(sport: Int) {
        _selectedSport.value = sport
    }

    fun fetchLeagues(sport: String) {
        viewModelScope.launch {
            try {
                Log.d("LeaguesViewModel", "Fetching leagues for $sport")
                val fetchedLeagues = leaguesRepository.getLeagues(sport)
                Log.d("LeaguesViewModel", "Fetched ${fetchedLeagues.size} leagues")
                _leagues.value = fetchedLeagues
            } catch (e: Exception) {
                // Handle or report the error
                Log.e("LeaguesViewModel", "Error fetching leagues", e)
            }
        }
    }
}
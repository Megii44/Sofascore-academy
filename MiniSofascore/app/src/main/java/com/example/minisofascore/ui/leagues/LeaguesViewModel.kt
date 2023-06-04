package com.example.minisofascore.ui.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minisofascore.data.models.Tournament
import com.example.minisofascore.data.repositories.LeaguesRepository
import kotlinx.coroutines.launch

class LeaguesViewModel(private val leaguesRepository: LeaguesRepository) : ViewModel() {
    private val _leagues = MutableLiveData<List<Tournament>>()
    val leagues: LiveData<List<Tournament>> get() = _leagues

    init {
        fetchLeagues()
    }

    private fun fetchLeagues() {
        viewModelScope.launch {
            try {
                val fetchedLeagues = leaguesRepository.getLeagues()
                _leagues.value = fetchedLeagues
            } catch (e: Exception) {
                // Handle or report the error
            }
        }
    }
}

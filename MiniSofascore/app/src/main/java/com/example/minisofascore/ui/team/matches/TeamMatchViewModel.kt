package com.example.minisofascore.ui.team.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.minisofascore.data.repositories.MatchRepository
import kotlinx.coroutines.launch

class TeamMatchViewModel (
    private val eventRepository: MatchRepository
) : ViewModel() {

    fun getEvents(teamId: Int) =
        eventRepository.getEvents(teamId).cachedIn(viewModelScope).asLiveData()

    fun refreshEvents(teamId: Int, span: String, page: Int) {
        viewModelScope.launch {
            eventRepository.refreshEvents(teamId, span, page)
        }
    }
}

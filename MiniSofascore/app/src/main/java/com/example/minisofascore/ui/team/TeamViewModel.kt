package com.example.minisofascore.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minisofascore.data.models.Tournament
import com.example.minisofascore.data.repositories.TeamRepository

class TeamViewModel(private val leaguesRepository: TeamRepository) : ViewModel() {
    private val _leagues = MutableLiveData<List<Tournament>>()
    val leagues: LiveData<List<Tournament>> get() = _leagues

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

}

package com.example.minisofascore.data.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.data.repositories.CountryRepository
import com.example.minisofascore.data.repositories.MatchRepository
import com.example.minisofascore.data.repositories.TeamRepository
import com.example.minisofascore.ui.team.matches.TeamMatchViewModel

class MatchViewModelFactory(
    private val teamRepository: MatchRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<@JvmSuppressWildcards T>): T {
        return when {
            modelClass.isAssignableFrom(TeamMatchViewModel::class.java) -> {
                TeamMatchViewModel(teamRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

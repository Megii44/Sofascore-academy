package com.example.minisofascore.data.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.data.repositories.MatchRepository
import com.example.minisofascore.ui.team.matches.TeamMatchViewModel

class MatchViewModelFactory(
    private val teamMatchRepository: MatchRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<@JvmSuppressWildcards T>): T {
        return when {
            modelClass.isAssignableFrom(TeamMatchViewModel::class.java) -> {
                TeamMatchViewModel(teamMatchRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

package com.example.minisofascore.ui.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.data.repositories.CountryRepository
import com.example.minisofascore.data.repositories.TeamRepository
import com.example.minisofascore.ui.team.details.TeamDetailsViewModel

class TeamViewModelFactory(
    private val teamRepository: TeamRepository,
    private val countryRepository: CountryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<@JvmSuppressWildcards T>): T {
        return when {
            modelClass.isAssignableFrom(TeamDetailsViewModel::class.java) -> {
                TeamDetailsViewModel(teamRepository, countryRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

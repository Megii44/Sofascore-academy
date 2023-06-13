package com.example.minisofascore.data.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.data.repositories.CountryRepository
import com.example.minisofascore.data.repositories.TeamRepository
import com.example.minisofascore.ui.team.TeamViewModel

class TeamViewModelFactory(
    private val teamRepository: TeamRepository,
    private val countryRepository: CountryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<@JvmSuppressWildcards T>): T {
        return when {
            modelClass.isAssignableFrom(TeamViewModel::class.java) -> {
                TeamViewModel(teamRepository, countryRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

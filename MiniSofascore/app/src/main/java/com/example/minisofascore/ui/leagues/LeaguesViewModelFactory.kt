package com.example.minisofascore.ui.leagues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.data.repositories.LeaguesRepository

class LeaguesViewModelFactory(private val leaguesRepository: LeaguesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<@JvmSuppressWildcards T>): T {
        return when {
            modelClass.isAssignableFrom(LeaguesViewModel::class.java) -> {
                LeaguesViewModel(leaguesRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

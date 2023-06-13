package com.example.minisofascore.ui.event
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.data.repositories.EventDetailsRepository

class EventDetailsViewModelFactory(private val eventDetailsRepository: EventDetailsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<@JvmSuppressWildcards T>): T {
        return when {
            modelClass.isAssignableFrom(EventDetailsViewModel::class.java) -> {
                EventDetailsViewModel(eventDetailsRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

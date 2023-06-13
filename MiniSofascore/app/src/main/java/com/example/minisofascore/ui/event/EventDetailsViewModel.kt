package com.example.minisofascore.ui.event

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minisofascore.data.models.Incident
import com.example.minisofascore.data.repositories.EventDetailsRepository
import kotlinx.coroutines.launch

class EventDetailsViewModel(private val eventDetailsRepository: EventDetailsRepository) : ViewModel() {
    private val _incidents = MutableLiveData<List<Incident>>()
    val incidents: LiveData<List<Incident>> get() = _incidents

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun fetchIncidents(eventId: Int) {
        viewModelScope.launch {
            try {
                Log.d("EventDetailsViewModel", "Fetching incidents for event with id $eventId")
                val fetchedIncidents = eventDetailsRepository.getIncidents(eventId)
                Log.d("EventDetailsViewModel", "Fetched ${fetchedIncidents.size} incidents")
                _incidents.value = fetchedIncidents
            } catch (e: Exception) {
                // Handle or report the error
                Log.e("EventDetailsViewModel", "Error fetching incidents", e)
            }
        }
    }
}

package com.example.minisofascore.ui.football

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FootballViewModel : ViewModel() {
    private val _events = MutableLiveData<List<EventResponse>>()
    val events: LiveData<List<EventResponse>> get() = _events

    fun getEvents(sport: String, date: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = Network().getService().getEventsForDay(sport, date)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    _events.postValue(body)
                }
            }
        }
    }
}

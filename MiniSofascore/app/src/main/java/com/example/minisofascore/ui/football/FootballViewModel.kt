package com.example.minisofascore.ui.football

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FootballViewModel : ViewModel() {
    private val events = MutableLiveData<List<EventResponse>>()

    fun getEvents(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = Network().getService().getEventsForDay(query)
            if (response.isSuccessful) {
                events.postValue(response.body())
            }
        }
    }
}

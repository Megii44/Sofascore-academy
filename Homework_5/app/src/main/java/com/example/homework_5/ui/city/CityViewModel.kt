package com.example.homework_5.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework_5.model.Forecast

class CityViewModel : ViewModel() {

    // LiveData for holding recent searches
    private val _dailyForecast = MutableLiveData<ArrayList<Forecast>>()
    val dailyForecast: LiveData<ArrayList<Forecast>> = _dailyForecast

    fun updateForecast(dailyForecast: Forecast?) {
        if(dailyForecast != null) {
            val updatedList = ArrayList<Forecast>()
            updatedList.add(dailyForecast)
            _dailyForecast.value = updatedList
        }
    }

}

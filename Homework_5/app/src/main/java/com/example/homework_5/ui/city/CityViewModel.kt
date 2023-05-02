package com.example.homework_5.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework_5.model.Forecast

class CityViewModel : ViewModel() {

    // LiveData for holding recent searches
    private val _forecast = MutableLiveData<ArrayList<Forecast>>()
    public val forecast: LiveData<ArrayList<Forecast>> = _forecast

    fun updateNextSevenDaysForecast(forecastData: Forecast?) {
        if(forecastData != null) {
            val updatedList = ArrayList<Forecast>()
            updatedList.add(forecastData)
            _forecast.value = updatedList
        }
    }
}

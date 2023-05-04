package com.example.homework_5.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework_5.model.TimeItem

class CityViewModel : ViewModel() {

    // LiveData for holding forecast data displayed in weather sequence recycler views
    // Today forecast sequence / Next seven days forecast sequence
    private val _todayForecast = MutableLiveData<ArrayList<TimeItem>>()
    public val todayForecast: LiveData<ArrayList<TimeItem>> = _todayForecast

    private val _nextSevenDaysForecast = MutableLiveData<ArrayList<TimeItem>>()
    public val nextSevenDaysForecast: LiveData<ArrayList<TimeItem>> = _nextSevenDaysForecast

    fun updateTodayForecast(todayForecast: List<TimeItem>) {
        if(todayForecast != null) {
            val updatedList = ArrayList<TimeItem>()
            updatedList.addAll(todayForecast)
            _todayForecast.value = updatedList
        }
    }
    fun updateNextSevenDaysForecast(nextSevenDaysForecast: List<TimeItem>) {
        if(nextSevenDaysForecast != null) {
            val updatedList = ArrayList<TimeItem>()
            updatedList.addAll(nextSevenDaysForecast)
            _nextSevenDaysForecast.value = updatedList
        }
    }
}

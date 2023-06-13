package com.example.minisofascore.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class MainViewModel : ViewModel() {
    private val _selectedSport = MutableLiveData<Int>()
    val selectedSport: LiveData<Int> = _selectedSport

    private val _selectedDay = MutableLiveData<LocalDate>()
    val selectedDay: LiveData<LocalDate> = _selectedDay

    fun selectSport(sport: Int) {
        _selectedSport.value = sport
    }

    fun selectDay(day: LocalDate) {
        _selectedDay.value = day
    }
}

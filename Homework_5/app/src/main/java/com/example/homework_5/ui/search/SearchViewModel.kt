package com.example.homework_5.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework_5.model.CurrentLocationWeather

class SearchViewModel : ViewModel() {

    // LiveData for holding recent searches
    private val _recentSearches = MutableLiveData<ArrayList<CurrentLocationWeather>>()
    val recentSearches: LiveData<ArrayList<CurrentLocationWeather>> = _recentSearches

    fun addRecentSearch(currentWeather: CurrentLocationWeather?) {
        if(currentWeather != null) {
            val currentList = recentSearches.value ?: ArrayList()
            val updatedList = ArrayList<CurrentLocationWeather>()
            updatedList.addAll(currentList)
            updatedList.add(currentWeather)
            _recentSearches.value = updatedList
        }
    }

}

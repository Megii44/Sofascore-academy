package com.example.homework_5.ui.search

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_5.database.CityWeatherDatabase
import com.example.homework_5.model.CurrentLocationWeather
import com.example.homework_5.model.CurrentLocationWeatherResponse
import com.example.homework_5.networking.Network
import com.example.homework_5.utils.getApiKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    // LiveData for holding recent searches
    private val _recentSearches = MutableLiveData<ArrayList<CurrentLocationWeather>>()
    val recentSearches: LiveData<ArrayList<CurrentLocationWeather>> = _recentSearches

    private fun addRecentSearch(currentWeather: CurrentLocationWeather?) {
        if(currentWeather != null) {
            val currentList = recentSearches.value ?: ArrayList()
            val updatedList = ArrayList<CurrentLocationWeather>()
            updatedList.addAll(currentList)
            updatedList.add(currentWeather)
            _recentSearches.value = updatedList
        }
    }

    fun searchLocations(query: String, adapter: ArrayAdapter<String>) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = Network().getService().searchLocations(getApiKey(), query)
            if (response.isSuccessful) {
                response.body()?.map { it.name }?.let { locationNames ->
                    adapter.clear()
                    adapter.addAll(locationNames)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    fun getCurrentWeatherForLocation(query: String) {
        if (query.isNotBlank()) {
            CoroutineScope(Dispatchers.Main).launch {
                val response = Network().getService().getCurrentWeatherForLocation(getApiKey(), query)
                response.body()?.let { addRecentSearch(CurrentLocationWeather(location = it.location, current = it.current)) }
            }
        }
    }

    fun insertCityWeatherToDb(context: Context, cityWeather: CurrentLocationWeatherResponse) {
        viewModelScope.launch {
            val db = CityWeatherDatabase.getDatabase(context)
            db?.CityWeatherDao()?.insertCityWeather(cityWeather)
        }
    }
}

package com.example.homework_5.ui.my_cities

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_5.database.CityWeatherDatabase
import com.example.homework_5.model.CurrentLocationWeather
import com.example.homework_5.model.CurrentLocationWeatherResponse
import kotlinx.coroutines.launch

class MyCitiesViewModel : ViewModel() {

    // LiveData for holding my cities
    private val _myCities = MutableLiveData<ArrayList<CurrentLocationWeather>>()
    val myCities: LiveData<ArrayList<CurrentLocationWeather>> = _myCities

    private fun addMyCities(city: CurrentLocationWeather?) {
        myCities
        val updatedList = ArrayList<CurrentLocationWeather>()
        if (city != null) {
            updatedList.add(city)
        }
        _myCities.value = updatedList
    }

    fun insertCityWeatherToDb(context: Context, cityWeather: CurrentLocationWeatherResponse) {
        viewModelScope.launch {
            val db = CityWeatherDatabase.getDatabase(context)
            db?.CityWeatherDao()?.insertCityWeather(cityWeather)
        }
    }
}

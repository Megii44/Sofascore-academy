package com.example.homework_5.ui.city

import SequenceRecyclerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_5.databinding.ActivityCityBinding
import com.example.homework_5.helpers.getFormattedDate
import com.example.homework_5.helpers.getFormattedTime
import com.example.homework_5.helpers.getWeatherIcon
import com.example.homework_5.model.CurrentLocationWeather
import com.example.homework_5.model.Forecast
import com.example.homework_5.networking.Network
import com.example.homework_5.utils.getApiKey
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityBinding
    private lateinit var sequenceRecyclerView: RecyclerView
    private lateinit var sequenceAdapter: SequenceRecyclerAdapter
    private lateinit var viewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout for this activity
        binding = ActivityCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the JSON string from the intent
        val cityWeatherJson = intent.getStringExtra("cityWeather")

        // Deserialize the JSON string to a CurrentLocationWeather object
        val cityWeather = Gson().fromJson(cityWeatherJson, CurrentLocationWeather::class.java)

        // Set the title to the location's name
        binding.title.text = cityWeather.location.name

        // Set values inside baseCityInfoView container
        val baseCityInfoView = binding.baseCityInfoView
        val now = Calendar.getInstance().time
        val formattedDate = getFormattedDate(now)
        val formattedTime = getFormattedTime(now)
        baseCityInfoView.setDate(formattedDate)
        baseCityInfoView.setTime(formattedTime)
        val icon = getWeatherIcon(cityWeather.current.condition.code)
        baseCityInfoView.setIcon(icon)
        baseCityInfoView.setDescription(cityWeather.current.condition.text)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[CityViewModel::class.java]

        // Initialize the RecyclerView
        sequenceRecyclerView = binding.hourlyWeatherRecyclerView

        // Set the layout manager
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        sequenceRecyclerView.layoutManager = layoutManager

        // Create the adapter with the hourly weather data
        val emptyList = emptyList<Forecast>()
        sequenceAdapter = SequenceRecyclerAdapter(emptyList)

        // Set the adapter to the RecyclerView
        sequenceRecyclerView.adapter = sequenceAdapter

        // Observe the data from the ViewModel and update the UI accordingly
        viewModel.forecast.observe(this) { forecastData ->
            // Update the adapter with the new data
            sequenceAdapter.updateData(forecastData)
        }

        getForecast(cityWeather.location.name + "&days=8")
    }

    private fun getForecast(query: String) {
        if (query.isNotBlank()) {
            CoroutineScope(Dispatchers.Main).launch {
                val response = Network().getService().getForecast(getApiKey(), query)
                val responseBody = response.body()

                // Get 8 days prognosis - today + next 7 days
                responseBody?.let {
                    viewModel.updateNextSevenDaysForecast(Forecast(forecastday =  it.forecast.forecastday))
                }

            }
        }
    }
}

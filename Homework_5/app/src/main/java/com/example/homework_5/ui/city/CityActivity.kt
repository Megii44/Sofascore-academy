package com.example.homework_5.ui.city

import SequenceAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_5.databinding.ActivityCityBinding
import com.example.homework_5.helpers.getFormattedDate
import com.example.homework_5.helpers.getFormattedTime
import com.example.homework_5.model.CurrentLocationWeather
import com.example.homework_5.utils.getApiKey
import com.google.gson.Gson
import java.util.*

class CityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityBinding
    private lateinit var sequenceRecyclerView: RecyclerView
    private lateinit var sequenceAdapter: SequenceAdapter
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
        var baseCityInfoView = binding.baseCityInfoView
        val now = Calendar.getInstance().time
        val formattedDate = getFormattedDate(now)
        val formattedTime = getFormattedTime(now)
        baseCityInfoView.setDate(formattedDate)
        baseCityInfoView.setTime(formattedTime)
        //baseCityInfoView.setIcon(cityWeather.current.condition.icon)
        baseCityInfoView.setDescription(cityWeather.current.condition.text)

        // Inflate the layout for this activity
        //val baseCityBinding = BaseCityInfoViewBinding.inflate(layoutInflater)
        //baseCityBinding.date.text = Date().toString()
        //baseCityBinding.description.text = cityWeather.current.condition.text
        //baseCityBinding.temperature.text = cityWeather.current.temp_c.toString()

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[CityViewModel::class.java]

        // Initialize the RecyclerView
        //sequenceRecyclerView = binding.hourlyWeatherRecyclerView

        // Set the layout manager
        //val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //sequenceRecyclerView.layoutManager = layoutManager

        // Create the adapter with the hourly weather data
        //val emptyList = emptyList<Hour>()
        //sequenceAdapter = SequenceAdapter(emptyList)

        // Set the adapter to the RecyclerView
        //sequenceRecyclerView.adapter = sequenceAdapter

        // Observe the data from the ViewModel and update the UI accordingly
        //viewModel.dailyForecast.observe(this, Observer { sequenceWeatherData ->
            // Update the adapter with the new data
            //sequenceAdapter.updateData(sequenceWeatherData)
        //})

        //getDailyForecastSequence(cityWeather.location.name)

    }

    private fun getDailyForecastSequence(query: String) {
        val apiKey = getApiKey()

        /*CoroutineScope(Dispatchers.Main).launch {
            val response = Network().getService().getDailyForecast(apiKey, query)
            if (response.isSuccessful) {
                val forecastResponse = response.body()
                val hourlyForecast = forecastResponse?.let { (it.forecast) }
                if(hourlyForecast != null) {
                    viewModel.updateForecast(hourlyForecast)
                }
            } else {
                // Handle the API error call
            }
        }*/
    }
}

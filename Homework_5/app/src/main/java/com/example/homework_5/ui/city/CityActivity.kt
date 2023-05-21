package com.example.homework_5.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_5.adapter.SequenceRecyclerAdapter
import com.example.homework_5.databinding.ActivityCityBinding
import com.example.homework_5.helpers.*
import com.example.homework_5.model.CurrentLocationWeather
import com.example.homework_5.model.TimeItem
import com.example.homework_5.networking.Network
import com.example.homework_5.utils.getApiKey
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityBinding
    private lateinit var todaySequenceRecyclerView: RecyclerView
    private lateinit var nextSevenDaysSequenceRecyclerView: RecyclerView
    private lateinit var todaySequenceAdapter: SequenceRecyclerAdapter
    private lateinit var nextSevenDaysSequenceAdapter: SequenceRecyclerAdapter
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

        // Set values for weather info tiles
        val temperatureWeatherInfoTile = binding.weatherInfoTileTemperature
        temperatureWeatherInfoTile.setValue(cityWeather.current.temp_c.toString())
        val windWeatherInfoTile = binding.weatherInfoTileWind
        windWeatherInfoTile.setValue(cityWeather.current.wind_kph.toString())
        val humidityWeatherInfoTile = binding.weatherInfoTileHumidity
        humidityWeatherInfoTile.setValue(cityWeather.current.humidity.toString())
        val pressureWeatherInfoTile = binding.weatherInfoTilePressure
        pressureWeatherInfoTile.setValue(cityWeather.current.pressure_in.toString())
        val visibilityWeatherInfoTile = binding.weatherInfoTileVisibility
        visibilityWeatherInfoTile.setValue(cityWeather.current.vis_km.toString())
        val accuracyWeatherInfoTile = binding.weatherInfoTileAccuracy
        accuracyWeatherInfoTile.setValue("93%")

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[CityViewModel::class.java]

        // Get forecast data with API call
        getForecast(cityWeather.location.name, 8)

        // Get the RecyclerViews
        todaySequenceRecyclerView = binding.hourlyWeatherRecyclerView
        nextSevenDaysSequenceRecyclerView = binding.next7DaysWeatherRecyclerView

        // SET TODAY WEATHER SEQUENCE
        //
        // Set the layout manager for the today recycler view
        val todayLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        todaySequenceRecyclerView.layoutManager = todayLayoutManager

        // Observe the data from the ViewModel and update the UI accordingly
        viewModel.todayForecast.observe(this) { todayForecastData ->
            // Create the adapter with the forecast data
            todaySequenceAdapter = SequenceRecyclerAdapter(this, todayForecastData)

            // Set the adapter to the RecyclerView
            todaySequenceRecyclerView.adapter = todaySequenceAdapter

            // Update the adapter with the new data
            todaySequenceAdapter.updateData(todayForecastData)
        }

        // SET NEXT SEVEN DAYS WEATHER SEQUENCE
        //
        // Set the layout manager for the next seven days recycler view
        val nextSevenDaysLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        nextSevenDaysSequenceRecyclerView.layoutManager = nextSevenDaysLayoutManager

        // Observe the data from the ViewModel and update the UI accordingly
        viewModel.nextSevenDaysForecast.observe(this) { nextSevenDaysForecastData ->
            // Create the adapter with the forecast data
            nextSevenDaysSequenceAdapter = SequenceRecyclerAdapter(this, nextSevenDaysForecastData)

            // Set the adapter to the RecyclerView
            nextSevenDaysSequenceRecyclerView.adapter = nextSevenDaysSequenceAdapter

            // Update the adapter with the new data
            nextSevenDaysSequenceAdapter.updateData(nextSevenDaysForecastData)
        }

    }

    private fun getForecast(locationName: String, days_: Int) {
        if (locationName.isNotBlank()) {
            CoroutineScope(Dispatchers.Main).launch {
                val response = Network().getService().getForecast(getApiKey(), locationName, days_)
                val responseBody = response.body()

                responseBody?.let {
                    // Get today's hourly forecast
                    val todayHourlyForecast = it.forecast.forecastday[0].hour.map { hour ->
                        TimeItem(
                            hour = getFormattedTime(hour.time),
                            icon = getWeatherIcon(hour.condition.code),
                            temp_c = hour.temp_c,
                            temp_f = hour.temp_f
                        )
                    }
                    viewModel.updateTodayForecast(todayHourlyForecast)

                    // Get the next 7 days forecast
                    val nextSevenDaysForecast = it.forecast.forecastday.map { forecastday ->
                        print(it.forecast.forecastday)
                        TimeItem(
                            hour = getDayOfWeekAbr(forecastday.date),
                            icon = getWeatherIcon(forecastday.day.condition.code),
                            temp_c = forecastday.day.avgtemp_c,
                            temp_f = forecastday.day.avgtemp_f
                        )
                    }
                    viewModel.updateNextSevenDaysForecast(nextSevenDaysForecast)
                }

            }
        }
    }
}

package com.example.homework_5.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_5.databinding.ActivityCityBinding
import com.example.homework_5.model.CurrentLocationWeather
import com.google.gson.Gson

class CityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityBinding

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
    }
}

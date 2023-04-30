package com.example.homework_5.model

import android.graphics.drawable.Icon
import java.util.Date

data class DailyForecast (
    val id: Int,
    val city: City,
    val date: Date,
    val description: String,
    val temperature: Int,
    val icon: Icon,
    val minTemperature: Int,
    val maxTemperature: Int,
    val windSpeed: Int,
    val humidity: Int,
    val pressure: Int,
    val visibility: Int,
    val accuracy: Int
    ) : java.io.Serializable
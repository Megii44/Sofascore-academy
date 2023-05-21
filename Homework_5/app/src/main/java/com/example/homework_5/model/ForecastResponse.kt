package com.example.homework_5.model

data class ForecastResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast,
) : java.io.Serializable

data class Forecast(
    val forecastday: List<Forecastday>,
) : java.io.Serializable

data class Forecastday(
    val date: String,
    val day: Day,
    val hour: List<Hour>
) : java.io.Serializable

data class Hour(
    val time: String,
    val temp_c: Double,
    val temp_f: Double,
    val isDay: Boolean,
    val condition: Condition,
) : java.io.Serializable

data class Day(
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val condition: Condition,
) : java.io.Serializable

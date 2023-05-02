package com.example.homework_5.model

import java.sql.Time

data class ForecastResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast,
) : java.io.Serializable

data class Forecast(
    val forecastDay: List<ForecastDay>,
) : java.io.Serializable

data class ForecastDay(
    val hour: List<Hour>
) : java.io.Serializable

data class Hour(
    val time: Time,
    val temp_c: Double,
    val temp_f: Double,
    val isDay: Boolean,
    val condition: Condition,
) : java.io.Serializable

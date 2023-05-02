package com.example.homework_5.helpers

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import com.example.homework_5.R
import java.util.Date
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun fromLatLonToDMS(lat: Double, lon: Double): String {
    val latDegree = abs(lat.toInt())
    val lonDegree = abs(lon.toInt())

    val latMinutes = ((abs(lat) - latDegree) * 60).toInt()
    val lonMinutes = ((abs(lon) - lonDegree) * 60).toInt()

    val latDirection = if (lat >= 0) "N" else "S"
    val lonDirection = if (lon >= 0) "E" else "W"

    return "$latDegree°$latMinutes'$latDirection, $lonDegree°$lonMinutes'$lonDirection"
}

fun getWeatherIcon(iconCode: Int): Int {
    return when (iconCode) {
        1006, 1009 -> R.drawable.ic_weather_hc // Cloudy
        1204, 1207, 1249, 1252 -> R.drawable.ic_weather_sl // Sleet
        1114, 1117, 1219, 1222, 1225 -> R.drawable.ic_weather_h // Heavy Snow
        1210, 1213, 1216, 1255, 1258 -> R.drawable.ic_weather_sn // Snow
        1180, 1183, 1240 -> R.drawable.ic_weather_lr // Light Rain
        1186, 1189, 1192, 1195, 1243, 1246 -> R.drawable.ic_weather_hr // Heavy Rain
        1087, 1273, 1276 -> R.drawable.ic_weather_t // Thunder
        1063, 1240 -> R.drawable.ic_weather_s // Rain with a little sun
        1003 -> R.drawable.ic_weather_lc // Cloudy with a little sun
        1000 -> R.drawable.ic_weather_c // Sunny
        else -> R.drawable.ic_weather_c // Default to Sunny if no match
    }
}


fun getFormattedDate(date: Date): String {
    val dateFormat = SimpleDateFormat("EEE, MMMM dd", Locale.getDefault())
    return dateFormat.format(date)
}

fun getFormattedTime(time: Date): String {
    val timeFormat = SimpleDateFormat("hh:mm a (z)", Locale.getDefault())
    return timeFormat.format(time)
}

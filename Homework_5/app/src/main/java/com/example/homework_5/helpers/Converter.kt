package com.example.homework_5.helpers

import com.example.homework_5.R
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

fun fromWeatherCodeToDrawableIcon(code: Int): Int {
    return when (code) {
        1000 -> R.drawable.ic_weather_hc
        1001 -> R.drawable.ic_weather_sl
        1002 -> R.drawable.ic_weather_h
        1003 -> R.drawable.ic_weather_sn
        1004 -> R.drawable.ic_weather_lr
        1005 -> R.drawable.ic_weather_hr
        1006 -> R.drawable.ic_weather_t
        1007 -> R.drawable.ic_weather_s
        1008 -> R.drawable.ic_weather_lc
        1009 -> R.drawable.ic_weather_c
        else -> R.drawable.ic_weather_c
    }
}

package com.example.minisofascore.ui.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.minisofascore.data.enums.SportEnum
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun getSportForPosition(position: Int): SportEnum {
    return when (position) {
        0 -> SportEnum.Football
        1 -> SportEnum.Basketball
        2 -> SportEnum.AmericanFootball
        else -> throw IllegalArgumentException("Invalid position")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun checkEventStatus(startTime: String): Int {
    val eventStartTime = ZonedDateTime.parse(startTime)
    val now = ZonedDateTime.now(ZoneId.of("UTC"))

    return when {
        now.isBefore(eventStartTime) -> {
            0
        }
        now.isAfter(eventStartTime.plusHours(2)) -> {
            // Assuming that the event lasts approximately 2 hours
            1
        }
        else -> {
            2
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedTime(date: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return date.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedTime(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return date.format(formatter)
}
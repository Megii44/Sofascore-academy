package com.example.minisofascore.utils

import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.res.TypedArrayUtils.getString
import com.bumptech.glide.Glide
import com.example.minisofascore.R
import com.example.minisofascore.data.enums.EventStatusEnum
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
fun getFormattedDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return date.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDate(dateTimeString: String): String {
    val zonedDateTime = ZonedDateTime.parse(dateTimeString)
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return formatter.format(zonedDateTime)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedTime(dateTimeString: String): String {
    val zonedDateTime = ZonedDateTime.parse(dateTimeString)
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return formatter.format(zonedDateTime)
}

fun getEventStatusAbr(context: Context, status: String, time: String = ""): String {
    return when (status) {
        EventStatusEnum.InProgress.toString() -> time.ifEmpty { "NOW" }
        EventStatusEnum.Finished.toString() -> context.getString(R.string.finished_abr)
        EventStatusEnum.NotStarted.toString() -> context.getString(R.string.not_started_abr)
        else -> ""
    }
}

fun loadImage(context: Context, url: String, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .into(imageView)
}

fun loadRoundImage(context: Context, url: String, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(imageView);
}